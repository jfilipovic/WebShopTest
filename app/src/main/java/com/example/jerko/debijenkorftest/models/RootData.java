package com.example.jerko.debijenkorftest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Jerko on 18.1.2017..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RootData {

    private String redirectUrl;
    private List<Product> products;
    private SearchText searchText;

    public List<Product> getProducts() {
        return products;
    }
    public String getRedirectUrl(){
        return redirectUrl;
    }
    public SearchText getSearchText() {
        return searchText;
    }
}
