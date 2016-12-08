package com.placetopay.dnetix.redirection.Entities;

import com.placetopay.dnetix.redirection.Contracts.Mappable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class RedirectRequest implements Mappable {

    private Person payer;
    private Person buyer;
    private Payment payment;
    private String expiration;
    private String ipAddress;
    private String returnUrl;
    private String userAgent;

    public RedirectRequest() {
        this.payer = new Person();
        this.buyer = new Person();
        this.payment = new Payment();

        // Setting a default expiration
        this.expiration = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.getDefault())).format(new Date(Calendar.getInstance().getTimeInMillis() + (300 * 60000)));
    }

    public Person getPayer() {
        return payer;
    }

    public Person getBuyer() {
        return buyer;
    }

    public Payment getPayment() {
        return payment;
    }

    public String getExpiration() {
        return expiration;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public RedirectRequest setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public RedirectRequest setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
        return this;
    }

    public RedirectRequest setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("payer", getPayer().toMap());
        map.put("buyer", getBuyer().toMap());
        map.put("payment", getPayment().toMap());
        map.put("expiration", getExpiration());
        map.put("ipAddress", getIpAddress());
        map.put("returnUrl", getReturnUrl());
        map.put("userAgent", getUserAgent());
        return map;
    }

}
