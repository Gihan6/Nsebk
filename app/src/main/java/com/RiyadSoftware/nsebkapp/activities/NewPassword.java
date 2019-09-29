package com.RiyadSoftware.nsebkapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.forgetPassword.NewPasswordPrsenter;
import com.RiyadSoftware.nsebkapp.Ui.forgetPassword.NewPasswordView;
import com.RiyadSoftware.nsebkapp.base.BaseActivity;
import com.RiyadSoftware.nsebkapp.data.models.forgetPassword.NewPasswordRequest;
import com.RiyadSoftware.nsebkapp.data.models.forgetPassword.NewPasswordResponse;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewPassword extends BaseActivity implements NewPasswordView {

    @BindView(R.id.et_password)
    EditText et_password;

    @BindView(R.id.et_passwordConfirm)
    EditText et_passwordConfirm;

    @BindView(R.id.error_txt_r_1)
    TextView error_txt_r_1;

    @BindView(R.id.pass_iv)
    ImageView pass_iv;

    @BindView(R.id.passConfLN)
    LinearLayout passConfLN;

    TextWatcher passwatcher;
    Boolean verify=false;

    @Inject
    NewPasswordPrsenter mNewPasswordPrsenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        ButterKnife.bind(this);
        activityComponent().inject(this);

        mNewPasswordPrsenter.attachView(this);

        passwatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!et_password.getText().toString().isEmpty() && !et_password.getText().toString().isEmpty()) {
                    if (editable.toString().length() >= 0) {
                        passConfLN.setVisibility(View.VISIBLE);
                        if (et_password.getText().toString().equals(et_passwordConfirm.getText().toString())) {
                            error_txt_r_1.setText(getString(R.string.valid_pass));
                            pass_iv.setImageResource(R.drawable.true_ic);
                            verify=true;
                        } else {
                            error_txt_r_1.setText(getString(R.string.regconpass));
                            pass_iv.setImageResource(R.drawable.error_icon);
                            verify=false;
                        }
                    } else {
                        passConfLN.setVisibility(View.INVISIBLE);
                    }
                }
            }
        };
        et_passwordConfirm.addTextChangedListener(passwatcher);


    }

    @OnClick(R.id.btn_sendNewPassword)
    void sendNewPassword() {
       if(et_password.getText().toString().length() == 0)
       {
           Toast.makeText(this, getString(R.string.enter_password), Toast.LENGTH_SHORT).show();
       }
        if(et_passwordConfirm.getText().toString().length() == 0)
        {
            Toast.makeText(this, getString(R.string.enter_confirm_password), Toast.LENGTH_SHORT).show();

        }


        else if (verify){
           if(!getIntent().getStringExtra("code").equals(null)) {
               NewPasswordRequest newPasswordRequest = new NewPasswordRequest();
               newPasswordRequest.setCode(getIntent().getStringExtra("code").toString());
                newPasswordRequest.setPassword(et_password.getText().toString());
               mNewPasswordPrsenter.sendNewPassword(newPasswordRequest);
           }

        }

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }


    @Override
    public void navigateToLogin(NewPasswordResponse response) {
        if(response.getSuccess().equalsIgnoreCase("success"))
        {

            Toast.makeText(getApplicationContext(), ""+response.getMessage(), Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), Login.class));
        }
        else
        {
            Toast.makeText(this, ""+response.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showErrorInRequest(String messageError) {

        Toast.makeText(this, ""+messageError, Toast.LENGTH_LONG).show();
    }
}
