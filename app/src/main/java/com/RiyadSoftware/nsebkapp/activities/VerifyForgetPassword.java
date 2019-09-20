package com.RiyadSoftware.nsebkapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.forgetPassword.ForgetPresenter;
import com.RiyadSoftware.nsebkapp.Ui.forgetPassword.ForgetView;
import com.RiyadSoftware.nsebkapp.base.BaseActivity;
import com.RiyadSoftware.nsebkapp.data.models.forgetPassword.VerifyCodeRequest;
import com.RiyadSoftware.nsebkapp.data.models.forgetPassword.VerifyCodeResponse;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VerifyForgetPassword extends BaseActivity implements ForgetView {

    @BindView(R.id.codeEtForgetPassword)
    EditText et_code;

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
        startActivity(new Intent(getApplicationContext(), NewPassword.class));
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
    public void navigateToNewPassword(VerifyCodeResponse response) {
        startActivity(new Intent(getApplicationContext(), NewPassword.class));
    }

    @Override
    public void showErrorInForgetPAssword() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoader() {

    }

    @Override
    public void hideLoader() {

    }
}
