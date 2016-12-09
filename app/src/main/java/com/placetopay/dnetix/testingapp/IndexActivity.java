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

import com.placetopay.dnetix.container.IoCWrapper;
import com.placetopay.dnetix.redirection.Entities.RedirectRequest;
import com.placetopay.dnetix.redirection.PlacetopayApi;

import java.util.Locale;
import java.util.TimeZone;

public class IndexActivity extends AppCompatActivity {

    Button testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
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
        redirectRequest.getBuyer().setName("Diego").setSurname("Calle");
        redirectRequest.getPayment().getAmount().setTotal((double) 100000).setCurrency("COP");

        CreateAsyncTask createAsyncTask = new CreateAsyncTask();
        createAsyncTask.execute(redirectRequest);
    }
}
