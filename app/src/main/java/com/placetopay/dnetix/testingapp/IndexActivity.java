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
import android.widget.Button;
import android.widget.EditText;

import com.placetopay.dnetix.container.IoCWrapper;
import com.placetopay.dnetix.redirection.Entities.RedirectRequest;
import com.placetopay.dnetix.redirection.PlacetopayApi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class IndexActivity extends AppCompatActivity {

    Button testButton;
    EditText txtName;
    EditText txtSurname;
    EditText txtEmail;
    EditText txtAmount;
    EditText txtReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        txtName = (EditText) findViewById(R.id.txtName);
        txtSurname = (EditText) findViewById(R.id.txtSurname);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtAmount = (EditText) findViewById(R.id.txtAmount);
        txtReference = (EditText) findViewById(R.id.txtReference);
    }

    @Override
    protected void onResume() {
        super.onResume();

        txtReference.setText((new SimpleDateFormat("'TEST_'yyyyMMdd_HHmm", Locale.getDefault())).format(new Date()));
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
                sharedPreferences.getString("login", ""),
                sharedPreferences.getString("trankey", ""),
                sharedPreferences.getString("url", "")
        ));

        RedirectRequest redirectRequest = new RedirectRequest();
        redirectRequest.getBuyer()
                .setName(txtName.getText().toString())
                .setSurname(txtSurname.getText().toString());

        redirectRequest.getPayment()
                .setReference(txtReference.getText().toString())
                .getAmount().setTotal(Double.valueOf(txtAmount.getText().toString()))
                .setCurrency("COP");

        redirectRequest.setReturnUrl("http://dnetix.co/ping")
            .setIpAddress("127.0.0.1")
            .setUserAgent("Android APP");

        CreateAsyncTask createAsyncTask = new CreateAsyncTask();
        createAsyncTask.execute(redirectRequest);
    }
}
