package com.placetopay.dnetix.redirection;


import com.placetopay.dnetix.redirection.Contracts.Authentication;
import com.placetopay.dnetix.redirection.Contracts.Client;
import com.placetopay.dnetix.redirection.Entities.RedirectRequest;
import com.placetopay.dnetix.redirection.Entities.RedirectResponse;
import com.placetopay.dnetix.redirection.Implementations.RestBasicClient;

public class PlacetopayApi {

    private String login;
    private String tranKey;
    private String url;
    public Client client;

    public PlacetopayApi(String login, String tranKey, String url) {
        this.login = login;
        this.tranKey = tranKey;
        this.url = url;

        this.client = new RestBasicClient();
        this.client.configure(getUrl(), getAuthentication());
    }

    public RedirectResponse createRequest(RedirectRequest redirectRequest) {
        return client.createRequest(redirectRequest);
    }

    public Authentication getAuthentication() {
       return new Authentication(getLogin(), getTranKey());
    }

    public String getLogin() {
        return login;
    }

    public String getTranKey() {
        return tranKey;
    }

    public String getUrl() {
        return url;
    }

}
