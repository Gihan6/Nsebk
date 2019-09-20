package com.RiyadSoftware.nsebkapp.injection.component;

import com.RiyadSoftware.nsebkapp.Ui.Register.VerifyActivity;
import com.RiyadSoftware.nsebkapp.activities.AboutUs;
import com.RiyadSoftware.nsebkapp.activities.AccountInfos;
import com.RiyadSoftware.nsebkapp.activities.Buy;
import com.RiyadSoftware.nsebkapp.activities.Conditions;
import com.RiyadSoftware.nsebkapp.activities.ContactUs;
import com.RiyadSoftware.nsebkapp.activities.Login;
import com.RiyadSoftware.nsebkapp.activities.MainActivity2;
import com.RiyadSoftware.nsebkapp.activities.MainCategory;
import com.RiyadSoftware.nsebkapp.activities.MyAddresses;
import com.RiyadSoftware.nsebkapp.activities.MyDeals;
import com.RiyadSoftware.nsebkapp.activities.MyInterests;
import com.RiyadSoftware.nsebkapp.activities.NewPassword;
import com.RiyadSoftware.nsebkapp.activities.Privacy;
import com.RiyadSoftware.nsebkapp.activities.ProductsSubActivity;
import com.RiyadSoftware.nsebkapp.activities.Register;
import com.RiyadSoftware.nsebkapp.activities.ResetPassword;
import com.RiyadSoftware.nsebkapp.activities.ShippingDates;
import com.RiyadSoftware.nsebkapp.activities.Shipping_buy;
import com.RiyadSoftware.nsebkapp.activities.SubCategActivity;
import com.RiyadSoftware.nsebkapp.activities.VerifyForgetPassword;
import com.RiyadSoftware.nsebkapp.fragments.MainFragment;
import com.RiyadSoftware.nsebkapp.fragments.MyFavorites;
import com.RiyadSoftware.nsebkapp.fragments.PackageFragment;
import com.RiyadSoftware.nsebkapp.fragments.Rewards;
import com.RiyadSoftware.nsebkapp.injection.PerActivity;
import com.RiyadSoftware.nsebkapp.injection.module.ActivityModule;

import dagger.Subcomponent;


/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(Register register);

    void inject(Shipping_buy shipping_buy);

    void inject(Privacy register);

    void inject(ResetPassword register);

    void inject(MyAddresses register);

    void inject(SubCategActivity register);

    void inject(MyDeals register);

    void inject(VerifyActivity register);

    void inject(ContactUs register);

    void inject(Conditions register);

    void inject(Buy buy);

    void inject(MyFavorites mainFragment);

    void inject(MainFragment mainFragment);

    void inject(AboutUs mainFragment);

    void inject(MainActivity2 mainFragment);

    void inject(AccountInfos mainFragment);

    void inject(ShippingDates mainFragment);

    void inject(ProductsSubActivity mainFragment);

    void inject(Rewards Rewards);

    void inject(MyInterests Rewards);

    void inject(PackageFragment Rewards);

    void inject(MainCategory Rewards);

    void inject(Login Rewards);

    void inject(VerifyForgetPassword verifyForgetPassword);

    void inject(NewPassword newPassword);

}
