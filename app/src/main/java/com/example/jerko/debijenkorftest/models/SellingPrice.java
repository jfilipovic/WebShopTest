package com.example.jerko.debijenkorftest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Jerko on 18.1.2017..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SellingPrice {

    String value;

    public String getValue() {
        return value;
    }
}
