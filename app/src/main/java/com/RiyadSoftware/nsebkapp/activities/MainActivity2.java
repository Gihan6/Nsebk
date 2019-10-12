package com.RiyadSoftware.nsebkapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.Main.MainPresenter;
import com.RiyadSoftware.nsebkapp.Ui.Main.MainView;
import com.RiyadSoftware.nsebkapp.adapters.NavMenuAdapter;
import com.RiyadSoftware.nsebkapp.base.BaseActivity;
import com.RiyadSoftware.nsebkapp.data.models.HomeRequest;
import com.RiyadSoftware.nsebkapp.fragments.MainFragment;
import com.RiyadSoftware.nsebkapp.fragments.MyFavorites;
import com.RiyadSoftware.nsebkapp.fragments.PackageFragment;
import com.RiyadSoftware.nsebkapp.fragments.Rewards;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity2 extends BaseActivity implements MainView,
        NavMenuAdapter.UserActions {


    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;
    @BindView(R.id.nav_view)
    NavigationView nav_view;
    @BindView(R.id.nav_menu_recyclerview)
    RecyclerView nav_menu_recyclerview;
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;
    @BindView(R.id.text_nav_header_welcome)
    TextView txtProfileName;
    @BindView(R.id.text_nav_points_num)
    TextView txtPointsNum;
    @BindView(R.id.text_nav_tickets_num)
    TextView txtTicketsNum;
    @BindView(R.id.radio_ar)
    RadioButton radioAr;
    @BindView(R.id.radio_en)
    RadioButton radioEn;
    //init the recycler adapters
    NavMenuAdapter navMenuAdapter;
    //init the prefrence
    SharedPrefDueDate pref;
    @Inject
    MainPresenter mainPresenter;
    //init the views

    private ImageView menuIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        ButterKnife.bind(this);
        //init the pref ;
        pref = new SharedPrefDueDate(this);


        if (pref.getLanguage().equals("ar")) {
            radioAr.setChecked(true);
            radioEn.setChecked(false);
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

        } else {
            radioEn.setChecked(true);
            radioAr.setChecked(false);
            //nav_view.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

        activityComponent().inject(this);
        mainPresenter.attachView(this);

        setLanguageFromNavMenu();
//        BottomNavigationViewHelper.removeShiftMode(bottomNavigation);
        if (pref.getUserLogged().getUser_name() != null)
            txtProfileName.setText(getString(R.string.welcome) + "  " + pref.getUserLogged().getUser_name());

        int cc = pref.getUserPointsFromPk() + Integer.parseInt(pref.getUserLogged().getPoints());
        if (pref.getUserLogged().getPoints() != null)
            txtPointsNum.setText("" + cc);

        if (pref.getUserLogged().getCoupons() != null)
            txtTicketsNum.setText("" + pref.getUserLogged().getCoupons());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        navMenuAdapter = new NavMenuAdapter(this, drawer_layout, this);
        nav_menu_recyclerview.setLayoutManager(layoutManager);
        nav_menu_recyclerview.setNestedScrollingEnabled(false);
        nav_menu_recyclerview.setAdapter(navMenuAdapter);

        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(getString(R.string.navmain),
                R.drawable.ic_home_white, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(getString(R.string.credits),
                R.drawable.ic_dollar_white, R.color.colorPrimary);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(getString(R.string.rewards),
                R.drawable.ic_reward_white, R.color.colorPrimary);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(getString(R.string.favorite),
                R.drawable.ic_favorite_border_white, R.color.colorPrimary);

// Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);


// Set background color
        bottomNavigation.setDefaultBackgroundColor(getResources().getColor(R.color.colorPrimary));

// Disable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(false);

// Change colors
//        bottomNavigation.setColoredModeColors(getResources().getColor(R.color.colorPrimaryDark),getResources().getColor(R.color.white));
//        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
//        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));

// Force to tint the drawable (useful for font with icon for example)
//        bottomNavigation.setForceTint(true);
        bottomNavigation.setAccentColor(getResources().getColor(R.color.white));
        bottomNavigation.setInactiveColor(getResources().getColor(R.color.white));

// Display color under navigation bar (API 21+)
// Don't forget these lines in your style-v21
// <item name="android:windowTranslucentNavigation">true</item>
// <item name="android:fitsSystemWindows">true</item>
        bottomNavigation.setTranslucentNavigationEnabled(false);
//        Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.frutiger_fint);
//        bottomNavigation.setTitleTypeface(boldTypeface);
        bottomNavigation.setTitleTypeface(typeface);
// Manage titles
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

// Use colored navigation with circle reveal effect
//        bottomNavigation.setColored(true);


// Set current item programmatically
        bottomNavigation.setCurrentItem(0);

//        Enable / disable item & set disable color

//        bottomNavigation.enableItemAtPosition(2);
//        bottomNavigation.disableItemAtPosition(2);
//        bottomNavigation.setItemDisableColor(Color.parseColor("#3A000000"));


// Set Default Fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_container, new MainFragment()).commit();
// Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (position == 0) {
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.main_activity_container, new MainFragment()).commit();
                } else if (position == 1) {
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.main_activity_container, new PackageFragment()).commit();
                } else if (position == 2) {
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.main_activity_container, new Rewards()).commit();
                } else if (position == 3) {
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.main_activity_container, new MyFavorites()).commit();
                }

                return true;
            }
        });

        menuIV = findViewById(R.id.menuIV);

        menuIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawer_layout.isDrawerVisible(GravityCompat.END)) {
                    drawer_layout.closeDrawer(GravityCompat.END);
                } else {
                    drawer_layout.openDrawer(GravityCompat.END);
                }
            }
        });

    }

    public void englishFlow() {

        pref.setEnglish();
        Intent i = new Intent(this, MainActivity2.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);

    }

    public void arabicFlow() {
        pref.setArabic();
        Intent i = new Intent(this, MainActivity2.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);


    }


    private void setLanguageFromNavMenu() {

        radioAr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    arabicFlow();
                }

            }
        });


        radioEn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    englishFlow();
                }

            }
        });

    }


//    @Override
//    public void onBackPressed() {
//        if (getSupportFragmentManager().findFragmentById(R.id.main_activity_container) instanceof MainFragment) {
//            finishAffinity();
//        } else {
//
////            getSupportFragmentManager().beginTransaction().
////                    replace(R.id.main_activity_container,
////                            new MainFragment()).commit();
//            bottomNavigation.setCurrentItem(0);
//
//        }
//    }

    @Override
    public void showEmpty() {

    }

    @Override
    protected void onResume() {
        super.onResume();


        txtPointsNum.setText("" + pref.getUserPointsFromPk());
        //txtTicketsNum.setText("" + pref.getUserCouponsFromPk());

    }

    @Override
    public void showError() {

    }

    @Override
    public void userLogout() {
        mainPresenter.logout(new HomeRequest(pref.getUserLogged().getRemember_token()));
    }

    @Override
    public void afterLogout() {
        pref.setUserLogged(null);
        pref.clear();
        finish();
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);

        // Toast.makeText(getApplicationContext(),getString(R.string.logout),Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        int cc = pref.getUserPointsFromPk() + Integer.parseInt(pref.getUserLogged().getPoints());
        if (pref.getUserLogged().getPoints() != null)
            txtPointsNum.setText("" + cc);

        if (pref.getUserLogged().getCoupons() != null)
            txtTicketsNum.setText("" + pref.getUserLogged().getCoupons());
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.areYouWantToExit))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}

