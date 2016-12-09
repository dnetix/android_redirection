package com.placetopay.dnetix.redirection.Entities;

import com.placetopay.dnetix.redirection.Contracts.Entity;

public class RedirectResponse extends Entity {

    private Status status;
    private String processUrl;
    private String requestId;

    public RedirectResponse() {
        this.status = new Status();
    }

    public Status getStatus() {
        return status;
    }

    public String getProcessUrl() {
        return processUrl;
    }

    public String getRequestId() {
        return requestId;
    }

    public RedirectResponse setProcessUrl(String processUrl) {
        this.processUrl = processUrl;
        return this;
    }

    public RedirectResponse setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

}
