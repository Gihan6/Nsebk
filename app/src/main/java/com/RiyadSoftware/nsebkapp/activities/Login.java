package com.RiyadSoftware.nsebkapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.Login.LoginPresenter;
import com.RiyadSoftware.nsebkapp.Ui.Login.LoginView;
import com.RiyadSoftware.nsebkapp.base.BaseActivity;
import com.RiyadSoftware.nsebkapp.data.models.LoginRequest;
import com.RiyadSoftware.nsebkapp.data.models.LoginResponse;
import com.RiyadSoftware.nsebkapp.util.Constant;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends BaseActivity implements LoginView {

    //init the views

    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.emailET)
    EditText emailET;
    @BindView(R.id.passET)
    EditText passET;


    //the shared prefrence
    SharedPrefDueDate pref;

    @Inject
    LoginPresenter mloLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        activityComponent().inject(this);
        mloLoginPresenter.attachView(this);
        pref = new SharedPrefDueDate(this);

        TextView textView = findViewById(R.id.login_forget_password);
        SpannableString content = new SpannableString(textView.getText().toString());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        textView.setText(content);

    }

    @OnClick(R.id.login_forget_password)
    void openForgetPassword() {
        startActivity(new Intent(this, ResetPassword.class));
    }


    // here to add the login request

    @OnClick(R.id.login_button)
    void login() {

        if (validate())
            FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, new OnSuccessListener<InstanceIdResult>() {
                @Override
                public void onSuccess(InstanceIdResult instanceIdResult) {
                    String newToken = instanceIdResult.getToken();
                    mloLoginPresenter.login(new LoginRequest(emailET.getText().toString(), passET.getText().toString(),
                            Constant.DEVICE_TYPE, newToken));
                }
            });

    }


    // the login

    /**
     * here to send  the data from the server
     */


    /**
     * here to extract the data from the server and get it
     *
     * @param response the json object is returned from the server
     * @throws JSONException the json exception
     */
    @SuppressLint("SetTextI18n")
    private void extractJson(JSONObject response) throws JSONException {


        JSONArray data = response.getJSONArray("data");

        if (data.length() == 0) {
            Toast.makeText(Login.this, getString(R.string.wrong_login), Toast.LENGTH_LONG).show();
        } else {

            JSONObject object = data.getJSONObject(0);

            pref.setPersonId(object.getString("id"));

        }

//            Intent i = new Intent(LoginActivity.this, MainActivity.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(i);


//            Toast.makeText(Login.this, getString(R.string.wrong_login), Toast.LENGTH_LONG).show();

    }


    // the validate
    private boolean validate() {

        if (emailET.getText().toString().isEmpty() || passET.getText().toString().isEmpty()) {
            Toast.makeText(Login.this, getString(R.string.complete_data), Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    @OnClick(R.id.back_login_1)
    void back() {
        finish();
    }

    @Override
    public void navigateToMain(LoginResponse response) {

        if (response.getErrors() == null && response.getSuccess().equalsIgnoreCase("success")) {
            response.getData().setVerified(true);
            pref.setUserLogged(new Gson().toJson(response));

            Intent i = new Intent(Login.this, MainActivity2.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.putExtra("password", passET.getText().toString());
            startActivity(i);
        } else {
//            String error = "";
//            for (LoginResponse.error errorClass:response.getErrors())
//                error+=errorClass.getMessage()+"\n";

            Toast.makeText(this, "" + response.getErrors(), Toast.LENGTH_SHORT).show();
//            showErrorInLogin();
        }
    }

    @Override
    public void showErrorInLogin() {
//        Toast.makeText(this, getString(R.string.error_login), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }
}
