package com.placetopay.dnetix.redirection.Implementations;

import android.util.Log;
import android.widget.Toast;

import com.placetopay.dnetix.container.JSONHelper;
import com.placetopay.dnetix.redirection.Contracts.Authentication;
import com.placetopay.dnetix.redirection.Contracts.Client;
import com.placetopay.dnetix.redirection.Entities.RedirectRequest;
import com.placetopay.dnetix.redirection.Entities.RedirectResponse;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class RestBasicClient implements Client {

    private String url;
    private Authentication authentication;

    @Override
    public void configure(String url, Authentication authentication) {
        this.url = url;
        this.authentication = authentication;
    }

    @Override
    public RedirectResponse createRequest(RedirectRequest redirectRequest) {

        Map<String, Object> map = redirectRequest.toMap();
        map.put("auth", authentication.toMap());

        JSONObject data = JSONHelper.parseMap(map);

        try {
            URL url = new URL(this.url + "api/session");
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json");
            httpConnection.setRequestProperty("Accept","*/*");
            // Not required
            // urlConnection.setRequestProperty("Content-Length", String.valueOf(input.getBytes().length));
            // Writes the JSON parsed as string to the connection
            DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream());
            wr.write(data.toString().getBytes());
            Integer responseCode;
            try {
                responseCode = httpConnection.getResponseCode();
            } catch (IOException e) {
                responseCode = 401;
            }

            BufferedReader bufferedReader;

            // Creates a reader buffer
            if (responseCode > 199 && responseCode < 300) {
                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
            }

            // To receive the response
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();

            JSONObject response = new JSONObject(content.toString());
            RedirectResponse redirectResponse = new RedirectResponse();

            JSONObject status = (JSONObject) response.get("status");
            redirectResponse.getStatus()
                .setStatus(status.get("status").toString())
                .setMessage(status.getString("message"))
                .setReason(status.getString("reason"))
                .setDate(status.getString("date"));

            if (response.has("processUrl"))
                redirectResponse.setProcessUrl(response.getString("processUrl"))
                        .setRequestId(response.getString("requestId"));

            return redirectResponse;
        } catch (FileNotFoundException e) {
            Log.i("appx", "FNFE: " + e.toString() + " ++ " + e.getMessage());
        } catch (Exception e) {
            Log.i("appx", "Error: " + e.getClass().getSimpleName() + " ++ " + e.getMessage());
        }

        return null;
    }

}
