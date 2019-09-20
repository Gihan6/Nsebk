package com.RiyadSoftware.nsebkapp.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.LongSparseArray;
import android.view.View;

import com.RiyadSoftware.nsebkapp.Application;
import com.RiyadSoftware.nsebkapp.activities.MainActivity;
import com.RiyadSoftware.nsebkapp.activities.MainActivity2;
import com.RiyadSoftware.nsebkapp.injection.component.ActivityComponent;
import com.RiyadSoftware.nsebkapp.injection.component.ConfigPersistentComponent;
import com.RiyadSoftware.nsebkapp.injection.component.DaggerConfigPersistentComponent;
import com.RiyadSoftware.nsebkapp.injection.module.ActivityModule;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;
import com.RiyadSoftware.nsebkapp.util.Utils;

import java.util.concurrent.atomic.AtomicLong;


/**
 * Abstract activity that every other Activity in this application must implement. It handles
 * creation of Dagger components and makes sure that instances of ConfigPersistentComponent survive
 * across configuration changes.
 */
public abstract class BaseFragment extends Fragment implements MvpView {

    private static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    private static final LongSparseArray<ConfigPersistentComponent>
            sComponentsMap = new LongSparseArray<>();

    private ActivityComponent mActivityComponent;
    private long mActivityId;
    public Context context;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    public  void logout(){
        new SharedPrefDueDate(getActivity()).setUserLogged(null);
        Intent intent = new Intent(getActivity() , MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        mActivityId = savedInstanceState != null ?
                savedInstanceState.getLong(KEY_ACTIVITY_ID) : NEXT_ID.getAndIncrement();

        ConfigPersistentComponent configPersistentComponent = sComponentsMap.get(mActivityId, null);

        if (configPersistentComponent == null) {

            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(Application.get(getActivity()).getComponent())
                    .build();
            sComponentsMap.put(mActivityId, configPersistentComponent);
        }
        mActivityComponent = configPersistentComponent.activityComponent(new
                ActivityModule(getActivity()));

    }

    public void showLoaderDialog() {
        Intents.showProgressDialog(DTDialog.newInstance(), "callback", this);
    }

    @Override
    public void showLoader() {
        showLoaderDialog();
    }

    @Override
    public void hideLoader() {
        hideLoaderDialog();
    }

    @Override
    public void onStop() {
        Utils.hideKeyboard((Activity) context);
        super.onStop();
    }

    public void hideLoaderDialog() {
        Intents.hideProgressDialog("callback", this);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putLong(KEY_ACTIVITY_ID, mActivityId);
//    }

//    @Override
//    protected void onDestroy() {
//        if (!isChangingConfigurations()) {
//            Timber.i("Clearing ConfigPersistentComponent id=%d", mActivityId);
//            sComponentsMap.remove(mActivityId);
//        }
//        super.onDestroy();
//    }

    public ActivityComponent activityComponent() {
        return mActivityComponent;
    }

}
