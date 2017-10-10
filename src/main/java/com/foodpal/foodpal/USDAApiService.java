package com.foodpal.foodpal;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.CollectionType;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class USDAApiService {
    private String usdaApiKey = "dvSNamKI73wkskIctqmUBZAwN7JKAfTTFG5yPnj3";
    private String apiRoot = "https://api.nal.usda.gov/ndb/";
    private OkHttpClient client;
    private ObjectMapper mapper;


    public USDAApiService(){
        this.client = new OkHttpClient();
        this.mapper = new ObjectMapper();
    }

    public List<GroceryListItem> search(String query){
        String queryString = "";
        try {
            queryString = String.format("q=%s&api_key=%s&ds=%s",
                    URLEncoder.encode(query, java.nio.charset.StandardCharsets.UTF_8.name()),
                    URLEncoder.encode(usdaApiKey, java.nio.charset.StandardCharsets.UTF_8.name()),
                    URLEncoder.encode("Standard Reference", java.nio.charset.StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = apiRoot + "search/?format=json&sort=n&max=25&offset=0&" + queryString;
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            CollectionType listType = mapper.getTypeFactory()
                    .constructCollectionType(List.class, GroceryListItem.class);
            return mapper.readValue(response.toString(), listType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public GroceryListItem foodReport(String nbId){
        String queryString = "";
        try {
            queryString = String.format("ndbno=%s&api_key=%s",
                    URLEncoder.encode(nbId, java.nio.charset.StandardCharsets.UTF_8.name()),
                    URLEncoder.encode(usdaApiKey, java.nio.charset.StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = apiRoot + "search/?format=json&type=b&" + queryString;
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return mapper.readValue(response.toString(), GroceryListItem.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
