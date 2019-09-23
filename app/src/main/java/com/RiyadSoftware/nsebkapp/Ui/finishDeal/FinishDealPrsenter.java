package com.RiyadSoftware.nsebkapp.Ui.finishDeal;

import androidx.annotation.NonNull;

import com.RiyadSoftware.nsebkapp.Ui.Home.HomeView;
import com.RiyadSoftware.nsebkapp.activities.FinishDeal;
import com.RiyadSoftware.nsebkapp.base.BasePresenter;
import com.RiyadSoftware.nsebkapp.data.DataManager;
import com.RiyadSoftware.nsebkapp.data.models.Finishdeal.FinishDealRequest;
import com.RiyadSoftware.nsebkapp.data.models.Finishdeal.FinishDealResponse;
import com.RiyadSoftware.nsebkapp.data.models.HomeModel;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FinishDealPrsenter extends BasePresenter<FinishDealView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public FinishDealPrsenter(DataManager dataManager) {
        mDataManager = dataManager;
    }


    public void getFinishDealData(FinishDealRequest request){
        getMvpView().showLoader();
        mDataManager.getFinishDealData(request)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<FinishDealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull FinishDealResponse response) {

                        getMvpView().getFinishDealData(response);

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
