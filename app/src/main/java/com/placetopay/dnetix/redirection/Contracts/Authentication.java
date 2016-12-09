package com.placetopay.dnetix.redirection.Contracts;


import android.util.Base64;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Authentication implements Mappable {

    private String login;
    private String tranKey;
    private String nonce;
    private String seed;

    public Authentication(String login, String tranKey) {
        this.login = login;
        this.tranKey = tranKey;
        this.create();
    }

    public Authentication create() {
        this.nonce = new BigInteger(130, new SecureRandom()).toString();
        this.seed = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ", Locale.getDefault())).format(new Date());
        return this;
    }

    public Authentication setNonce(String nonce) {
        this.nonce = nonce;
        return this;
    }

    public Authentication setSeed(String seed) {
        this.seed = seed;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public String getTranKey() {
        return tranKey;
    }

    public String getNonce() {
        return nonce;
    }

    public String getSeed() {
        return seed;
    }

    public String getDigestTrankey(){
        try {
            return base64(sha1(getNonce() + getSeed() + getTranKey()));
        } catch (Exception e) {
            return "";
        }
    }

    public String getEncodedNonce() {
        return base64(getNonce().getBytes());
    }

    static byte[] sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        return mDigest.digest(input.getBytes());
    }

    static String base64(byte[] input) {
        byte[] encodedBytes = Base64.encode(input, Base64.DEFAULT);
        return (new String(encodedBytes)).replace("\n", "");
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("login", getLogin());
        map.put("tranKey", getDigestTrankey());
        map.put("seed", getSeed());
        map.put("nonce", getEncodedNonce());
        return map;
    }
}
