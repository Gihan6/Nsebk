package com.RiyadSoftware.nsebkapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.forgetPassword.ForgetPresenter;
import com.RiyadSoftware.nsebkapp.Ui.forgetPassword.ForgetView;
import com.RiyadSoftware.nsebkapp.base.BaseActivity;
import com.RiyadSoftware.nsebkapp.data.models.VerifyResponse;
import com.RiyadSoftware.nsebkapp.data.models.forgetPassword.VerifyCodeRequest;
import com.RiyadSoftware.nsebkapp.data.models.forgetPassword.VerifyCodeResponse;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VerifyForgetPassword extends BaseActivity implements ForgetView {

    @BindView(R.id.codeEtForgetPassword)
    EditText et_code;

    @BindView(R.id.progress_verify_code)
    ProgressBar progress_verify_code;
    @Inject
    ForgetPresenter mForgetPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_forget_password);
        ButterKnife.bind(this);

        activityComponent().inject(this);
        mForgetPresenter.attachView(this);
    }

    @OnClick(R.id.skipBtnForgetPassword)
    void sendCode() {

        Map<String , String> val
                = new HashMap<>();
        val.put("code" , et_code.getText().toString());

        mForgetPresenter.verifyCodeForgetPassword(val);

       // startActivity(new Intent(getApplicationContext(), NewPassword.class));

//
//        if (!TextUtils.isEmpty(et_code.getText().toString())) {
//            mForgetPresenter.Forget(new VerifyCodeRequest());
//
//        } else {
//
//            et_code.setError(getString(R.string.enterVerifyCode));
//
//        }


    }


    @Override
    public void navigateToNewPassword(VerifyResponse response) {

        if(response.getSuccess().equalsIgnoreCase("success")){
        Intent intent = new Intent(this , NewPassword.class);
        intent.putExtra("code" , et_code.getText().toString());
        startActivity(intent);
        }
        else
        {
            Toast.makeText(this, ""+response.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showErrorMessage(String e) {
        Toast.makeText(this, ""+e, Toast.LENGTH_LONG).show();

    }

    @Override
    public void showErrorInForgetPAssword() {

    }

    @Override
    public void showLoading() {
        progress_verify_code.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        progress_verify_code.setVisibility(View.INVISIBLE);

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }

}
