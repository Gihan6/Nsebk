package com.RiyadSoftware.nsebkapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;

import com.RiyadSoftware.nsebkapp.data.models.LoginResponse;
import com.RiyadSoftware.nsebkapp.data.models.RegisterResponse;
import com.RiyadSoftware.nsebkapp.data.models.currency.CurrencyResponse;
import com.google.gson.Gson;

import java.util.Locale;

import javax.inject.Inject;

public class SharedPrefDueDate {
    // LogCat tag
    private static String TAG = SharedPrefDueDate.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;
    SharedPreferences prefUpdates;

    Editor editor;
    Editor editorUpdates;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "NesbkApp";
    private static final String PREF_NAME_updates = "NesbkAppUpdates";

    private static final String PERSON_ID_CODE = "PERSON_ID";
    private static final String USER_OBJECT = "user_object";


    public SharedPrefDueDate(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        prefUpdates = _context.getSharedPreferences(PREF_NAME_updates, PRIVATE_MODE);

        editor = pref.edit();
        editorUpdates = prefUpdates.edit();
    }


    public void setEnglish() {
        Locale locale = new Locale("en");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        _context.getResources().updateConfiguration(config, _context.getResources().getDisplayMetrics());
        editor.putString("lan", "en");
        editor.apply();

    }
    public void setSARCurrency() {
         // sar  == 2
        editor.putString("currency", "2");
        editor.apply();

    }

    public void saveCurrencyResponse(CurrencyResponse currencyResponse) {
        Gson gson = new Gson();
        String jsonObject = gson.toJson(currencyResponse);
        editor.putString("response_currency", jsonObject);
        editor.commit();

    }



    public CurrencyResponse retrieveCurrencyResponse(){
        String json = pref.getString("response_currency", "");
        Gson gson=new Gson();
        CurrencyResponse currencyResponse=gson.fromJson(json, CurrencyResponse.class);
        return  currencyResponse;
    }

    public void setDOLLARCurrency() {
        //dollar = 1
        editor.putString("currency","1" );
        editor.apply();

    }


    public void setArabic() {
        Locale locale = new Locale("ar");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        _context.getResources().updateConfiguration(config, _context.getResources().getDisplayMetrics());

        LocaleManager.setLocale(_context,"ar");
        editor.putString("lan", "ar");
        editor.apply();

    }


    /**
     * here to get the user id  from the shared prefrence
     *
     * @return the user id
     */
    public String getLanguage() {
        return pref.getString("lan", "en");
    }

    public String getLanguage__first() {
        return pref.getString("lan", "null");
    }

    public String getCurrency__first() {
        return pref.getString("currency", "0");
    }
    public String getCurrency() {
        return pref.getString("currency", "2");
    }


    //todo here to save the user id

    public void setPersonId(String numSub) {

        editor.putString(PERSON_ID_CODE, numSub);
        // commit changes
        editor.commit();

    }

    public void setUserLogged(String user) {

        editor.putString(USER_OBJECT, user);
        // commit changes
        editor.commit();

    }
    public void setUserPointsFromPk(String pointsFromPk) {

        editorUpdates.putString("points_pk", pointsFromPk);
        // commit changes
        editorUpdates.commit();

    }
    public int getUserPointsFromPk() {


        return Integer.parseInt(prefUpdates.getString("points_pk", null));

        // commit changes

    }

    public void setUserCouponsFromPk(String couponsFromPk) {

        editorUpdates.putString("coupons_pk", couponsFromPk);
        // commit changes
        editorUpdates.commit();

    }

    public int getUserCouponsFromPk() {

        return Integer.parseInt(prefUpdates.getString("coupons_pk", null));
        // commit changes

    }

    public void clear(){
        editor.clear();
        editor.commit();
    }

    public LoginResponse.Data getUserLogged() {
        RegisterResponse user = new Gson().fromJson(
                pref.getString(USER_OBJECT, null), RegisterResponse.class
        );
        if (user != null)
            return user.getData();

        return null;
    }

//    public String getpersonID() {
//        return pref.getString(PERSON_ID_CODE, "45");
//    }


}
