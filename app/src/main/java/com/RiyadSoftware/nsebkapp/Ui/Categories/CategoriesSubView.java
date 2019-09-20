package com.RiyadSoftware.nsebkapp.Ui.Categories;

import com.RiyadSoftware.nsebkapp.base.MvpView;
import com.RiyadSoftware.nsebkapp.data.models.CategoriesResponce;
import com.RiyadSoftware.nsebkapp.data.models.OffersResponseModel;
import com.RiyadSoftware.nsebkapp.data.models.PackageResponse;

public interface CategoriesSubView
        extends MvpView {

    void getCategories(CategoriesResponce categoriesResponce);
}
