package com.RiyadSoftware.nsebkapp.Ui.EditProfile;

import com.RiyadSoftware.nsebkapp.base.MvpView;
import com.RiyadSoftware.nsebkapp.data.CountriesResponse;
import com.RiyadSoftware.nsebkapp.data.models.CitiesModel;
import com.RiyadSoftware.nsebkapp.data.models.LoginResponse;
import com.RiyadSoftware.nsebkapp.data.models.MyFavResponse;

public interface editProfileMvpView
        extends MvpView {

    void isEditDone(EditprofileResponse loginResponse);
    public void showCountries(CountriesResponse countriesResponse);
    public void showCities(CitiesModel countriesResponse);

}
