package com.placetopay.dnetix.testingapp;

import android.os.AsyncTask;
import android.util.Log;

import com.placetopay.dnetix.container.IoCWrapper;
import com.placetopay.dnetix.redirection.Entities.RedirectRequest;

public class CreateAsyncTask extends AsyncTask {

    private ResponseHandler handler;

    public void setHandler(ResponseHandler handler) {
        this.handler = handler;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        RedirectRequest redirectRequest = (RedirectRequest) objects[0];

        return IoCWrapper.getInstance().getPlacetopayApi().createRequest(redirectRequest);
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        if (handler != null)
            handler.handleResponse("request", o);
    }
}
