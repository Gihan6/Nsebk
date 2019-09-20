package com.RiyadSoftware.nsebkapp.injection.component;


import android.app.Application;
import android.content.Context;

import com.RiyadSoftware.nsebkapp.data.DataManager;
import com.RiyadSoftware.nsebkapp.data.remote.RibotsService;
import com.RiyadSoftware.nsebkapp.injection.ApplicationContext;
import com.RiyadSoftware.nsebkapp.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {


    @ApplicationContext
    Context context();
    Application application();
    RibotsService ribotsService();
//    PreferencesHelper preferencesHelper();
//    DatabaseHelper databaseHelper();
    DataManager dataManager();
//    RxEventBus eventBus();

}
