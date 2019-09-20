package com.RiyadSoftware.nsebkapp.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.LongSparseArray;
import android.support.v7.app.AppCompatActivity;

import com.RiyadSoftware.nsebkapp.Application;
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
public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    private static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    private static final LongSparseArray<ConfigPersistentComponent>
            sComponentsMap = new LongSparseArray<>();

    private ActivityComponent mActivityComponent;
    private long mActivityId;


    public  void logout(){
        new SharedPrefDueDate(this).setUserLogged(null);
        Intent intent = new Intent(BaseActivity.this , MainActivity2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        mActivityId = savedInstanceState != null ?
                savedInstanceState.getLong(KEY_ACTIVITY_ID) : NEXT_ID.getAndIncrement();

        ConfigPersistentComponent configPersistentComponent = sComponentsMap.get(mActivityId, null);

        if (configPersistentComponent == null) {
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(Application.get(this).getComponent())
                    .build();
            sComponentsMap.put(mActivityId, configPersistentComponent);
        }
        mActivityComponent = configPersistentComponent.activityComponent(new ActivityModule(this));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_ACTIVITY_ID, mActivityId);
    }

    @Override
    protected void onDestroy() {
        if (!isChangingConfigurations()) {
            sComponentsMap.remove(mActivityId);
        }
        super.onDestroy();
    }

    @Override
    public void onStop() {
        Utils.hideKeyboard(this);
        super.onStop();
    }

    public ActivityComponent activityComponent() {
        return mActivityComponent;
    }

    public void showLoaderDialog() {
        Intents.showProgressDialog(DTDialog.newInstance(), "callback", this);
    }

    public void hideLoaderDialog() {
        Intents.hideProgressDialog("callback", this);
    }

    @Override
    public void showLoader() {
        showLoaderDialog();
    }

    @Override
    public void hideLoader() {
        hideLoaderDialog();
    }
}
