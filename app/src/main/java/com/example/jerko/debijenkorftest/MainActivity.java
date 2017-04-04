package com.example.jerko.debijenkorftest;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.jerko.debijenkorftest.fragments.ProductsFragment;
import com.example.jerko.debijenkorftest.fragments.SearchFragment;
import com.example.jerko.debijenkorftest.models.RootData;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class MainActivity extends AppCompatActivity {

    private Fragment frag;
    private RootData mData;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolBar();
        setFragment("search");

    }


    public void setFragment(String fragName){
        if (fragName.equals("search"))
            frag = new SearchFragment();
        else
            frag = new ProductsFragment();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit);
        fragmentTransaction.replace(R.id.fragment_holder, frag);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (frag instanceof ProductsFragment) setFragment("search");
        else super.onBackPressed();
    }

    public void hideKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void openLinkInBrowser(String url) {
        try{
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" + url;
            }
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        } catch (ActivityNotFoundException e){
            e.printStackTrace();
        }

    }

    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle(getResources().getString(R.string.search_debijenkorf));
        setSupportActionBar(toolbar);
    }

    public void setToolbarIcon(){
        if (frag instanceof ProductsFragment){
            toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha );
            toolbar.setNavigationOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setFragment("search");
                        }
                    }

            );
            if (mData != null && mData.getSearchText() != null){
                try {
                    String searchText = URLDecoder.decode(mData.getSearchText().getOriginal(), "utf-8");
                    toolbar.setTitle(searchText);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        } else {
            toolbar.setNavigationIcon(null);
            toolbar.setTitle(getResources().getString(R.string.search_debijenkorf));
        }
    }

    public void setmData(RootData mData){
        this.mData = mData;
    }
    public RootData getmData(){
        return  mData;
    }
}
