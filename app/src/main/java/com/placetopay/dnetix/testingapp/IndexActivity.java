package com.placetopay.dnetix.testingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.placetopay.dnetix.container.IoCWrapper;
import com.placetopay.dnetix.redirection.Entities.RedirectRequest;
import com.placetopay.dnetix.redirection.Entities.RedirectResponse;
import com.placetopay.dnetix.redirection.PlacetopayApi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

public class IndexActivity extends AppCompatActivity implements ResponseHandler {

    WebView webView;
    EditText txtName;
    EditText txtSurname;
    EditText txtEmail;
    EditText txtAmount;
    EditText txtReference;
    Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        txtName = (EditText) findViewById(R.id.txtName);
        txtSurname = (EditText) findViewById(R.id.txtSurname);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtAmount = (EditText) findViewById(R.id.txtAmount);
        txtReference = (EditText) findViewById(R.id.txtReference);

        webView = (WebView) findViewById(R.id.webView);
        btnTest = (Button) findViewById(R.id.btn_test);
    }

    @Override
    protected void onResume() {
        super.onResume();

        txtReference.setText((new SimpleDateFormat("'TEST_'yyyyMMdd_HHmmss", Locale.getDefault())).format(new Date()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent i = new Intent(this, SettingsActivity.class);
                startActivityForResult(i, 1);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void testing(View v) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        IoCWrapper.getInstance().setPlacetopayApi(new PlacetopayApi(
                sharedPreferences.getString("login", "LOGIN_AQUI"),
                sharedPreferences.getString("trankey", "TRANKEY_AQUI"),
                sharedPreferences.getString("url", "https://test.placetopay.com/redirection/")
        ));

        RedirectRequest redirectRequest = new RedirectRequest();
        redirectRequest.getBuyer()
                .setDocumentType("CC")
                .setDocument("1040035000")
                .setEmail("dnetix@gmail.com")
                .setName(txtName.getText().toString())
                .setSurname(txtSurname.getText().toString());

        redirectRequest.getPayment()
                .setReference(txtReference.getText().toString())
                .setDescription("Testing purchase")
                .getAmount().setTotal(Double.valueOf(txtAmount.getText().toString()))
                .setCurrency("COP");

        redirectRequest.setReturnUrl("http://192.168.1.13/html")
            .setIpAddress("127.0.0.1")
            .setUserAgent("Android APP");

        CreateAsyncTask createAsyncTask = new CreateAsyncTask();
        createAsyncTask.setHandler(this);
        createAsyncTask.execute(redirectRequest);
    }

    @Override
    public void handleResponse(String type, Object response) {
        if (type.equals("request")) {
            RedirectResponse redirectResponse = (RedirectResponse) response;

            // Display on browser
            if (redirectResponse.getStatus().getStatus().equals("OK")) {
                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                webView.clearCache(true);
                if (webView.getContext() == null) {
                    Log.i("appx", "No context for WebView");
                }
                webView.clearHistory();
                webView.setVisibility(View.VISIBLE);
                webView.setWebChromeClient(new WebChromeClient());
                webView.setWebViewClient(new WebViewClient());

                /*
                 * Es importante agregar el display a la url para determinar que se carga desde un webview
                 * de esta manera se lanzará el Android.closeView() en vez de la redirección
                 */
                webView.loadUrl(redirectResponse.getProcessUrl() + "?display=webview");
                webView.addJavascriptInterface(new WebAppInterface(this), "Android");

                btnTest.setVisibility(View.INVISIBLE);
            } else {
                Toast.makeText(this, redirectResponse.getStatus().getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

}
