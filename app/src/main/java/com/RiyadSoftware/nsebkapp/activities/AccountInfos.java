package com.RiyadSoftware.nsebkapp.activities;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.EditProfile.EditProfilePresenter;
import com.RiyadSoftware.nsebkapp.Ui.EditProfile.EditprofileResponse;
import com.RiyadSoftware.nsebkapp.Ui.EditProfile.editProfileMvpView;
import com.RiyadSoftware.nsebkapp.adapters.CustomArrayAdapter;
import com.RiyadSoftware.nsebkapp.base.BaseActivity;
import com.RiyadSoftware.nsebkapp.data.CountriesResponse;
import com.RiyadSoftware.nsebkapp.data.models.CitiesModel;
import com.RiyadSoftware.nsebkapp.data.models.CityRequest;
import com.RiyadSoftware.nsebkapp.data.models.LoginResponse;
import com.RiyadSoftware.nsebkapp.data.models.RegisterResponse;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountInfos extends BaseActivity implements editProfileMvpView {

    Intent GalIntent;
    Uri uri;
    @BindView(R.id.nameTV)
    EditText nameTV;
    @BindView(R.id.id)
    TextView id;
    @BindView(R.id.ticket_tv)
    TextView ticket_tv;
    @BindView(R.id.points_tv)
    TextView points_tv;
    @BindView(R.id.emailTV)
    EditText emailTV;
    @BindView(R.id.passwordTv)
    EditText passwordTv;
//    @BindView(R.id.countryTV)
//    TextView countryTV;

    String imageEncoded;
    //    @BindView(R.id.cityTV)
//    TextView cityTV;
    List<CountriesResponse.InnerDatum> coInnerData = new ArrayList<>();
    List<CitiesModel.Datum> citiesData = new ArrayList<>();

    @BindView(R.id.phoneTV)
    EditText phoneTV;

    @BindView(R.id.view_iv)
    ImageView view_iv;

    @BindView(R.id.countrySP)
    Spinner countrySP;

    @BindView(R.id.cities_sp)
    Spinner cities_sp;

    //    @BindView(R.id.nameTV)TextView nameTV;
    SharedPrefDueDate pref;

    @Inject
    EditProfilePresenter mEditProfilePresenter;

    public static String convert(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }

    public static Bitmap convert(String base64Str) throws IllegalArgumentException {
        byte[] decodedBytes = Base64.decode(
                base64Str.substring(base64Str.indexOf(",") + 1),
                Base64.DEFAULT
        );

        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_infos);
        ButterKnife.bind(this);

        activityComponent().inject(this);
        mEditProfilePresenter.attachView(this);
        pref = new SharedPrefDueDate(this);
//        Log.e("aaaaa",pref.getpersonID());
//

        nameTV.setText(pref.getUserLogged().getName());
        phoneTV.setText(pref.getUserLogged().getMobile());
        emailTV.setText(pref.getUserLogged().getEmail());
//        countryTV.setText(pref.getUserLogged().getJob());
//        cityTV.setText(pref.getUserLogged().getCity_name());
        points_tv.setText(pref.getUserLogged().getPoints());
        ticket_tv.setText(pref.getUserLogged().getCoupons());
        id.setText("" + pref.getUserLogged().getId());

        mEditProfilePresenter.getCountries();

        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.logo);
        options.fallback(R.drawable.logo);
        Glide.with(this).load(pref.getUserLogged().getImage()).apply(options).into(view_iv);
    }

    @OnClick(R.id.back_account_info)
    void back() {
        finish();
    }

    @OnClick(R.id.login_button)
    void login_button() {
        Map<String, String> val = new HashMap<>();
        val.put("token", new SharedPrefDueDate(this).getUserLogged().getRemember_token());
        if (imageEncoded != null)
            val.put("profile_pic", imageEncoded);

        if (!Patterns.EMAIL_ADDRESS.matcher(emailTV.getText().toString()).matches()) {
            Toast.makeText(AccountInfos.this, getString(R.string.enter_valid_email), Toast.LENGTH_LONG).show();
            return;
        }
        if (phoneTV.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.complete_data), Toast.LENGTH_LONG).show();
            return;
        }

        if (coInnerData.isEmpty()) {
            Toast.makeText(this, getString(R.string.select_country_fist), Toast.LENGTH_SHORT).show();
            return;
        }

        if (citiesData.isEmpty()) {
            Toast.makeText(this, getString(R.string.select_city_fist), Toast.LENGTH_SHORT).show();
            return;
        }

        if (!passwordTv.getText().toString().trim().isEmpty()) {
            if (passwordTv.getText().toString().trim().length() < 6) {
                Toast.makeText(this, getString(R.string.pass_), Toast.LENGTH_SHORT).show();
                return;
            } else {
                val.put("password", passwordTv.getText().toString());
            }
        }
        val.put("name", nameTV.getText().toString().trim());
        val.put("email", emailTV.getText().toString().trim());
        val.put("mobile", phoneTV.getText().toString().trim());
        val.put("city_id", String.valueOf(citiesData.get(cities_sp.getSelectedItemPosition()).getCity_id()));
        val.put("country_id", String.valueOf(coInnerData.get(countrySP.getSelectedItemPosition()).getId()));


        mEditProfilePresenter.editProfile(val);
    }

    private void changeViewStatus(boolean isEditable) {
        emailTV.setFocusable(isEditable);
        emailTV.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
        emailTV.setClickable(false); // user navigates with wheel and selects widget

        nameTV.setFocusable(isEditable);
        emailTV.setFocusable(isEditable);
        emailTV.setFocusable(isEditable);
        emailTV.setFocusable(isEditable);
    }

    @OnClick(R.id.image)
    void chooseImage() {
        if (ActivityCompat.checkSelfPermission(AccountInfos.this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(AccountInfos.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AccountInfos.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        } else {
//            openGallery();
            getImageFromGallery();

        }


    }

    public String encodeTobase64(Bitmap image) {
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        return imageEncoded;
    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    private void imageCropFunction() {

        try {
            Intent CropIntent = new Intent("com.android.camera.action.CROP");

            CropIntent.setDataAndType(uri, "image/*");

            CropIntent.putExtra("crop", "true");
            CropIntent.putExtra("outputX", 200);
            CropIntent.putExtra("outputY", 200);
            CropIntent.putExtra("aspectX", 3);
            CropIntent.putExtra("aspectY", 3);
            CropIntent.putExtra("scaleUpIfNeeded", true);
            CropIntent.putExtra("return-data", true);

            startActivityForResult(CropIntent, 1);

        } catch (ActivityNotFoundException e) {

        }
    }

    private void getImageFromGallery() {
        GalIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(Intent.createChooser(GalIntent, " Select image from gallery "), 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK) {
            imageCropFunction();
        } else if (requestCode == 2) {
            if (data != null) {
                Glide.with(this).clear(view_iv);
                uri = data.getData();
                imageCropFunction();
            }
        } else if (requestCode == 1) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                Bitmap bitmap = bundle.getParcelable("data");
                imageEncoded = convert(bitmap);
//                Log.e("imageEncoded", imageEncoded);

                Bitmap bitmap1 = convert(imageEncoded);
                view_iv.setImageBitmap(bitmap1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery();
                } else {
                    //do something like displaying a message that he didn`t allow the app to access gallery and you wont be able to let him select from gallery
                }
                break;
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(Intent.createChooser(intent, " Select image from gallery "), 1);
    }

    @Override
    public void isEditDone(EditprofileResponse loginResponse) {
        if (loginResponse.getSuccess().equalsIgnoreCase("success")) {
            Toast.makeText(this, "" + loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
            LoginResponse loginResponse1 = new LoginResponse();
            loginResponse.getUser().setVerified(true);
            loginResponse1.setData(loginResponse.getUser());
            pref.setUserLogged(new Gson().toJson(loginResponse1));
            recreate();
        } else if (loginResponse.getErrors() != null) {
            String errorAll = "";
            for (RegisterResponse.Error error : loginResponse.getErrors()) {
                errorAll += error.getMessage() + "\n";
            }

            Toast.makeText(this, "" + errorAll, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showCountries(CountriesResponse countriesResponse) {
//        ArrayAdapter countryAdapter = ArrayAdapter.createFromResource(this,
//                R.array.gavor_array, android.R.layout.simple_spinner_item);
//
//        countryAdapter.setDropDownViewResource(R.layout.spinner_center_item);
//        countrySP.setAdapter(countryAdapter);
//
//        String d[] = new String[countriesResponse.getInnerData().size()];
//        coInnerData = countriesResponse.getInnerData();
//        for (int i = 0; i < coInnerData.size(); i++) {
//            if (pref.getLanguage().equals("en")) {
//                d[i] = coInnerData.get(i).getC_name_en();
//            } else {
//                d[i] = coInnerData.get(i).getC_name_ar();
//            }
//        }


//        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(Register.this,
//                R.layout.spinner_center_item,
//                d);
//        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_center_item); // The drop down view
        coInnerData = countriesResponse.getData();

        countrySP.setAdapter(new CustomArrayAdapter(this, R.layout.custom_spinner_item
                , coInnerData));


        countrySP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mEditProfilePresenter.getCities(new CityRequest(coInnerData.get(i).getId()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        Log.e("CityModel", "" + new Gson().toJson(pref.getUserLogged()));

        for (CountriesResponse.InnerDatum innerDatum : coInnerData) {
            if (innerDatum.getId() == pref.getUserLogged().getCountry_id()) {
                Log.e("NNNN", "-->" + innerDatum.getId());
                countrySP.setSelection(coInnerData.indexOf(innerDatum));
            }
        }
    }

    @Override
    public void showCities(CitiesModel countriesResponse) {
        if (countriesResponse == null) {
            citiesData.clear();
            cities_sp.setAdapter(new ArrayAdapter<Object>(this,
                    R.layout.spinner_center_item,
                    new String[]{}));
        } else if (countriesResponse.getSuccess().equalsIgnoreCase("success")) {
            citiesData = countriesResponse.getData();

            String[] d = new String[countriesResponse.getData().size()];
            for (int i = 0; i < citiesData.size(); i++) {
                d[i] = citiesData.get(i).getCity_name();
            }

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this,
                    R.layout.spinner_center_item,
                    d);
            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_center_item); // The drop down view
            cities_sp.setAdapter(spinnerArrayAdapter);

            Log.e("City", "" + pref.getUserLogged().getCity_id());
            for (CitiesModel.Datum innerDatum : citiesData) {
                if (innerDatum.getCity_id() == pref.getUserLogged().getCity_id()) {
                    Log.e("City11", "" + innerDatum.getCity_id());
                    cities_sp.setSelection(citiesData.indexOf(innerDatum));
                }
            }
        } else {
            citiesData.clear();
            cities_sp.setAdapter(new ArrayAdapter<Object>(this,
                    R.layout.spinner_center_item,
                    new String[]{}));
        }
    }

}
