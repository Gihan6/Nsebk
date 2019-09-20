package com.RiyadSoftware.nsebkapp.Ui.Register;

import com.RiyadSoftware.nsebkapp.base.MvpView;
import com.RiyadSoftware.nsebkapp.data.CountriesResponse;
import com.RiyadSoftware.nsebkapp.data.models.CitiesModel;
import com.RiyadSoftware.nsebkapp.data.models.RegisterResponse;
import com.RiyadSoftware.nsebkapp.data.models.VerifyResponse;

public interface RegisterView
        extends MvpView {

    public void navigateToMain(RegisterResponse response);
    public void showCountries(CountriesResponse countriesResponse);
    public void showCities(CitiesModel countriesResponse);
    public void afterVerify(VerifyResponse countriesResponse);
    public void showErrorInRegister(Throwable e);
}
