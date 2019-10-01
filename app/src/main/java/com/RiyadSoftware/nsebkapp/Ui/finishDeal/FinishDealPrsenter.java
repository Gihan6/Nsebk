package com.RiyadSoftware.nsebkapp.Ui.finishDeal;

import androidx.annotation.NonNull;

import com.RiyadSoftware.nsebkapp.Ui.AddTicket.AddTicketMvpView;
import com.RiyadSoftware.nsebkapp.Ui.Home.HomeView;
import com.RiyadSoftware.nsebkapp.activities.FinishDeal;
import com.RiyadSoftware.nsebkapp.base.BasePresenter;
import com.RiyadSoftware.nsebkapp.data.DataManager;
import com.RiyadSoftware.nsebkapp.data.models.Finishdeal.FinishDealRequest;
import com.RiyadSoftware.nsebkapp.data.models.Finishdeal.FinishDealResponse;
import com.RiyadSoftware.nsebkapp.data.models.HomeModel;
import com.RiyadSoftware.nsebkapp.data.models.awards.ReplaceAwardRequest;
import com.RiyadSoftware.nsebkapp.data.models.awards.ReplaceAwardResponse;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FinishDealPrsenter extends BasePresenter<FinishDealView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Override
    public void attachView(FinishDealView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Inject
    public FinishDealPrsenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    public void replaceAward(String token , int awardId) {
        getMvpView().showLoader();
        mDataManager.getReplaceAwards(new ReplaceAwardRequest(token , awardId))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ReplaceAwardResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull ReplaceAwardResponse replaceAwardResponse) {
                        getMvpView().getFinishDealData(replaceAwardResponse);
                        getMvpView().hideLoader();

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getMvpView().showError();
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onComplete() {
                        getMvpView().hideLoader();
                    }
                });
    }

}
