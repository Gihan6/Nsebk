package com.RiyadSoftware.nsebkapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.Reset.ForgetResponse;
import com.RiyadSoftware.nsebkapp.Ui.Reset.ResetPresenter;
import com.RiyadSoftware.nsebkapp.Ui.Reset.ResetSubView;
import com.RiyadSoftware.nsebkapp.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPassword extends BaseActivity implements ResetSubView {

    @BindView(R.id.email_et)
    EditText emailEditText;

    @Inject
    ResetPresenter mResetPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
        activityComponent().inject(this);
        mResetPresenter.attachView(this);
    }

    @OnClick(R.id.back_reset_password)
    void back() {
        finish();
    }


    @OnClick(R.id.reset_password_button)
    public void resetPassword() {
        if (!Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText().toString().trim()).matches()) {
            Toast.makeText(this, getString(R.string.enter_valid_email), Toast.LENGTH_LONG).show();
            return;
        }
        Map val = new HashMap();
        val.put("email", emailEditText.getText().toString().trim());
        mResetPresenter.sendMail(val);
    }

    @Override
    public void showMessage(ForgetResponse offersResponseModel) {
        if (offersResponseModel.getSuccess().equalsIgnoreCase("success")) {

            startActivity(new Intent(this,VerifyForgetPassword.class));
            Toast.makeText(this, "" + offersResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "" + offersResponseModel.getErrors(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }
}
