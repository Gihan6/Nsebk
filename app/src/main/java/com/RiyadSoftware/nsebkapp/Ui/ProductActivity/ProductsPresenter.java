package com.RiyadSoftware.nsebkapp.Ui.ProductActivity;

import com.RiyadSoftware.nsebkapp.Ui.ProductActivity.ProductSubView;
import com.RiyadSoftware.nsebkapp.base.BasePresenter;
import com.RiyadSoftware.nsebkapp.data.DataManager;
import com.RiyadSoftware.nsebkapp.data.models.AddFavRequest;
import com.RiyadSoftware.nsebkapp.data.models.AddFavResponse;
import com.RiyadSoftware.nsebkapp.data.models.CategoryProductResponse;
import com.RiyadSoftware.nsebkapp.data.models.CategoryProductsRequest;
import com.RiyadSoftware.nsebkapp.data.models.DealsResponseModel;
import com.RiyadSoftware.nsebkapp.data.models.OffersResponseModel;
import com.RiyadSoftware.nsebkapp.injection.ConfigPersistent;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


@ConfigPersistent
public class ProductsPresenter extends BasePresenter<ProductSubView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public ProductsPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(ProductSubView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }


    public void getProducts(CategoryProductsRequest request) {
        getMvpView().showLoader();
        mDataManager.getCatsProducts(request)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CategoryProductResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull CategoryProductResponse offersResponseModel) {

                        getMvpView().showData(offersResponseModel);

                        getMvpView().hideLoader();

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getMvpView().showError();
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void addToFav(AddFavRequest addFavRequest){
        getMvpView().showLoader();
        mDataManager.AddFav(addFavRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<AddFavResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull AddFavResponse homeModel) {

                        getMvpView().DealAddedToFav(homeModel);
                        getMvpView().hideLoader();

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getMvpView().showError();
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
