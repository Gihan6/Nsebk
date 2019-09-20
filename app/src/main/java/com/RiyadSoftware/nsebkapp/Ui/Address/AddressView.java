package com.RiyadSoftware.nsebkapp.Ui.Address;

import com.RiyadSoftware.nsebkapp.base.MvpView;
import com.RiyadSoftware.nsebkapp.data.CountriesResponse;
import com.RiyadSoftware.nsebkapp.data.models.CitiesModel;
import com.RiyadSoftware.nsebkapp.data.models.LoginResponse;

public interface AddressView
        extends MvpView {

    public void afterAddressAdded(Addressresponse response);

    public void showCountries(CountriesResponse countriesResponse);

    public void showCities(CitiesModel countriesResponse);


}
