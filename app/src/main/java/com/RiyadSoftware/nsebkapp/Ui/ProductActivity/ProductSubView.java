package com.RiyadSoftware.nsebkapp.Ui.ProductActivity;

import com.RiyadSoftware.nsebkapp.base.MvpView;
import com.RiyadSoftware.nsebkapp.data.models.AddFavResponse;
import com.RiyadSoftware.nsebkapp.data.models.CategoryProductResponse;
import com.RiyadSoftware.nsebkapp.data.models.DealsResponseModel;
import com.RiyadSoftware.nsebkapp.data.models.OffersResponseModel;

import java.util.List;

public interface ProductSubView
        extends MvpView {

    public void showData(CategoryProductResponse offersResponseModel);
    void DealAddedToFav(AddFavResponse addFavResponse);

}
