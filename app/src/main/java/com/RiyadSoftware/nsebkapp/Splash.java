package com.RiyadSoftware.nsebkapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;

import com.RiyadSoftware.nsebkapp.Ui.Register.VerifyActivity;
import com.RiyadSoftware.nsebkapp.activities.LanguageActivity;
import com.RiyadSoftware.nsebkapp.activities.MainActivity;
import com.RiyadSoftware.nsebkapp.activities.MainActivity2;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;

public class Splash extends AppCompatActivity {


    //init the shared pref
    SharedPrefDueDate pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        pref = new SharedPrefDueDate(this);


        new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {


                if (pref.getLanguage__first().equals("null") && pref.getCurrency__first().equals("0")) {
                    startActivity(new Intent(Splash.this, LanguageActivity.class));
                    finish();
                } else {
                    if (pref.getLanguage().equals("en")) {
                        pref.setEnglish();
                    } else {
                        pref.setArabic();
                    }
                    if (pref.getCurrency().equals("2")) {
                        pref.setSARCurrency();
                    } else {
                        pref.setDOLLARCurrency();
                    }


                    if (pref.getUserLogged() == null) {
                        startActivity(new Intent(Splash.this,
                                MainActivity.class));

                    } else {
                        if (pref.getUserLogged().isVerified())
                            startActivity(new Intent(Splash.this,
                                    MainActivity2.class));
                        else
                            startActivity(new Intent(Splash.this,
                                    VerifyActivity.class));
                    }
                    finish();
                }
            }
        }.start();
    }
}
