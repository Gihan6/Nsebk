package com.RiyadSoftware.nsebkapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;

public class LanguageActivity extends AppCompatActivity {


    //to set the language
    SharedPrefDueDate pref;


    int dataIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_langauge);


        dataIntent = getIntent().getIntExtra("data", 0);
        pref = new SharedPrefDueDate(this);

    }

    public void englishAction(View view) {

        pref.setEnglish();
        if (dataIntent == 0) {
            Intent intent = new Intent(LanguageActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Intent i = new Intent(LanguageActivity.this, MainActivity2.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }

    public void arabicAction(View view) {
        pref.setArabic();
        if (dataIntent == 0) {
            Intent intent = new Intent(LanguageActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Intent i = new Intent(LanguageActivity.this, MainActivity2.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }

    }

    public void sarAction(View view) {
        pref.setSARCurrency();
        //TODO SAR Currency Logic
    }

    public void dollarAction(View view) {
        pref.setDOLLARCurrency();
        //TODO $ Currency Logic
    }


}
