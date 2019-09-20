package com.RiyadSoftware.nsebkapp.Ui.Address;

import com.RiyadSoftware.nsebkapp.base.BasePresenter;
import com.RiyadSoftware.nsebkapp.data.CountriesResponse;
import com.RiyadSoftware.nsebkapp.data.DataManager;
import com.RiyadSoftware.nsebkapp.data.models.CitiesModel;
import com.RiyadSoftware.nsebkapp.data.models.CityRequest;
import com.RiyadSoftware.nsebkapp.data.models.LoginRequest;
import com.RiyadSoftware.nsebkapp.data.models.LoginResponse;
import com.RiyadSoftware.nsebkapp.injection.ConfigPersistent;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


@ConfigPersistent
public class AddressPresenter extends BasePresenter<AddressView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public AddressPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(AddressView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }


    public void addAddress(AddressRequest addressRequest) {
        getMvpView().showLoader();
        mDataManager.AddAddress(addressRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Addressresponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull Addressresponse loginResponse) {
                        getMvpView().afterAddressAdded(loginResponse);
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getCountries() {
        getMvpView().showLoader();
        mDataManager.getCountries()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CountriesResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull CountriesResponse countriesResponse) {
                        getMvpView().showCountries(countriesResponse);
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getCities(CityRequest cityRequest) {
        getMvpView().showLoader();
        mDataManager.getCities(cityRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CitiesModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull CitiesModel countriesResponse) {
                        getMvpView().showCities(countriesResponse);
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getMvpView().showCities(null);
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
