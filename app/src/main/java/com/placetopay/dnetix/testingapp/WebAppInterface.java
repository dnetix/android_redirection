package com.placetopay.dnetix.testingapp;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;

/**
 * It will interface with Javascript functions from the WebView
 */
public class WebAppInterface {
    Context context;

    public WebAppInterface(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void closeView() {
        ((IndexActivity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                IndexActivity activity = (IndexActivity) context;

                activity.btnTest.setVisibility(View.VISIBLE);
                activity.btnTest.bringToFront();
                activity.webView.setVisibility(View.INVISIBLE);
                activity.webView.destroy();
            }
        });
    }
}
