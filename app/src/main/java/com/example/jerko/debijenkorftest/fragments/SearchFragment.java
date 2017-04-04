package com.example.jerko.debijenkorftest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jerko.debijenkorftest.MainActivity;
import com.example.jerko.debijenkorftest.R;
import com.example.jerko.debijenkorftest.rest.RestService;
import com.example.jerko.debijenkorftest.tasks.GetResponseDataTask;

/**
 * Created by Jerko on 18.1.2017..
 */

public class SearchFragment extends Fragment {


    private Button searchButton;
    private EditText searchBox;
    private TextView redirectLink;
    private LinearLayout redirectLayout;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ((MainActivity)getActivity()).setToolbarIcon();

        searchButton = (Button) view.findViewById(R.id.search_button);
        searchBox = (EditText) view.findViewById(R.id.search_view);
        redirectLayout = (LinearLayout) view.findViewById(R.id.redirect_layout);
        redirectLink = (TextView) view.findViewById(R.id.redirect_url_view);

        searchButton.setEnabled(false);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).hideKeyboard();
                SearchFragment f = (SearchFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_holder);
                new GetResponseDataTask(getActivity(), f , new RestService(getActivity()), searchBox.getText().toString()).execute();
            }
        });

        searchBox.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = searchBox.getText().toString();
                if (text != null && !text.isEmpty()) searchButton.setEnabled(true);
                else searchButton.setEnabled(false);
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        redirectLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).openLinkInBrowser(redirectLink.getText().toString());
            }
        });

        return view;
    }

    public void showRedirectLink(String url){
        redirectLink.setText(url);
        redirectLayout.setVisibility(View.VISIBLE);
    }

    public void goToProductsFragment(){
        ((MainActivity)getActivity()).setFragment("products");
    }

}
