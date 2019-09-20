package com.RiyadSoftware.nsebkapp.Ui.Home;

import com.RiyadSoftware.nsebkapp.base.MvpView;
import com.RiyadSoftware.nsebkapp.data.models.AddFavResponse;
import com.RiyadSoftware.nsebkapp.data.models.DealsResponseModel;
import com.RiyadSoftware.nsebkapp.data.models.HomeModel;
import com.RiyadSoftware.nsebkapp.data.models.OffersResponseModel;
import com.RiyadSoftware.nsebkapp.data.models.currency.CurrencyResponse;

import java.util.List;

public interface HomeView  extends MvpView {
    void getHomeData(HomeModel homeModel);
    void DealAddedToFav(AddFavResponse addFavResponse);

    void currenciesList(CurrencyResponse data);
}
