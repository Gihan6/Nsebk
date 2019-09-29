package com.RiyadSoftware.nsebkapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.RiyadSoftware.nsebkapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.main_register_textview)
    void register() {
        startActivity(new Intent(this, Register.class));
    }

    @OnClick(R.id.main_login_textview)
    void login() {
        startActivity(new Intent(this, Login.class));
    }

    @OnClick(R.id.skip)
    void skip() {
        startActivity(new Intent(this, /*AfterSkip.class*/ Login.class));
    }

}
