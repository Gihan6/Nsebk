package com.RiyadSoftware.nsebkapp.Ui.Rewards;

import com.RiyadSoftware.nsebkapp.base.BasePresenter;
import com.RiyadSoftware.nsebkapp.data.DataManager;
import com.RiyadSoftware.nsebkapp.data.models.HomeRequest;
import com.RiyadSoftware.nsebkapp.data.models.OffersResponseModel;
import com.RiyadSoftware.nsebkapp.data.models.RewardsResponse;
import com.RiyadSoftware.nsebkapp.data.models.awards.ReplaceAwardRequest;
import com.RiyadSoftware.nsebkapp.data.models.awards.ReplaceAwardResponse;
import com.RiyadSoftware.nsebkapp.injection.ConfigPersistent;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


@ConfigPersistent
public class RewardsPresenter extends BasePresenter<RewardsSubView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public RewardsPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(RewardsSubView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }



    public void getRewards(String token) {
        getMvpView().showLoader();
        mDataManager.getRewards(new HomeRequest(token))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RewardsResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull RewardsResponse offersResponseModel) {

                        getMvpView().getRewards(offersResponseModel);

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
                        getMvpView().getReplaceAwardsResponse(replaceAwardResponse);
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
