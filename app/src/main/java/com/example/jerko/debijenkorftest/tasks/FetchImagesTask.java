package com.example.jerko.debijenkorftest.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.jerko.debijenkorftest.fragments.ProductsFragment;
import com.example.jerko.debijenkorftest.models.Product;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Jerko on 18.1.2017..
 */

public class FetchImagesTask extends AsyncTask<String, Void, Bitmap> {
    Exception  exception;
    Product product;
    ProductsFragment productsFragment;
    boolean lastImg;

    public FetchImagesTask(ProductsFragment productsFragment, Product product, boolean lastImg){
        this.product = product;
        this.productsFragment = productsFragment;
        this.lastImg = lastImg;
    }

    protected Bitmap doInBackground(String... params) {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(
                    (InputStream)new URL("http:" + product.getCurrentVariantProduct().getImages().get(0).getUrl()).getContent());
            return bitmap;
        } catch (Exception e) {
            this.exception = e;

            return null;
        }
    }

    protected void onPostExecute(Bitmap bitmap) {
        productsFragment.fillImages(bitmap);
        if (lastImg) productsFragment.setGridView();
    }
}
