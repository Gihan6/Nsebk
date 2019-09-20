package com.RiyadSoftware.nsebkapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.Address.AddressPresenter;
import com.RiyadSoftware.nsebkapp.Ui.Address.AddressRequest;
import com.RiyadSoftware.nsebkapp.Ui.Address.AddressView;
import com.RiyadSoftware.nsebkapp.Ui.Address.Addressresponse;
import com.RiyadSoftware.nsebkapp.adapters.CustomArrayAdapter;
import com.RiyadSoftware.nsebkapp.base.BaseActivity;
import com.RiyadSoftware.nsebkapp.data.CountriesResponse;
import com.RiyadSoftware.nsebkapp.data.models.CitiesModel;
import com.RiyadSoftware.nsebkapp.data.models.CityRequest;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAddresses extends BaseActivity implements AddressView {


    @Inject
    AddressPresenter mAddressPresenter;

    @BindView(R.id.address_details)
    EditText address_details;
    @BindView(R.id.address_details2)
    EditText address_details2;
    @BindView(R.id.street_name)
    EditText street_name;
    @BindView(R.id.zip_code)
    EditText zipEditText;
    @BindView(R.id.phone_number)
    EditText phone_number;
    @BindView(R.id.countrySP)
    Spinner countrySP;
    @BindView(R.id.city_spinner)
    Spinner cities_sp;
    @BindView(R.id.homeNumberEt)
    EditText homeNumberEt;

    List<CountriesResponse.InnerDatum> coInnerData = new ArrayList<>();
    List<CitiesModel.Datum> citiesData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_addresses);
        ButterKnife.bind(this);
        activityComponent().inject(this);
        mAddressPresenter.attachView(this);


        mAddressPresenter.getCountries();
    }

    @OnClick(R.id.back_my_addresses)
    void back() {
        finish();
    }

    @Override
    public void afterAddressAdded(Addressresponse response) {
        if (response.getErrors() == null && response.getSuccess().equalsIgnoreCase("success")) {
            Toast.makeText(this, "" + response.getMessage(), Toast.LENGTH_SHORT).show();

        } else {
//            String error = "";
//            for (LoginResponse.error errorClass:response.getErrors())
//                error+=errorClass.getMessage()+"\n";

            Toast.makeText(this, "" + response.getErrors(), Toast.LENGTH_SHORT).show();
//            showErrorInLogin();
        }
    }

    @Override
    public void showCountries(CountriesResponse countriesResponse) {
        coInnerData = countriesResponse.getData();

        countrySP.setAdapter(new CustomArrayAdapter(this, R.layout.custom_spinner_item
                , coInnerData));


        countrySP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mAddressPresenter.getCities(new CityRequest(coInnerData.get(i).getId()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

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

        } else {
            citiesData.clear();
            cities_sp.setAdapter(new ArrayAdapter<Object>(this,
                    R.layout.spinner_center_item,
                    new String[]{}));
        }

    }


    @OnClick(R.id.insrtBtn)
    public void insertAddress() {
        if (
                homeNumberEt.getText().toString().trim().isEmpty() ||
                        street_name.getText().toString().trim().isEmpty() ||
                        address_details.getText().toString().trim().isEmpty() ||
                        zipEditText.getText().toString().trim().isEmpty() ||
                        phone_number.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, getString(R.string.error_in_data), Toast.LENGTH_SHORT).show();
            return;
        }

        if (phone_number.getText().toString().trim().length() != 10) {
            Toast.makeText(this, getString(R.string.phone_lenghth), Toast.LENGTH_SHORT).show();
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

        AddressRequest request = new AddressRequest();
        request.setToken(new SharedPrefDueDate(this).getUserLogged().getRemember_token());
        request.setCity_id(String.valueOf(citiesData.get(cities_sp.getSelectedItemPosition()).getCity_id()));
        request.setCountry_id(String.valueOf(coInnerData.get(countrySP.getSelectedItemPosition()).getId()));
        request.setZip_code(zipEditText.getText().toString().trim());
        request.setMobile(phone_number.getText().toString().trim());
        request.setDisc(address_details.getText().toString().trim());
//        request.setDisc2(address_details2.getText().toString().trim());
        request.setTitle(street_name.getText().toString().trim() + "-" + homeNumberEt.getText().toString().trim());

        mAddressPresenter.addAddress(request);
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }
}
