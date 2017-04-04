package com.example.jerko.debijenkorftest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Jerko on 18.1.2017..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentVariantProduct {
    private List<Image> images;

    public List<Image> getImages() {
        return images;
    }
}
