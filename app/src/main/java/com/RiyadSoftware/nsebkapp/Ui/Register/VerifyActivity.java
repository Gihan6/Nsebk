package com.RiyadSoftware.nsebkapp.Ui.Register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.activities.Login;
import com.RiyadSoftware.nsebkapp.activities.MainActivity2;
import com.RiyadSoftware.nsebkapp.base.BaseActivity;
import com.RiyadSoftware.nsebkapp.data.CountriesResponse;
import com.RiyadSoftware.nsebkapp.data.models.CitiesModel;
import com.RiyadSoftware.nsebkapp.data.models.LoginResponse;
import com.RiyadSoftware.nsebkapp.data.models.RegisterResponse;
import com.RiyadSoftware.nsebkapp.data.models.VerifyResponse;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VerifyActivity extends BaseActivity implements RegisterView {

    @Inject
    RegisterPresenter mRegisterPresenter;

    @BindView(R.id.codeEt)
    EditText codeEt;

    SharedPrefDueDate sharedPrefDueDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        activityComponent().inject(this);
        mRegisterPresenter.attachView(this);
        ButterKnife.bind(this);

        sharedPrefDueDate = new SharedPrefDueDate(this);
    }


    @OnClick(R.id.skipBtn)
    public void verifyEmail9(){
        if (codeEt.getText().toString().isEmpty())
            return;

        Map<String , String> val
                 = new HashMap<>();
        val.put("code" , codeEt.getText().toString());
    mRegisterPresenter.verifyUsr(val);
    }

    @Override
    public void navigateToMain(RegisterResponse response) {

    }

    @Override
    public void showCountries(CountriesResponse countriesResponse) {

    }

    @Override
    public void showCities(CitiesModel countriesResponse) {

    }

    @Override
    public void afterVerify(VerifyResponse countriesResponse) {
        if (countriesResponse.getSuccess().equalsIgnoreCase("success")) {
            LoginResponse loginResponse = new LoginResponse();

            loginResponse.setData(sharedPrefDueDate.getUserLogged()) ;
            loginResponse.getData().setVerified(true);
            sharedPrefDueDate.setUserLogged(new Gson().toJson(loginResponse));

            Intent i = new Intent(VerifyActivity.this, MainActivity2.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }

    @Override
    public void showErrorInRegister(Throwable throwable) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }
}
