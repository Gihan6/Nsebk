package com.RiyadSoftware.nsebkapp;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.RiyadSoftware.nsebkapp.injection.component.ApplicationComponent;
import com.RiyadSoftware.nsebkapp.injection.component.DaggerApplicationComponent;
import com.RiyadSoftware.nsebkapp.injection.module.ApplicationModule;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Application extends android.app.Application {

    ApplicationComponent mApplicationComponent;
    static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
//        printHashKey();
    }

    public static Application get(Context context) {
        return (Application) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
    public static Context getAppContext() {
        return context;
    }

//    public void printHashKey() {
//        // Add code to print out the key hash
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(this.getPackageName(),
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                //life saver
//                //https://stackoverflow.com/questions/44355452/google-play-app-signing-key-hash/44448437#44448437
////                byte[] sha={
////                        (byte)0xCF,(byte)0x2C,(byte)0xC3,(byte)0x04,(byte)0xA7,(byte)0xC5,(byte)0x76,
////                        (byte)0x7C,(byte)0x9D,(byte)0x08,(byte)0x25,(byte)0x6D,(byte)0x9E,(byte)0x17,
////                        (byte)0x25,(byte)0xF5,(byte)0x79,(byte)0xBF,(byte)0xF9,(byte)0x3F
////
////                };
//
//
//                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.NO_WRAP));
////                Log.e("KeyHash:", Base64.encodeToString(sha, Base64.NO_WRAP));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }
//    }

}

