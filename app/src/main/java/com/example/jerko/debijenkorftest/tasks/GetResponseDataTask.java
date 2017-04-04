package com.example.jerko.debijenkorftest.tasks;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.jerko.debijenkorftest.MainActivity;
import com.example.jerko.debijenkorftest.R;
import com.example.jerko.debijenkorftest.fragments.SearchFragment;
import com.example.jerko.debijenkorftest.models.ResponseData;
import com.example.jerko.debijenkorftest.rest.RestService;

/**
 * Created by Jerko on 18.1.2017..
 */

public class GetResponseDataTask extends AsyncTask<String, Void, ResponseData> {

    private Activity activity;
    private SearchFragment fragment;
    private RestService restService;
    private Dialog dialog;
    private Handler handler = new Handler();
    private Runnable runnable;
    private String productName;


    public GetResponseDataTask(final Activity activity, SearchFragment fragment, RestService restService, String productName) {
        this.activity = activity;
        this.restService = restService;
        this.productName = productName;
        this.fragment = fragment;
        runnable = new Runnable() {
            @Override
            public void run() {
                dialog = ProgressDialog.show(activity, null, activity.getResources().getString(R.string.dialog_data), true);
            }
        };
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        showDialog();
    }

    @Override
    protected ResponseData doInBackground(String... params) {
        return restService.getResponseData(productName);
    }

    @Override
    protected void onPostExecute(ResponseData responseData) {

        if (activity instanceof MainActivity && responseData != null) {
            dismissDialog();
            if (responseData != null){
                if (responseData.getData().getRedirectUrl() != null){
                    fragment.showRedirectLink(responseData.getData().getRedirectUrl());
                }
                else{
                    ((MainActivity) activity).setmData(responseData.getData());
                    fragment.goToProductsFragment();
              //      Log.d("TASK", responseData.getData().getProducts().get(0).getName());
                }
            }

        }
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
