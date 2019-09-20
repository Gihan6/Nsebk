package com.RiyadSoftware.nsebkapp.Ui.Home;

import com.RiyadSoftware.nsebkapp.base.BasePresenter;
import com.RiyadSoftware.nsebkapp.data.DataManager;
import com.RiyadSoftware.nsebkapp.data.models.AddFavRequest;
import com.RiyadSoftware.nsebkapp.data.models.AddFavResponse;
import com.RiyadSoftware.nsebkapp.data.models.DealsResponseModel;
import com.RiyadSoftware.nsebkapp.data.models.HomeModel;
import com.RiyadSoftware.nsebkapp.data.models.HomeRequest;
import com.RiyadSoftware.nsebkapp.data.models.OffersResponseModel;
import com.RiyadSoftware.nsebkapp.data.models.currency.CurrencyRequest;
import com.RiyadSoftware.nsebkapp.data.models.currency.CurrencyResponse;
import com.RiyadSoftware.nsebkapp.injection.ConfigPersistent;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


@ConfigPersistent
public class HomePresenter extends BasePresenter<HomeView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public HomePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(HomeView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }


    public void getHomeData(HomeRequest homeRequest) {
        getMvpView().showLoader();
        mDataManager.getHomeData(homeRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<HomeModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull HomeModel homeModel) {

                        getMvpView().getHomeData(homeModel);

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
//    public void getOffers() {
//        getMvpView().showLoader();
//        mDataManager.getOffers()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Observer<OffersResponseModel>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        mDisposable = d;
//                    }
//
//                    @Override
//                    public void onNext(@NonNull OffersResponseModel offersResponseModel) {
//
//                        getMvpView().getOffers(offersResponseModel);
//
//                        getMvpView().hideLoader();
//
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        getMvpView().showError();
//                        getMvpView().hideLoader();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
//    public void getCurrentDeals() {
////        getMvpView().showLoader();
//        mDataManager.getCurrentDelas()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Observer<DealsResponseModel>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        mDisposable = d;
//                    }
//
//                    @Override
//                    public void onNext(@NonNull DealsResponseModel offersResponseModel) {
//
//                        getMvpView().getCurrentDeals(offersResponseModel);
//
//                        getMvpView().hideLoader();
//
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        getMvpView().showError();
//                        getMvpView().hideLoader();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
//    public void getPastDeals() {
////        getMvpView().showLoader();
//        mDataManager.getPastDelas()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Observer<DealsResponseModel>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        mDisposable = d;
//                    }
//
//                    @Override
//                    public void onNext(@NonNull DealsResponseModel offersResponseModel) {
//
//                        getMvpView().getPastDeals(offersResponseModel);
//
//                        getMvpView().hideLoader();
//
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        getMvpView().showError();
//                        getMvpView().hideLoader();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
//    public void getEndedDeals() {
////        getMvpView().showLoader();
//        mDataManager.getEndedDeals()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Observer<DealsResponseModel>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        mDisposable = d;
//                    }
//
//                    @Override
//                    public void onNext(@NonNull DealsResponseModel offersResponseModel) {
//
//                        getMvpView().getEndedDeals(offersResponseModel);
//
//                        getMvpView().hideLoader();
//
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        getMvpView().showError();
//                        getMvpView().hideLoader();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
//    public void getMydeals() {//getMvpView().showLoader();
//        mDataManager.getMyDeals()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Observer<DealsResponseModel>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        mDisposable = d;
//                    }
//
//                    @Override
//                    public void onNext(@NonNull DealsResponseModel offersResponseModel) {
//
//                        getMvpView().getMyDeals(offersResponseModel);
//
//                        getMvpView().hideLoader();
//
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        getMvpView().showError();
//                        getMvpView().hideLoader();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }

    public void getCurrenciesList(String token) {
        getMvpView().showLoader();
    final CurrencyRequest currencyRequest = new CurrencyRequest();
    currencyRequest.setToken(token);
        mDataManager.getCurrenciesList(currencyRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CurrencyResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull CurrencyResponse currencyResponse) {
                        getMvpView().currenciesList(currencyResponse);
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
