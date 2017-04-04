package com.example.jerko.debijenkorftest.fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.jerko.debijenkorftest.MainActivity;
import com.example.jerko.debijenkorftest.R;
import com.example.jerko.debijenkorftest.adapters.ProductsAdapter;
import com.example.jerko.debijenkorftest.models.Product;
import com.example.jerko.debijenkorftest.models.RootData;
import com.example.jerko.debijenkorftest.tasks.FetchImagesTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerko on 18.1.2017..
 */

public class ProductsFragment extends Fragment {

    private GridView gridView;
    private TextView noProductText;
    private String searchText;
    private List<Product> products;
    private RootData data;
    private List<Bitmap> images;
    private ProductsFragment productsFragment;
    private Handler handler = new Handler();
    private Dialog dialog;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            dialog = ProgressDialog.show(getActivity(), null, getActivity().getResources().getString(R.string.dialog_images), true);
        }
    };

    public ProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_products, container, false);
        ((MainActivity)getActivity()).setToolbarIcon();

        gridView = (GridView) view.findViewById(R.id.grid_layout);
        noProductText = (TextView) view.findViewById(R.id.no_products_text);
        productsFragment = (ProductsFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_holder);

        data = ((MainActivity) getActivity()).getmData();
        searchText = data.getSearchText().toString();
        products = data.getProducts();
        images = new ArrayList<Bitmap>();

        if (data != null){
            if (products != null && products.size() > 0 ){
                getImages(products);
            }
            else {
                noProductText.setVisibility(View.VISIBLE);
                gridView.setVisibility(View.GONE);
            }
        }

        return view;
    }

    public void getImages(List<Product> products){
        showDialog();
        for(int i = 0; i < products.size(); i++){
            if (i == products.size() - 1) new FetchImagesTask(productsFragment, products.get(i), true).execute();
            else new FetchImagesTask(productsFragment, products.get(i), false).execute();
        }

    }

    public void fillImages(Bitmap image){
        images.add(image);
    }

    public  void setGridView(){
        noProductText.setVisibility(View.GONE);
        gridView.setVisibility(View.VISIBLE);
        dismissDialog();

        ProductsAdapter adapter = new ProductsAdapter(getActivity(), products, images);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id)
            {
                ((MainActivity)getActivity()).openLinkInBrowser(products.get(position).getUrl());
            }
        });
    }

    private void showDialog(){
        handler.postDelayed(runnable, 500);
    }

    private void dismissDialog() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }else {
                handler.removeCallbacks(runnable);
            }
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }

    }

}
