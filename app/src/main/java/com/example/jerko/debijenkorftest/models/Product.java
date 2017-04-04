package com.example.jerko.debijenkorftest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Jerko on 18.1.2017..
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    private String name;
    private String url;
    private Brand brand;
    private SellingPrice sellingPrice;
    private CurrentVariantProduct currentVariantProduct;

    public String getName() {
        return name;
    }
    public String getUrl() {
        return url;
    }
    public Brand getBrand() {
        return brand;
    }
    public SellingPrice getSellingPrice() {
        return sellingPrice;
    }
    public CurrentVariantProduct getCurrentVariantProduct() {
        return currentVariantProduct;
    }
}
