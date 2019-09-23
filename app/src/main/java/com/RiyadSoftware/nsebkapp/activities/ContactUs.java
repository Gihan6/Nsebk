package com.RiyadSoftware.nsebkapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.Contact.ContactMvpView;
import com.RiyadSoftware.nsebkapp.Ui.Contact.ContactPresenter;
import com.RiyadSoftware.nsebkapp.Ui.Contact.ContactRequest;
import com.RiyadSoftware.nsebkapp.Ui.Contact.ContactResponse;
import com.RiyadSoftware.nsebkapp.Ui.Terms.TermsResponse;
import com.RiyadSoftware.nsebkapp.base.BaseActivity;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactUs extends BaseActivity implements ContactMvpView {

    @Inject
    ContactPresenter mPresenter;

    @BindView(R.id.name_et)
    EditText name_et;
    @BindView(R.id.phone_et)
    TextView phone_et;

    @BindView(R.id.email_et)
    EditText email_et;


    @BindView(R.id.title_et)
    EditText title_et;


    @BindView(R.id.message_et)
    EditText message_et;

    @BindView(R.id.email_tv)
    TextView email_tv;

    @BindView(R.id.phone_tv_1)
    TextView phone_tv_1;

    @BindView(R.id.phone_tv_2)
    TextView phone_tv_2;


    TermsResponse mResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        activityComponent().inject(this);
        mPresenter.attachView(this);
        ButterKnife.bind(this);
        SharedPrefDueDate pref = new SharedPrefDueDate(this);

        if (pref.getUserLogged() != null) {
            phone_et.setText(pref.getUserLogged().getMobile());
            email_et.setText(pref.getUserLogged().getEmail());
            email_tv.setText(pref.getUserLogged().getEmail());

        }

        mPresenter.getAbout();
    }

    private void openLink(String url) {
        try {
            startActivity(Intent.createChooser(new Intent(Intent.ACTION_VIEW, Uri.parse(url)),
                    "Select"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.back_my_interest)
    void back_buy() {
        finish();
    }


    @OnClick(R.id.sendBtn)
    void sendBtn() {
        if (name_et.getText().toString().trim().isEmpty() ||
                phone_et.getText().toString().trim().isEmpty() ||
                email_et.getText().toString().trim().isEmpty() ||
                title_et.getText().toString().trim().isEmpty() ||
                message_et.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, getString(R.string.complete_data), Toast.LENGTH_SHORT).show();
            return;
        }
        if (phone_et.getText().toString().length() != 10) {
            Toast.makeText(this, getString(R.string.phone_lenghth), Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email_et.getText().toString()).matches()) {
            Toast.makeText(this, getString(R.string.enter_valid_email), Toast.LENGTH_LONG).show();
            return;
        }
        mPresenter.sendContactData(new ContactRequest(name_et.getText().toString().trim(),
                phone_et.getText().toString().trim(),
                title_et.getText().toString().trim(),
                email_et.getText().toString().trim(),
                message_et.getText().toString().trim()));
    }

    @OnClick(R.id.insta_iv)
    void insta_iv() {
        if (mResponse != null)
            openLink(mResponse.getContacts().getInstagram());
//        openLink("https://instagram.com/naseebk.app?utm_source=ig_profile_share&igshid=p0bfbwbc608a");
    }

    @OnClick(R.id.twitter_iv)
    void twiitter() {
        if (mResponse != null)
            openLink(mResponse.getContacts().getTwitter());

//        openLink("https://twitter.com/Naseebkapp");
    }

    @OnClick(R.id.snapchat_iv)
    void snapchat_iv() {
        if (mResponse != null)
            openLink(mResponse.getContacts().getSnapchat());

//        openLink("https://www.snapchat.com/add/naseebk.app");
    }

    @OnClick(R.id.whatsapp_iv)
    void whatsapp_iv() {
        if (mResponse != null)
            openLink(mResponse.getContacts().getWhatsapp());
    }

    @OnClick(R.id.youtube_iv)
    void youtube_iv() {
        if (mResponse != null)
            openLink(mResponse.getContacts().getYoutube());
    }

    @OnClick({R.id.phone_tv_1})
    void callNumber() {
        if (mResponse != null)
            dialPhoneNumber(mResponse.getContacts().getPhone1());
    }

    @OnClick({R.id.phone_tv_2})
    void callNumber2() {
        if (mResponse != null)
            dialPhoneNumber(mResponse.getContacts().getPhone2());
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @OnClick(R.id.email_tv)
    void email_tv() {
        if (mResponse != null) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setType("text/plain");
//            intent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
//            intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
//            intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");
            startActivity(Intent.createChooser(intent, "Send Email"));

        }
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void afterSendData(ContactResponse response) {
        if (response != null) {
            if (response.getSuccess().toLowerCase().equals("success")) {
                Toast.makeText(this, getString(R.string.message_sent), Toast.LENGTH_SHORT).show();
                onBackPressed();
            } else {
                Toast.makeText(this, "" + response.getErrors().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void showTerms(TermsResponse response) {
        mResponse = response;
//        phone_tv_1.setText(response.getContacts().get);
//        email_tv.setText(mResponse.getContacts().getEmail());
        phone_tv_2.setText(mResponse.getContacts().getPhone2());
        phone_tv_1.setText(mResponse.getContacts().getPhone1());
    }
}
