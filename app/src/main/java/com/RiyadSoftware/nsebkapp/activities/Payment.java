package com.RiyadSoftware.nsebkapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.RiyadSoftware.nsebkapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.back_payment)
    void back_payment() {
        finish();
    }


}
