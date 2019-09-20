package com.RiyadSoftware.nsebkapp.Ui.EditProfile;

import com.RiyadSoftware.nsebkapp.base.BasePresenter;
import com.RiyadSoftware.nsebkapp.data.CountriesResponse;
import com.RiyadSoftware.nsebkapp.data.DataManager;
import com.RiyadSoftware.nsebkapp.data.models.CitiesModel;
import com.RiyadSoftware.nsebkapp.data.models.CityRequest;
import com.RiyadSoftware.nsebkapp.data.models.HomeRequest;
import com.RiyadSoftware.nsebkapp.data.models.LoginResponse;
import com.RiyadSoftware.nsebkapp.data.models.MyFavResponse;
import com.RiyadSoftware.nsebkapp.injection.ConfigPersistent;

import java.util.Map;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Body;


@ConfigPersistent
public class EditProfilePresenter extends BasePresenter<editProfileMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public EditProfilePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(editProfileMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
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


    public void editProfile(Map<String ,String> values) {
        getMvpView().showLoader();
        mDataManager.editProfile(values)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<EditprofileResponse
                        >() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull EditprofileResponse loginResponse) {
                        getMvpView().isEditDone(loginResponse);
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



}
