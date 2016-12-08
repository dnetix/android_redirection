package com.placetopay.dnetix.testingapp;

import android.os.AsyncTask;

import com.placetopay.dnetix.container.IoCWrapper;
import com.placetopay.dnetix.redirection.Entities.RedirectRequest;

public class CreateAsyncTask extends AsyncTask {

    @Override
    protected Object doInBackground(Object[] objects) {

        RedirectRequest redirectRequest = (RedirectRequest) objects[0];

        IoCWrapper.getInstance().getPlacetopayApi().createRequest(redirectRequest);

        return null;
    }

}
