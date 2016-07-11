package com.universeprojects.miniup.server.hooks;

import com.universeprojects.cacheddatastore.CachedEntity;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import org.json.JSONObject;

public class SlackHook
{
    private String token;

    public void init(String incomingWebhookToken) {
        token = incomingWebhookToken;
        // TODO: registerCallback("onGlobalChat", hookChat)
        // or something
    }

    public void chatHook(CachedEntity character, String message, long timestamp)
    {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        JSONObject json = new JSONObject();
        json.put("text", message);
        json.put("username", (String) character.getProperty("name"));

        try {
            HttpPost request = new HttpPost("https://hooks.slack.com/services/T06MWLA2X/B08AKLEHE/" + token);
            StringEntity params = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            httpClient.execute(request);
        } catch (Exception ex) {
            // Swallow exceptions for now
        } finally {
            httpClient.close();
        }
    }
}
