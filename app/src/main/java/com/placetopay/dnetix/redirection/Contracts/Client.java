package com.placetopay.dnetix.redirection.Contracts;

import com.placetopay.dnetix.redirection.Entities.RedirectRequest;
import com.placetopay.dnetix.redirection.Entities.RedirectResponse;

public interface Client {

    void configure(String url, Authentication authentication);

    RedirectResponse createRequest(RedirectRequest redirectRequest);

}
