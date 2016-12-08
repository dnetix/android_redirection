package com.placetopay.dnetix.container;

import com.placetopay.dnetix.redirection.PlacetopayApi;

/**
 *
 */
public class IoCWrapper {

    private static IoCWrapper instance;

    private PlacetopayApi placetopayApi;

    private IoCWrapper() {
    }

    public static IoCWrapper getInstance() {

        if (IoCWrapper.instance == null)
            IoCWrapper.instance = new IoCWrapper();

        return IoCWrapper.instance;
    }

    public void setPlacetopayApi(PlacetopayApi placetopayApi) {
        this.placetopayApi = placetopayApi;
    }

    public PlacetopayApi getPlacetopayApi() {
        return this.placetopayApi;
    }
}
