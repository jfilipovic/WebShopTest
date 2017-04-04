package com.example.jerko.debijenkorftest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Jerko on 18.1.2017..
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseData {

    private RootData data;
    public RootData getData(){
        return data;
    }
}
