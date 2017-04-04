package com.example.jerko.debijenkorftest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Jerko on 18.1.2017..
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchText {
    private String original;

    public String getOriginal() {
        return original;
    }
}
