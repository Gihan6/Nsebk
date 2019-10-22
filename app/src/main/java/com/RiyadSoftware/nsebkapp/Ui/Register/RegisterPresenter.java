package com.RiyadSoftware.nsebkapp.Ui.Register;

import androidx.annotation.NonNull;

import com.RiyadSoftware.nsebkapp.base.BasePresenter;
import com.RiyadSoftware.nsebkapp.data.CountriesResponse;
import com.RiyadSoftware.nsebkapp.data.DataManager;
import com.RiyadSoftware.nsebkapp.data.models.CitiesModel;
import com.RiyadSoftware.nsebkapp.data.models.CityRequest;
import com.RiyadSoftware.nsebkapp.data.models.RegisterRequest;
import com.RiyadSoftware.nsebkapp.data.models.RegisterResponse;
import com.RiyadSoftware.nsebkapp.data.models.VerifyResponse;
import com.RiyadSoftware.nsebkapp.injection.ConfigPersistent;
import com.RiyadSoftware.nsebkapp.models.JopModel;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


@ConfigPersistent
public class RegisterPresenter extends BasePresenter<RegisterView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public RegisterPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(RegisterView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }


    public void register(RegisterRequest registerRequest) {
        getMvpView().showLoader();
        mDataManager.register(registerRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RegisterResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull RegisterResponse loginResponse) {
                        getMvpView().navigateToMain(loginResponse);
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getMvpView().showErrorInRegister(e);
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void verifyUsr(Map<String, String> body) {
        getMvpView().showLoader();
        mDataManager.verifyUser(body)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<VerifyResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull VerifyResponse countriesResponse) {
                        getMvpView().afterVerify(countriesResponse);
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getMvpView().showErrorInRegister(e);
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
                        getMvpView().showErrorInRegister(e);
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

    public void getJop() {
        mDataManager.getJop()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<JopModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<JopModel> response) {
                        getMvpView().showJops(response);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getMvpView().showErrorInRegister(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getAge() {
        mDataManager.getAge()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<JopModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<JopModel> response) {
                        getMvpView().showAge(response);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getMvpView().showErrorInRegister(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
