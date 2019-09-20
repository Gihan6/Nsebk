package com.RiyadSoftware.nsebkapp.Ui.Favourite;

import com.RiyadSoftware.nsebkapp.base.MvpView;
import com.RiyadSoftware.nsebkapp.data.models.AddFavResponse;
import com.RiyadSoftware.nsebkapp.data.models.LoginResponse;
import com.RiyadSoftware.nsebkapp.data.models.MyFavResponse;

public interface FavMvpView
        extends MvpView {

    void showListOfFav(MyFavResponse myFavResponse);
    void DealAddedToFav(AddFavResponse addFavResponse);
}
