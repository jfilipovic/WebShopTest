package com.example.jerko.debijenkorftest.rest;

/**
 * Created by Jerko on 18.1.2017..
 */

import android.content.Context;
import android.util.Log;
import com.example.jerko.debijenkorftest.models.ResponseData;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RestService {

    private Context context;

    public RestService(Context context) {
        this.context = context;

    }

    public ResponseData getResponseData(String productName){
        try {

            String query = android.net.Uri.encode(productName, "utf-8");
            final String url = "https://ceres-catalog.debijenkorf.nl/catalog/navigation/search?text=" + query;

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Log.d("REST", url);
            ResponseData responseData = restTemplate.getForObject(url, ResponseData.class);

            return responseData;
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }


}

