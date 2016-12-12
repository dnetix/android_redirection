package com.placetopay.dnetix.testingapp;

import android.content.Context;
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
        IndexActivity indexActivity = (IndexActivity) context;
        indexActivity.btnTest.setVisibility(View.VISIBLE);
        indexActivity.btnTest.bringToFront();
        indexActivity.webView.setVisibility(View.INVISIBLE);
    }
}
