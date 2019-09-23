package com.RiyadSoftware.nsebkapp.Ui.AddTicket;

import com.RiyadSoftware.nsebkapp.base.BasePresenter;
import com.RiyadSoftware.nsebkapp.data.DataManager;
import com.RiyadSoftware.nsebkapp.data.models.AddTicketRequest;
import com.RiyadSoftware.nsebkapp.data.models.AddTicketResponse;
import com.RiyadSoftware.nsebkapp.data.models.DealDetailsRequest;
import com.RiyadSoftware.nsebkapp.data.models.DealDetailsResponse;
import com.RiyadSoftware.nsebkapp.injection.ConfigPersistent;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


@ConfigPersistent
public class AddTicketPresenter extends BasePresenter<AddTicketMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public AddTicketPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(AddTicketMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }


    public void AddTicket(AddTicketRequest addTicketRequest) {
        getMvpView().showLoader();
        mDataManager.AddTicket(addTicketRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<AddTicketResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull AddTicketResponse homeModel) {

                        getMvpView().addedToTicket(homeModel);

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

    public void getDealDetails(DealDetailsRequest dealDetailsRequest) {
        getMvpView().showLoader();
        mDataManager.getDealDetails(dealDetailsRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<DealDetailsResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull DealDetailsResponse homeModel) {

                        getMvpView().showDetails(homeModel);
                        getMvpView().hideLoader();

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getMvpView().showError();
                        getMvpView().hideLoader();
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
