package com.RiyadSoftware.nsebkapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.RiyadSoftware.nsebkapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAccount extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.back_my_account)
    void back() {
        finish();
    }

    @OnClick(R.id.my_account_button_1)
    void my_account_button_1_click() {
        startActivity(new Intent(this, AccountInfos.class));
    }

    @OnClick(R.id.my_account_button_2)
    void my_account_button_2_click() {
        startActivity(new Intent(this, MyAddresses.class));
    }

    @OnClick(R.id.my_account_button_3)
    void my_account_button_3_click() {
        startActivity(new Intent(this, ShippingDates.class));
    }

    @OnClick(R.id.my_account_button_4)
    void my_account_button_4_click() {
        startActivity(new Intent(this, MyDeals.class));
    }

    @OnClick(R.id.my_account_button_5)
    void my_account_button_5_click() {
        startActivity(new Intent(this, MyInterests.class));
    }
}
