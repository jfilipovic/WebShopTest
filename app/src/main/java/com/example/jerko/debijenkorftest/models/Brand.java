package com.example.jerko.debijenkorftest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Jerko on 18.1.2017..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Brand {
    private String name;

    public String getName() {
        return name;
    }
}
