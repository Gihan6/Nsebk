package com.RiyadSoftware.nsebkapp.base;//package com.mecl.ui.base;
//
//import android.app.Activity;
//import android.app.NotificationManager;
//import android.content.Context;
//import android.content.ContextWrapper;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.content.res.Configuration;
//import android.content.res.XmlResourceParser;
//import android.graphics.Bitmap;
//import android.graphics.Rect;
//import android.graphics.drawable.Drawable;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.annotation.ArrayRes;
//import android.support.annotation.ColorRes;
//import android.support.annotation.IntegerRes;
//import android.support.annotation.NonNull;
//import android.support.annotation.StringRes;
//import android.support.design.widget.TextInputEditText;
//import android.support.design.widget.TextInputLayout;
//import android.support.v4.app.Fragment;
//import android.support.v4.content.ContextCompat;
//import android.support.v4.content.LocalBroadcastManager;
//import android.support.v4.util.Pair;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.text.InputFilter;
//import android.text.Selection;
//import android.text.Spanned;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.Menu;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.inputmethod.InputMethodManager;
//import android.webkit.MimeTypeMap;
//import android.webkit.URLUtil;
//import android.widget.EditText;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import org.xmlpull.v1.XmlPullParser;
//
//import java.math.RoundingMode;
//import java.text.DecimalFormat;
//import java.text.DecimalFormatSymbols;
//import java.text.NumberFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Locale;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//
///**
// * Created by cl-macmini-108 on 12/10/16.
// */
//public class GeneralFunction {
//
//
//    public static int CART_ITEMS = 0;
//
//    /**
//     * Checks Connection availability
//     *
//     * @param context
//     * @return True if the device has internet connection and false otherwise
//     */
//    public static boolean canConnect(Context context) {
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetworkInfo = connectivityManager
//                .getActiveNetworkInfo();
//        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
//    }
//
//    public static void itemAddedToCart() {
//        ++CART_ITEMS;
//    }
//
//    public static void itemRemovedFromCart() {
//        --CART_ITEMS;
//    }
//
//    public static int getCartItemsNumber() {
//        return CART_ITEMS;
//    }
//
//    /**
//     * Ensures that an object reference passed as a parameter to the calling method is not null.
//     *
//     * @param reference an object reference
//     * @return the non-null reference that was validated
//     * @throws NullPointerException if {@code reference} is null
//     */
//    public static <T> T checkNotNull(T reference) {
//        if (reference == null) {
//            throw new NullPointerException();
//        }
//        return reference;
//    }
//
//    /*******************************
//     * Find A View and Cast It
//     ***************************************/
//    public static <T> T findViewByIdAndCast(Activity activity, int id) {
//        return (T) activity.findViewById(id);
//    }
//
//    public static <T> T findViewByIdAndCast(View activity, int id) {
//        return (T) activity.findViewById(id);
//    }
//
//    /***************************************
//     * Hides the soft keyboard
//     ********************************************/
//    public static void hideSoftKeyboard(Object object) {
//        if (object == null)
//            return;
//        try {
//            Activity context = getActivity(object);
//            if (context.getCurrentFocus() != null) {
//                InputMethodManager inputMethodManager = (InputMethodManager) context.
//                        getSystemService(Context.INPUT_METHOD_SERVICE);
//                inputMethodManager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(),
//                        0);
//            }
//        } catch (Exception e) {
//            LogUtil.printStacktrace(e);
//        }
//    }
//
//    /***************************************
//     * Show Toast
//     ********************************************/
//    public static void showToast(String message, Context context) {
//        if (null == context)
//            return;
//        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
//    }
//
//    /*******************************
//     * Check to get Activity Instance from Object of framgent
//     * Activityt
//     ***************************************/
//    public static Activity getActivity(Object object) {
//        if (object instanceof Activity)
//            return ((Activity) object);
//        else if (object instanceof Fragment)
//            return ((Fragment) object).getActivity();
//        else
//            return null;
//    }
//
//    public static Activity getActivity(View view) {
//        Context context = view.getContext();
//        while (context instanceof ContextWrapper) {
//            if (context instanceof Activity) {
//                return (Activity) context;
//            }
//            context = ((ContextWrapper) context).getBaseContext();
//        }
//        return null;
//    }
//
//    /***************************************
//     * Mime type of file
//     ********************************************/
//    static String getMimeType(String url) {
//        String type = null;
//        String fileTypeMime = MimeTypeMap.getFileExtensionFromUrl(url);
//        if (fileTypeMime != null)
//            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileTypeMime);
//        return type;
//    }
//
//    /***************************************
//     * get extension of file
//     ********************************************/
//    static String getFileType(String url) {
//        return MimeTypeMap.getFileExtensionFromUrl(url);
//    }
//
//    /***************************************
//     * File Name from a URL
//     ********************************************/
//    public static String getFileName(String url) {
//        return URLUtil.guessFileName(url, null, null);
//    }
//
//    /***************************************
//     * Get Text from a view
//     ********************************************/
//    public static String getTextFromView(TextView textView) {
//        return textView.getText().toString().trim();
//    }
//
//    public static String getTextFromView(TextInputLayout textView) {
//        return getTextFromView(textView.getEditText());
//    }
//
//    public static boolean isEmpty(TextInputLayout textView) {
//        return isEmpty(getTextFromView(textView));
//    }
//
//    public static boolean isEmpty(TextInputEditText textView) {
//        return isEmpty(getTextFromView(textView));
//    }
//
//    public static boolean isEmpty(EditText textView) {
//        return isEmpty(getTextFromView(textView));
//    }
//
//    public static boolean isEmpty(String textView) {
//        return TextUtils.isEmpty(textView);
//    }
//
//    public static <T extends TextView> int getLength(T editText) {
//        return editText.getText().toString().length();
//    }
//
//    public static int getLength(String editText) {
//        return editText.length();
//    }
//
//    private static int getLength(TextInputEditText editText) {
//        return editText.getText().toString().trim().length();
//    }
//
//    public static int getLength(TextInputLayout editText) {
//        return getLength((TextInputEditText) editText.getEditText());
//    }
//
//    public static void setText(final TextInputEditText textInputEditText, final String string) {
//        setEditText(textInputEditText, string);
//    }
//
//    public static void setText(final EditTextRoman textInputEditText, final String string) {
//        setEditText(textInputEditText, string);
//    }
//
//    public static void setEditText(final EditText textInputEditText, final String string) {
//        textInputEditText.setText(string);
//        textInputEditText.setSelection(string.length());
//        Selection.setSelection(textInputEditText.getText(), textInputEditText.getText().length());
//    }
//
//    public static void setText(final TextInputLayout textInputEditText, final String string) {
//        setText((TextInputEditText) textInputEditText.getEditText(), string);
//    }
//
//    /***************************************Filters with editext***************
//     * *****************************/
//    public static void setAlphaBeticFilters(final EditText editText) {
//        final StringBuilder blockCharacterSet = new StringBuilder();
//        blockCharacterSet.append("!@#$%^&*()_+=-{}[]:;'<>,.?/'\\\"");
//        InputFilter filter = new InputFilter() {
//            public CharSequence filter(CharSequence source, int start, int end,
//                                       Spanned dest, int dstart, int dend) {
//                if (source != null && blockCharacterSet.toString().contains(("" + source)))
//                    return "";
//
//                for (int index = start; index < end; index++) {
//                    int type = Character.getType(source.charAt(index));
//                    if (type == Character.SURROGATE) {
//                        return "";
//                    }
//                }
//                return null;
//            }
//        };
//
//        editText.setFilters(new InputFilter[]{filter});
//    }
//
//    /***************************************
//     * String Operations
//     ********************************************/
//    public static String covertFistChat_everyWordUpper(final String str) {
//        StringBuffer stringbf = new StringBuffer();
//        Matcher m = Pattern.compile("([a-z])([a-z]*)",
//                Pattern.CASE_INSENSITIVE).matcher(str);
//        while (m.find()) {
//            m.appendReplacement(stringbf, m.group(1).toUpperCase() + m.group(2).toLowerCase());
//        }
//        return m.appendTail(stringbf).toString();
//    }
//
//    static String getNumber(final String editText) {
//        return editText.replaceAll("\\s+|-", "");
//    }
//
//    /***************************************
//     * Get Drawable
//     ********************************************/
//    public static Drawable getDrawable(Object context, int drawable) {
//        return ContextCompat.getDrawable(getActivity(context), drawable);
//    }
//
//    /***************************************
//     * Get Color
//     ********************************************/
//    public static int getColor(Object context, @ColorRes int drawable) {
//        return getColor(getActivity(context), drawable);
//    }
//
//    public static int getColor(Context context, @ColorRes int drawable) {
//        //khalws added this
//        if (context == null) {
//            return 1;
//        }
//        return ContextCompat.getColor(context, drawable);
//    }
//
//    /***************************************
//     * Visibility Gone & On
//     ********************************************/
//    public static void setVisibilityGone(View view) {
//        if (null != view)
//            view.setVisibility(View.GONE);
//    }
//
//    public static void setVisibilityGone(ViewGroup view) {
//        if (null != view)
//            view.setVisibility(View.GONE);
//    }
//
//    public static void setVisibilityOn(View view) {
//        if (null != view)
//            view.setVisibility(View.VISIBLE);
//    }
//
//    public static void setVisibilityOn(ViewGroup view) {
//        if (null != view)
//            view.setVisibility(View.VISIBLE);
//    }
//
//
//    /***************************************
//     * Get Class Name
//     ********************************************/
//
//    private static String getClassName(Class classa) {
//       /* String c=classa.getSimpleName();
//        LogUtil.e("ClassName:-",c+"");*/
//        return classa.getSimpleName();
//    }
//
//    static String getClassName(Fragment classa) {
//        return getClassName(classa.getClass());
//    }
//
//
//    /***************************************
//     * Shared PReference Instance
//     ********************************************/
//    public static StorageManager getStorageManager(Object context) {
//        Activity activity = getActivity(context);
//        return new StorageManager(activity, getString(R.string.app_name_universal, activity));
//    }
//
//    public static int getApplicationLang(Context context) {
//        Context mContext = GeneralFunction.getActivity(context);
//        int lang = GeneralFunction.getStorageManager(mContext)
//                .getValue(CallBackConstants.LANGUAGE_SELECTION, "0")
//                .equals(mContext.getString(R.string.english)) ? 0 : 1;
//        return lang;
//    }
//
//    /***************************************
//     * Get Device Info
//     ********************************************/
//    public static String getDeviceName() {
//        return Build.MANUFACTURER;
//    }
//
//    public static String getDeviceLanguage() {
//        return Locale.getDefault().getLanguage().toUpperCase();
//    }
//  /*  Locale.getDefault().getLanguage()       ---> en
//    Locale.getDefault().getISO3Language()   ---> eng
//    Locale.getDefault().getCountry()        ---> US
//    Locale.getDefault().getISO3Country()    ---> USA
//    Locale.getDefault().getDisplayCountry() ---> United States
//    Locale.getDefault().getDisplayName()    ---> English (United States)
//    Locale.getDefault().toString()          ---> en_US
//    Locale.getDefault().getDisplayLanguage()---> English*/
//
//    public static String getAppVersion(Object context) {
//        int versionCode = 0;
//        try {
//            versionCode = getActivity(context).getPackageManager()
//                    .getPackageInfo(getActivity(context).getPackageName(), 0).versionCode;
//        } catch (PackageManager.NameNotFoundException e) {
//            LogUtil.e("version errr", "" + e.getLocalizedMessage());
//        }
//        return String.valueOf(versionCode);
//    }
//
//    public static void checkForAccessToken(final Activity context) {
//        if (context == null)
//            return;
//        AlertDialog.Builder builder = new AlertDialog.Builder(context.
//                findViewById(android.R.id.content).getContext(), R.style.AlertDialogStyle);
//        builder.setMessage(getString(R.string.s_error_acessDenied, context))
//                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        //triggerLogoutEvent(context);
//                        dialog.dismiss();
//                        Bundle bundle1 = new Bundle();
//                        bundle1.putBoolean("logout", true);
//                        GeneralFunction.getStorageManager(context).setIsGuestUser(true);
//                        clearStorage(context);
//                        Intents.removeAllAndStartNewActivity(Splash.class, context, bundle1);
//                    }
//                });
//        AlertDialogs.showAlertDialog(context, builder, false);
//    }
//
//
//    /**
//     * trigger logout analytic event
//     *
//     * @param context context
//     */
//    public static void triggerLogoutEvent(Context context) {
//        MoEHelper.getInstance(context).logoutUser();
//        Analytics analytics = new Analytics(context);
//        analytics.addToAnalyticPayload(Analytics.Constants.IS_LOGGED_IN, false);
//        addCommonAttributesToEvent(analytics, context);
//        analytics.emmitAnalyticEvent(Analytics.Constants.SIGN_OUT);
//    }
//
//
//    public static void logoutUser(Activity context) {
//        GeneralFunction.clearDescriptionUrl();
//        if (Paper.book().exist("TOKEN")) {
//            Paper.book().delete("TOKEN");
//        }
//        FuguConfig.getInstance().clearFuguData(context);
//        GeneralFunction.triggerLogoutEvent(context);
//        GeneralFunction.triggerAdjustEvent(AdjustAnalytics.EventTokens.SIGN_OUT);
//        GeneralFunction.clearStorage(context);
//        Bundle bundle1 = new Bundle();
//        bundle1.putBoolean("logout", true);
//        GeneralFunction.getStorageManager(context).setIsGuestUser(true);
//        Intents.removeAllAndStartNewActivity(Splash.class, context, bundle1);
//    }
//
//    /**
//     * trigger log in analytic event
//     *
//     * @param context context
//     */
//    public static void triggerLogInEvent(Context context) {
//        MoEHelper.getInstance(context).setUniqueId(CPApplicaiton.getmProfile().getData().getId());
//        Analytics analytics = new Analytics(context);
//        analytics.addToAnalyticPayload(Analytics.Constants.IS_LOGGED_IN, true);
//        addCommonAttributesToEvent(analytics, context);
//        analytics.emmitAnalyticEvent(Analytics.Constants.LOG_IN);
//    }
//
//    /**
//     * trigger log in analytic event
//     *
//     * @param context context
//     */
//    public static void triggerSignUpEvent(Context context) {
//        MoEHelper.getInstance(context).setUniqueId(CPApplicaiton.getmProfile().getData().getId());
//        MoEHelper.getInstance(context).setUserAttribute("signedUpOn", new Date());
//        Analytics analytics = new Analytics(context);
//        addCommonAttributesToEvent(analytics, context);
//        analytics.emmitAnalyticEvent(Analytics.Constants.SIGN_UP);
//    }
//
//    /**
//     * trigger specified analytic event
//     *
//     * @param context
//     * @param eventName
//     */
//    public static void triggerAnalyticEvent(Context context, String eventName) {
//        Analytics analytics = new Analytics(context);
//        addCommonAttributesToEvent(analytics, context);
//        analytics.emmitAnalyticEvent(eventName);
//    }
//
//
//    /**
//     * trigger specified analytic event
//     *
//     * @param context
//     * @param eventName
//     */
//    public static void triggerCancelEvent(Context context, String eventName, boolean isPaymentDone) {
//        Analytics analytics = new Analytics(context);
//        analytics.addToAnalyticPayload(Analytics.Constants.CANCELED_AFTER_PAYMENT, isPaymentDone);
//        addCommonAttributesToEvent(analytics, context);
//        analytics.emmitAnalyticEvent(eventName);
//    }
//
//
//    /**
//     * to add common user attribute for analytic event
//     *
//     * @param analytics Analytics class object
//     */
//    private static void addCommonAttributesToEvent(final Analytics analytics, final Context context) {
//        analytics.addToAnalyticPayload(Analytics.Constants.CUSTOMER_ID, CPApplicaiton.getmProfile().getData().getEmail());
//        String name = CPApplicaiton.getmProfile().getData().getFirstName() + " " + CPApplicaiton.getmProfile().getData().getLastName();
//        analytics.addToAnalyticPayload(Analytics.Constants.CUSTOMER_NAME, name);
//        analytics.addToAnalyticPayload(Analytics.Constants.USER_ID, CPApplicaiton.getmProfile().getData().getId());
//        analytics.addToAnalyticPayload(Analytics.Constants.DATE, UtilityDate.getDate());
//        analytics.addToAnalyticPayload(Analytics.Constants.EMAIL, CPApplicaiton.getmProfile().getData().getEmail());
//        analytics.addToAnalyticPayload(Analytics.Constants.PHONE_NUMBER, CPApplicaiton.getmProfile().getData().getPhoneNumber());
//        StorageManager storageManager = getStorageManager(context);
//        analytics.addToAnalyticPayload(Analytics.Constants.IS_GUEST_USER, storageManager.getIsGuestUSer());
//    }
//
//    /**
//     * trigger orderConfirmation  analytic event
//     *
//     * @param context          context
//     * @param pharmacyAssigned true if pharmacy accepted order
//     */
//    public static void triggerOrderConfirmationEvent(final Context context, final boolean pharmacyAssigned) {
//        Analytics analytics = new Analytics(context);
//        addCommonAttributesToEvent(analytics, context);
//        analytics.addToAnalyticPayload(Analytics.Constants.IS_PHARMACY_ASSIGNED, pharmacyAssigned);
//        analytics.emmitAnalyticEvent(Analytics.Constants.ORDER_CONFIRMATION);
//    }
//
//    /**
//     * to trigger adjust analytic event
//     *
//     * @param event token (identifier) of event
//     */
//    public static void triggerAdjustEvent(String event) {
//        Adjust.trackEvent(new AdjustEvent(event));
//    }
//
//
//    public static  void makeTextViewSelectedLeftSide(Context context , TextView selectedTV){
//        selectedTV.setTextColor(context.getResources().getColor(R.color.color_white));
//        if (CPApplicaiton.getmProfile().getData().getLanguageType() ==
//                EnumNumericConstants.LanguageType.ARABIC.getCode()) {
//            selectedTV.setBackground(context.getResources().getDrawable(R.drawable.filter_shape_filled_right));
//        } else {
//            selectedTV.setBackground(context.getResources().getDrawable(R.drawable.filter_shape_filled_left));
//        }
//    }
//    public static void makeTextViewSelectedRightSide(Context context ,TextView selectedTV){
//        selectedTV.setTextColor(context.getResources().getColor(R.color.color_white));
//        if (CPApplicaiton.getmProfile().getData().getLanguageType() ==
//                EnumNumericConstants.LanguageType.ARABIC.getCode()) {
//            selectedTV.setBackground(context.getResources().getDrawable(R.drawable.filter_shape_filled_left));
//        } else {
//            selectedTV.setBackground(context.getResources().getDrawable(R.drawable.filter_shape_filled_right));
//        }
//    }
//
//    public static boolean appInstalledOrNot(Context context , String uri) {
//        PackageManager pm = context.getPackageManager();
//        try {
//            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
//            return true;
//        } catch (PackageManager.NameNotFoundException e) {
//        }
//
//        return false;
//    }
//
//    public static void clearStorage(final Context context) {
//        if (null != CPApplicaiton.getItemList() && !CPApplicaiton.getItemList().isEmpty())
//            CPApplicaiton.getItemList().clear();
//        if (null != CPApplicaiton.getDescriptionUrl() && !CPApplicaiton.getDescriptionUrl().
//                isEmpty()) {
//            CPApplicaiton.getDescriptionUrl().clear();
////            Paper.book().delete("descriptionUrl");
//        }
//
//        CPApplicaiton.setAccessToken(null);
//
//        /*
//            please notice that i re insert language again after
//           logout to prevent accessing language
//           activity again after splash when logout
//         */
//        String langSelected = GeneralFunction.getStorageManager(context).getValue(CallBackConstants.LANGUAGE_SELECTION, "");
//        getStorageManager(context).clear();
//        GeneralFunction.getStorageManager(context).setValue(CallBackConstants.LANGUAGE_SELECTION, langSelected);
//
//        //TODO : khaled commented this
////        new Thread(new Runnable() {
////            @Override
////            public void run() {
////                try {
////                    FirebaseInstanceId.getInstance().deleteInstanceId();
////                } catch (Exception e) {
////                    LogUtil.printStacktrace(e);
////                }
////            }
////        }).start();
//    }
//
//    public static boolean checkAllPermisssionGranterd(@NonNull String[] permissions,
//                                                      @NonNull int[] grantResults) {
//        boolean permissionResult = true;
//        for (int i = 0; i < permissions.length; i++)
//            if (grantResults[i] != PackageManager.PERMISSION_GRANTED)
//                permissionResult = false;
//        return permissionResult;
//    }
//
//
//    public static void disableEditTExt(EditText editText, boolean enableDisable) {
//        // editText.setCursorVisible(enableDisable);
//        editText.setFocusableInTouchMode(enableDisable);
//        editText.setFocusable(enableDisable);
//    }
//
//    public static void disableView(TextView editText, boolean enableDisable) {
//        editText.setEnabled(enableDisable);
//    }
//
//    public static void cancelNotification(Context ctx) {
//        String ns = Context.NOTIFICATION_SERVICE;
//        NotificationManager nMgr = (NotificationManager) ctx.getSystemService(ns);
//        nMgr.cancel(CallBackConstants.CNOTIFCATION);
//    }
//
//    public static Toolbar setToolbarMsgIconHide(final Context context, @NonNull String title,
//                                                boolean hideBack) {
//        final AppCompatActivity activity = (AppCompatActivity) context;
//        //final ProcessedResult processedResult=(ProcessedResult)context;
//        Toolbar mToolbar = (Toolbar) activity.findViewById(R.id.toolbar);
//        activity.setSupportActionBar(mToolbar);
//
//        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(hideBack);
//        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
//
//        mToolbar.setNavigationIcon(R.drawable.back);
//
//
//        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
//        if (!title.isEmpty()) {
//            TextView textViewTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
//            textViewTitle.setText(title);
//            if (!hideBack)
//                textViewTitle.setPaddingRelative((int) context.getResources().getDimension(R.dimen.dimen_drawable_padding), 0, 0, 0);
//        }
//        return mToolbar;
//    }
//
//    public static Toolbar setToolbarMsgIconHide2(final Context context, @NonNull String title,
//                                                 boolean hideBack) {
//        final AppCompatActivity activity = (AppCompatActivity) context;
//        Toolbar mToolbar = (Toolbar) activity.findViewById(R.id.toolbar);
//        activity.setSupportActionBar(mToolbar);
//
//        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(hideBack);
//        activity.getSupportActionBar().setDisplayShowHomeEnabled(hideBack);
//        mToolbar.setNavigationIcon(null);
//        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
//        if (!title.isEmpty()) {
//            TextView textViewTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
//            textViewTitle.setText(title);
//        }
//        return mToolbar;
//    }
//
//
//    public static Toolbar setToolbarMsgIconHide(final Context context, @NonNull String title,
//                                                boolean hideBack, boolean addExtraPaddingForMenuItem) {
//        final AppCompatActivity activity = (AppCompatActivity) context;
//        //final ProcessedResult processedResult=(ProcessedResult)context;
//        Toolbar mToolbar = (Toolbar) activity.findViewById(R.id.toolbar);
//        activity.setSupportActionBar(mToolbar);
//
//        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(hideBack);
//        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
//
//        mToolbar.setNavigationIcon(R.drawable.back);
//
//
//        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
//        if (!title.isEmpty()) {
//            TextView textViewTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
//            textViewTitle.setText(title);
//            if (!hideBack) {
//                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
//                        RelativeLayout.LayoutParams.MATCH_PARENT,
//                        RelativeLayout.LayoutParams.MATCH_PARENT
//                );
//                params.setMargins(48, 0, 48, 0);
//                textViewTitle.setLayoutParams(params);
//
//            }/*else {
//                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
//                        RelativeLayout.LayoutParams.MATCH_PARENT,
//                        RelativeLayout.LayoutParams.MATCH_PARENT
//                );
//                params.setMargins(48, 0, 48, 0);
//                textViewTitle.setLayoutParams(params);
//            }*/
//        }
//        return mToolbar;
//    }
//
//    public static Toolbar setToolbarMsgIconHide(final Context context, @NonNull String title) {
//        return setToolbarMsgIconHide(context, title, true);
//    }
//
//    public static Toolbar setToolbarMsgIconHide2(final Context context, @NonNull String title) {
//        return setToolbarMsgIconHide2(context, title, true);
//    }
//
//    public static Toolbar setToolbarMsgIconShow(final Context context, @NonNull String title) {
//        return setToolbarMsgIconHide(context, title, false);
//    }
//
//    public static void sendRateBroadCast(Context context , String id , int value){
//        MCustomRate customRate = new MCustomRate(id,value);
//        Intent idIntent = new Intent(EnumStringConstants.LocalReceivers.ADD_RATE_VALUE.toString());
//        idIntent.putExtra(CallBackConstants.MESSAGE, customRate);
//        LocalBroadcastManager.getInstance(context).sendBroadcast(idIntent);
//
//
//    }
//    public static String formatDecimal(double doubleData) {
//        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
//        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
//
//        symbols.setGroupingSeparator(',');
//        symbols.setDecimalSeparator('.');
//        formatter.setDecimalFormatSymbols(symbols);
//        formatter.setMinimumFractionDigits(0);
//        formatter.setMaximumFractionDigits(2);
//        formatter.setRoundingMode(RoundingMode.HALF_UP);
//        return formatter.format(doubleData);
//    }
//
//    public static String formatDecimalMine(double doubleData) {
//        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
//        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
//
//        symbols.setGroupingSeparator(',');
////        symbols.setDecimalSeparator('.');
//        formatter.setDecimalFormatSymbols(symbols);
//        formatter.setMinimumFractionDigits(0);
//        formatter.setMaximumFractionDigits(0);
//        formatter.setRoundingMode(RoundingMode.HALF_UP);
//        return formatter.format(doubleData);
//    }
//
///*    public static double parsStringPrice(String totalPrice)
//    {
//        NumberFormat format = NumberFormat.getInstance(Locale.US);
//        Number number = null;
//        try {
//            number = format.parse(totalPrice);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return number.doubleValue();
//    }*/
//
//    public static Drawable getCreditCardDrawable(Context context, String number) {
//        // String creditCardtype = getType(number).toLowerCase();
//        if (TextUtils.isEmpty(number))
//            return ContextCompat.getDrawable(context, R.drawable.icon_visa);
//        String creditCardtype = number.toLowerCase();
//        if (EnumStringConstants.CreditCards.AMERICAN_EXPRESS.toString().
//                toLowerCase().equals(creditCardtype))
//            return ContextCompat.getDrawable(context, R.drawable.icon_american);
//        else if (EnumStringConstants.CreditCards.DINERS_CLUB.toString().
//                toLowerCase().equals(creditCardtype))
//            return ContextCompat.getDrawable(context, R.drawable.icon_diner);
//        else if (EnumStringConstants.CreditCards.DISCOVER.toString().
//                toLowerCase().equals(creditCardtype))
//            return ContextCompat.getDrawable(context, R.drawable.icon_discover);
//        else if (EnumStringConstants.CreditCards.JCB.toString().
//                toLowerCase().equals(creditCardtype))
//            return ContextCompat.getDrawable(context, R.drawable.icon_jcb);
//        else if (EnumStringConstants.CreditCards.MASTERCARD.toString().
//                toLowerCase().equals(creditCardtype))
//            return ContextCompat.getDrawable(context, R.drawable.icon_master);
//        else if (EnumStringConstants.CreditCards.VISA.toString().
//                toLowerCase().equals(creditCardtype))
//            return ContextCompat.getDrawable(context, R.drawable.icon_visa);
//        else
//            return ContextCompat.getDrawable(context, R.drawable.icon_visa);
//    }
//
//     /*^ = Start of the string.
//            \d{di_best} = Match di_best digits (for condition 1, 2, 3)
//    (?:…) = Grouping
//    [-\s] = Match a space (for condition 3) or a hyphen (for condition 2)
//            \d{di_excellent} = Match di_excellent digits (for condition 2, 3)
//    …? = The pattern before it is optional (for condition 1)
//    $ = End of the string.*/
//
//    private static String getType(String number) {
//        String type = "";
//        if (!Validation.isBlank(number)) {
//            if (Validation.hasAnyPrefix(number, PREFIXES_AMERICAN_EXPRESS))
//                return EnumStringConstants.CreditCards.AMERICAN_EXPRESS.name();
//            else if (Validation.hasAnyPrefix(number, PREFIXES_DISCOVER))
//                return EnumStringConstants.CreditCards.DISCOVER.name();
//            else if (Validation.hasAnyPrefix(number, PREFIXES_JCB))
//                return EnumStringConstants.CreditCards.JCB.name();
//            else if (Validation.hasAnyPrefix(number, PREFIXES_DINERS_CLUB))
//                return EnumStringConstants.CreditCards.DINERS_CLUB.name();
//            else if (Validation.hasAnyPrefix(number, PREFIXES_VISA))
//                return EnumStringConstants.CreditCards.VISA.name();
//            else if (Validation.hasAnyPrefix(number, PREFIXES_MASTERCARD))
//                return EnumStringConstants.CreditCards.MASTERCARD.name();
//            else
//                return EnumStringConstants.CreditCards.UNKNOWN.name();
//        }
//        return type;
//    }
//
//    // Based on http://en.wikipedia.org/wiki/Bank_card_number#Issuer_identification_number_.28IIN.29
//    private static final String[] PREFIXES_AMERICAN_EXPRESS = {"34", "37"};
//    private static final String[] PREFIXES_DISCOVER = {"60", "62", "64", "65"};
//    private static final String[] PREFIXES_JCB = {"35"};
//    private static final String[] PREFIXES_DINERS_CLUB = {"300", "301", "302",
//            "303", "304", "305", "309", "36", "38", "37", "39"};
//    private static final String[] PREFIXES_VISA = {"di_excellent"};
//    private static final String[] PREFIXES_MASTERCARD = {"50", "51", "52", "53", "54", "55"};
//
//    public static int getInteger(String str) {
//        return Integer.parseInt(str);
//    }
//
//    public static String getString(@StringRes int mStringResourceId, Context context) {
//        if (context == null) {
//            return " ";
//        }
//        return context.getResources().getString(mStringResourceId);
//    }
//
//    public static String[] getStringArray(@ArrayRes int mStringResourceId, Context context) {
//        return context.getResources().getStringArray(mStringResourceId);
//    }
//
//    public static int getInteger(@IntegerRes int mStringResourceId, Context context) {
//        return context.getResources().getInteger(mStringResourceId);
//    }
//
//    public static String getString(int str) {
//        return String.valueOf(str);
//    }
//
//    public static String getString(boolean str) {
//        return String.valueOf(str);
//    }
//
//    public static String getString(double str) {
//        return String.valueOf(str);
//    }
//
//    public static Pair<String, Integer> getStatusWithColor(int code, Context context) {
//        switch (EnumNumericConstants.OrderStatus.fromRepresentation(code)) {
//            case ADMIN_CANCELLED:
//                return new Pair<>(getString(R.string.s_status_admin_cancelled, context),
//                        getColor(context, R.color.color_d0021));
//            case CUSTOMER_DECLINED:
//                return new Pair<>(getString(R.string.s_status_customer_cancelled, context),
//                        getColor(context, R.color.color_d0021));
//            case COMPLETED:
//                return new Pair<>(getString(R.string.s_status_completed, context),
//                        getColor(context, R.color.color_77bc1f));
//            case CONFIRMED:
//                return new Pair<>(getString(R.string.s_status_confirmed, context),
//                        getColor(context, R.color.color_13beaf));
//            case IN_PROGRESS:
//                return new Pair<>(getString(R.string.s_status_in_progress, context),
//                        getColor(context, R.color.color_f5a6));
//            case EXPIRE:
//                return new Pair<>(getString(R.string.s_status_expire, context),
//                        getColor(context, R.color.color_d0021));
//            case PHARMACY_ACCEPTED:
//                return new Pair<>(getString(R.string.s_status_pending_customer, context),
//                        getColor(context, R.color.color_4a90e2));
//            case PENDING_PHARMACY:
//                return new Pair<>(getString(R.string.s_status_pending_pharmacy, context),
//                        getColor(context, R.color.color_f567));
//            case PHARMACY_DECLINED:
//                return new Pair<>(getString(R.string.s_status_pharmacy_declined, context),
//                        getColor(context, R.color.color_red));
//            default:
//                return new Pair<>("invalid",
//                        getColor(context, R.color.color_d0021));
//        }
//    }
//
//    public static Pair<String, Integer> getStatusWithImage(int code, Context context) {
//        switch (EnumNumericConstants.OrderStatus.fromRepresentation(code)) {
//            case ADMIN_CANCELLED:
//                return new Pair<>(getString(R.string.s_status_admin_cancelled, context),
//                        R.drawable.canceled_customer3);
//            case CUSTOMER_DECLINED:
//                return new Pair<>(getString(R.string.s_status_customer_cancelled, context),
//                        R.drawable.canceled_customer3);
//            case COMPLETED:
//                return new Pair<>(getString(R.string.s_status_completed, context),
//                        R.drawable.delivered3);
//            case CONFIRMED:
//                return new Pair<>(getString(R.string.s_status_confirmed, context),
//                        R.drawable.delivered3);
//            case IN_PROGRESS:
//                return new Pair<>(getString(R.string.s_status_in_progress, context),
//                        R.drawable.pending_pharmacy3);
//            case EXPIRE:
//                return new Pair<>(getString(R.string.s_status_expire, context),
//                        R.drawable.canceled_customer3);
//            case PHARMACY_ACCEPTED:
//                return new Pair<>(getString(R.string.s_status_pending_customer, context),
//                        R.drawable.waiting_approval3);
//            case PENDING_PHARMACY:
//                return new Pair<>(getString(R.string.s_status_pending_pharmacy, context),
//                        R.drawable.pending_pharmacy3);
//            case PHARMACY_DECLINED:
//                return new Pair<>(getString(R.string.s_status_pharmacy_declined, context),
//                        R.drawable.canceled_customer3);
//            default:
//                return new Pair<>("invalid",
//                        R.drawable.pending_pharmacy3);
//        }
//    }
//
//    public static Map<String, String> getHashMapResource(XmlResourceParser parser) {
//        Map<String, String> map = null;
//        String key = null, value = null;
//
//        try {
//            int eventType = parser.getEventType();
//
//            while (eventType != XmlPullParser.END_DOCUMENT) {
//                if (eventType == XmlPullParser.START_DOCUMENT) {
//
//                } else if (eventType == XmlPullParser.START_TAG) {
//                    if (parser.getName().equals("map")) {
//                        boolean isLinked = parser.getAttributeBooleanValue(null, "linked", false);
//
//                        map = isLinked ? new LinkedHashMap<String, String>() :
//                                new HashMap<String, String>();
//                    } else if (parser.getName().equals("entry")) {
//                        key = parser.getAttributeValue(null, "key");
//
//                        if (null == key) {
//                            parser.close();
//                            return null;
//                        }
//                    }
//                } else if (eventType == XmlPullParser.END_TAG) {
//                    if (parser.getName().equals("entry")) {
//                        map.put(key, value);
//                        key = null;
//                        value = null;
//                    }
//                } else if (eventType == XmlPullParser.TEXT) {
//                    if (null != key) {
//                        value = parser.getText();
//                    }
//                }
//                eventType = parser.next();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//        return map;
//    }
//
//    public static int dp2px(Context context, float dipValue) {
//        final float scale = context.getResources().getDisplayMetrics().density;
//        return (int) (dipValue * scale + EnumNumericConstants.mDP2Px);
//    }
//
//    public static void upateLocale(@StringRes int stringResource, final Context context) {
//        upateLocale(context.getString(stringResource), context);
//    }
//
//    public static void upateLocale(String stringResource, final Context context) {
//        Locale locale = new Locale(stringResource);
//        Locale.setDefault(locale);
//
//        Configuration config = new Configuration();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
//            config.setLocale(locale);
//        else
//            config.locale = locale;
//        config.setLayoutDirection(config.locale);
//        context.getResources().updateConfiguration(config, context.
//                getResources().getDisplayMetrics());
//
//        /*Locale locale = new Locale(stringResource);
//        Locale.setDefault(locale);
//        Configuration config = new Configuration();
//        config.locale = locale;
//        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());*/
//    }
//
//    public static String getLast4Digit(MPayfort mPayfort) {
//        String last4 = mPayfort.getCardNumber().substring(Math.max(mPayfort.
//                getCardNumber().length() - 4, 0));
//        return String.format("%s%s", "XXXX-XXXX-XXXX-", last4);
//    }
//
//    public static  List<MCartItem>  changeCartitemsInOrderToGeneralCartItem(List<McartItem> orderCartItems){
//        List<MCartItem> cartItems = new ArrayList<>();
//
//        for(int i = 0; i <orderCartItems.size() ; i++){
//            MCartItem mCartItem = new MCartItem();
//            mCartItem.setId(orderCartItems.get(i).get_id());
//            mCartItem.setName(orderCartItems.get(i).getName());
//            mCartItem.setArbicName(orderCartItems.get(i).getArbicName());
//            mCartItem.setPrice(Double.parseDouble(orderCartItems.get(i).getPrice()));
//            mCartItem.setVat(Double.parseDouble(orderCartItems.get(i).getVatAdded()));
//            mCartItem.setQuantity(orderCartItems.get(i).getQuantity());
//            mCartItem.setIsBidded(orderCartItems.get(i).getIsBidded());
//
//            MProfilePictureURL pics = orderCartItems.get(i).getProductPic();
//            MImageUrls mImageUrls = new MImageUrls();
//            mImageUrls.setImg1(pics.getThumbnail());
//            mImageUrls.setImg2(pics.getThumbnail());
//            mImageUrls.setImg3(pics.getThumbnail());
//            mCartItem.setImageUrls(mImageUrls);
//
//            cartItems.add(mCartItem);
//        }
//
//        return cartItems;
//    }
//    public static String getFinalCost(MBooking booking, Context context) {
//        if (isNotConfirmed(booking.getStatus()))
//            return formatDecimal(booking.getFinalCost());
//        else {
//            double totalCost = booking.getFinalCost();
//            ShippingChargeInfo shippingChargeInfo = CPApplicaiton.getmProfile().
//                    getData().getShippingChargeInfo();
//           /* MProfile.Data.ShippingChargeInfo shippingChargeInfo =CPApplicaiton.getmProfile().
//                    getData().getShippingChargeInfo();*/
//            double deliveryCost;
//
//            if (String.valueOf(EnumNumericConstants.BookingType.DELIVERY.getCode()).equals(booking.getTypeOfBooking())) {
//                //if (null != booking.getPromotionCost())
//                if (booking.getPromotionCost() >= 0)
//                    totalCost -= GeneralFunction.getDiscountCode(booking.getPromotionCost(),
//                            totalCost, context);
//
//                if (!booking.getReferralCodeApplied()) {
//                    deliveryCost = shippingChargeInfo.getShippingBaseCharges();
//                    totalCost += deliveryCost;
//                }
//
//            } else {
//                //if (null != booking.getPromotionCost())
//                if (booking.getPromotionCost() >= 0)
//                    totalCost -= getDiscountCode(booking.getPromotionCost(),
//                            totalCost, context);
//
//                deliveryCost = shippingChargeInfo.getConvenienceCharge();
//                totalCost += deliveryCost;
//            }
//            return formatDecimal(totalCost);
//        }
//    }
//
//    public static double getDiscountCode(double discountPercentage, double tItemPrice, Context activity) {
//        return (tItemPrice) * discountPercentage /
//                GeneralFunction.getInteger(R.integer.integer_hundred, activity);
//    }
//
//    public static boolean isNotConfirmed(int status) {
//        return !(EnumNumericConstants.OrderStatus.PENDING_PHARMACY.getCode() == status
//                || EnumNumericConstants.OrderStatus.PHARMACY_DECLINED.getCode() == status);
//    }
//
//
//    public static void updateBadge(final Context context) {
//        final Menu menu = CPApplicaiton.getMenu();
//        if (menu != null)
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    ((Parent) context).setBadgeDrawable(menu.findItem(R.id.fh_product_cart));
//                }
//            }, GeneralFunction.getInteger(R.integer.integer_hundred_five, context));
//    }
//
//
//    public static boolean saveItemToCart(final MProductGeneral item, final Context context) {
//        boolean containsItem = false;
//        MCartItem singleItem = new MCartItem();
//        singleItem.setProductId(item.getId());
//        singleItem.setQuantity(item.getQuantity());
//        singleItem.setSize(item.getSize());
//        singleItem.setIsPrescribed(item.getIsPrescribed());
//        singleItem.setBasePrice(item.getPrice() / item.getQuantity());
//        singleItem.setPrice(singleItem.getBasePrice() * singleItem.getQuantity());
//        singleItem.setItemBrand(item.getBrand());
//        singleItem.setName(item.getName());
//        singleItem.setArbicName(item.getArbicName());
//        singleItem.setProduct(item);
//        singleItem.setImageUrls(item.getImageUrls());
////        singleItem.setDiscount(item.getDiscount());
//
//        List<MCartItem> cartItems = new ArrayList<>();
//        if (Paper.book().exist("GuestCart")) {
//            cartItems = Paper.book().read("GuestCart");
//
//            if (cartItems.contains(singleItem)) {
//                containsItem = true;
//                /*int position = cartItems.indexOf(singleItem);
//                cartItems.remove(position);
//                cartItems.add(position, singleItem);*/
//            } else {
//                cartItems.add(singleItem);
//            }
//
//        } else {
//            cartItems.add(0, singleItem);
//        }
//
//        if (!containsItem) {
//            Intent intent = new Intent(EnumStringConstants.LocalReceivers.ADD_CART.toString());
//            intent.putExtra(CallBackConstants.MESSAGE, singleItem);
//            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
//        }
//
//        CART_ITEMS = cartItems.size();
//        Paper.book().write("GuestCart", cartItems);
//        return containsItem;
//    }
//
//
//    public static boolean saveItemToCart(final MProductDetails item, final Context context) {
//        boolean containsItem = false;
//        MCartItem singleItem = new MCartItem();
//        singleItem.setProductId(item.getData().getData().getId());
//        singleItem.setQuantity(1);
//        singleItem.setSize(item.getData().getData().getSize());
//        singleItem.setIsPrescribed(item.getData().getData().getIsPrescribed());
//        singleItem.setBasePrice(item.getData().getData().getPrice() / item.getQuantity());
//        singleItem.setPrice(singleItem.getBasePrice() * singleItem.getQuantity());
//        singleItem.setItemBrand(item.getData().getData().getBrand());
//        singleItem.setName(item.getData().getData().getName());
//        singleItem.setArbicName(item.getData().getData().getArbicName());
//        singleItem.setImageUrls(item.getData().getData().getImageUrls());
//
//        MProductGeneral productGeneral = new MProductGeneral();
//        productGeneral.setAdded(item.getAdded());
//        productGeneral.setName(item.getData().getData().getName());
//        productGeneral.setArbicName(item.getData().getData().getArbicName());
//        productGeneral.setIsFavourite(item.getData().getData().getIsFavourite());
//        productGeneral.setIsCart(item.getData().getData().getIsCart());
//        productGeneral.setDiscount(item.getData().getData().getDiscount());
//        productGeneral.setPrice(item.getData().getData().getPrice());
//        productGeneral.setId(item.getData().getData().getId());
//        singleItem.setProduct(productGeneral);
//
////        singleItem.setDiscount(item.getData().getData().getDiscount());
//
//        List<MCartItem> cartItems = new ArrayList<>();
//        if (Paper.book().exist("GuestCart")) {
//            cartItems = Paper.book().read("GuestCart");
//
//            if (cartItems.contains(singleItem)) {
//                containsItem = true;
//                /*int position = cartItems.indexOf(singleItem);
//                cartItems.remove(position);
//                cartItems.add(position, singleItem);*/
//            } else {
//                cartItems.add(0, singleItem);
//            }
//
//        } else {
//            cartItems.add(singleItem);
//        }
//
//        if (!containsItem) {
//            Intent intent = new Intent(EnumStringConstants.LocalReceivers.ADD_CART.toString());
//            intent.putExtra(CallBackConstants.MESSAGE, singleItem);
//            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
//        }
//
//        CART_ITEMS = cartItems.size();
//        Paper.book().write("GuestCart", cartItems);
//        return containsItem;
//
//    }
//
//
//    public static MProductGeneral convertDetailsToGeneral(MProductDetails productDetails) {
//        MProductGeneral productGeneral = new MProductGeneral();
//        productGeneral.setId(productDetails.getData().getData().getId());
//        productGeneral.setName(productDetails.getData().getData().getName());
//        productGeneral.setArbicName(productDetails.getData().getData().getArbicName());
//        productGeneral.setDiscount(productDetails.getData().getData().getDiscount());
//        productGeneral.setRate(productDetails.getData().getData().getRate());
//        productGeneral.setRateCount(productDetails.getData().getData().getRateCount());
//
//        return productGeneral;
//    }
//
//    public static void sendBroadCastThatItemInserted(final MProductGeneral item, final Context context) {
//
//        MCartItem singleItem = new MCartItem();
//        singleItem.setProductId(item.getId());
//        singleItem.setQuantity(item.getQuantity());
//        singleItem.setSize(item.getSize());
//        singleItem.setIsPrescribed(item.getIsPrescribed());
//        if (item.getQuantity() != 0)
//            singleItem.setBasePrice(item.getPrice() / item.getQuantity());
//        singleItem.setPrice(singleItem.getBasePrice() * singleItem.getQuantity());
//        singleItem.setItemBrand(item.getBrand());
//        singleItem.setName(item.getName());
//        singleItem.setArbicName(item.getArbicName());
//        singleItem.setImageUrls(item.getImageUrls());
//
//        MProductGeneral productGeneral = new MProductGeneral();
//        productGeneral.setAdded(item.getAdded());
//        productGeneral.setName(item.getName());
//        productGeneral.setArbicName(item.getArbicName());
//        productGeneral.setIsFavourite(item.getIsFavourite());
//        productGeneral.setIsCart(item.getIsCart());
//        productGeneral.setDiscount(item.getDiscount());
//        productGeneral.setPrice(item.getPrice());
//        productGeneral.setId(item.getId());
//        singleItem.setProduct(productGeneral);
//
//
//        Intent intent = new Intent(EnumStringConstants.LocalReceivers.ADD_CART.toString());
//        intent.putExtra(CallBackConstants.MESSAGE, singleItem);
//        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
//
//    }
//
//    public static void sendBroadCastThatItemInserted(final MProductDetails item, final Context context) {
//
//        MCartItem singleItem = new MCartItem();
//        singleItem.setProductId(item.getData().getData().getId());
//        singleItem.setQuantity(1);
//        singleItem.setSize(item.getData().getData().getSize());
//        singleItem.setIsPrescribed(item.getData().getData().getIsPrescribed());
//        singleItem.setBasePrice(item.getData().getData().getPrice() / item.getQuantity());
//        singleItem.setPrice(singleItem.getBasePrice() * singleItem.getQuantity());
//        singleItem.setItemBrand(item.getData().getData().getBrand());
//        singleItem.setName(item.getData().getData().getName());
//        singleItem.setArbicName(item.getData().getData().getArbicName());
//        singleItem.setImageUrls(item.getData().getData().getImageUrls());
//
//        MProductGeneral productGeneral = new MProductGeneral();
//        productGeneral.setAdded(item.getAdded());
//        productGeneral.setName(item.getData().getData().getName());
//        productGeneral.setArbicName(item.getData().getData().getArbicName());
//        productGeneral.setIsFavourite(item.getData().getData().getIsFavourite());
//        productGeneral.setIsCart(item.getData().getData().getIsCart());
//        productGeneral.setDiscount(item.getData().getData().getDiscount());
//        productGeneral.setPrice(item.getData().getData().getPrice());
//        productGeneral.setId(item.getData().getData().getId());
//        singleItem.setProduct(productGeneral);
//
//
//        Intent intent = new Intent(EnumStringConstants.LocalReceivers.ADD_CART.toString());
//        intent.putExtra(CallBackConstants.MESSAGE, singleItem);
//        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
//
//    }
//
//    public static void sendBroadCastThatItemRemoved(final MProductGeneral productGeneral, final Context context) {
//
//        MCartItem item = new MCartItem();
//        item.setProductId(productGeneral.getId());
//        item.setId(productGeneral.getId());
//        Intent intent = new Intent(EnumStringConstants.LocalReceivers.DELETE_CART.
//                toString());
//        intent.putExtra(CallBackConstants.MESSAGE, item);
//        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
//
//    }
//
//    public static void sendBroadCastThatItemRemoved(final MProductDetails productGeneral, final Context context) {
//
//        MCartItem item = new MCartItem();
//        item.setProductId(productGeneral.getData().getData().getId());
//        Intent intent = new Intent(EnumStringConstants.LocalReceivers.DELETE_CART.
//                toString());
//        intent.putExtra(CallBackConstants.MESSAGE, item);
//        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
//
//    }
//
//    public static List<MCartItem> getItemFromCart() {
//        List<MCartItem> cartList = new ArrayList<>();
//        if (Paper.book().exist("GuestCart")) {
//            cartList = Paper.book().read("GuestCart");
//        }
//        return cartList;
//    }
//
//    public static void removeCartItem(final String id, final Context context) {
//        List<MCartItem> cartList = new ArrayList<>();
//        if (Paper.book().exist("GuestCart")) {
//            cartList = Paper.book().read("GuestCart");
//        }
//        if (cartList.size() > 0) {
//            for (int i = 0; i < cartList.size(); i++) {
//                MCartItem item = cartList.get(i);
//                if (item.getProductId().equalsIgnoreCase(id)) {
//                    cartList.remove(i);
//                    Intent intent = new Intent(EnumStringConstants.LocalReceivers.DELETE_CART.
//                            toString());
//                    intent.putExtra(CallBackConstants.MESSAGE, item);
//                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
//                    Paper.book().write("GuestCart", cartList);
//                    GeneralFunction.CART_ITEMS = cartList.size();
//                    break;
//                }
//            }
//        }
//    }
//
//    public static void increaseQuantity(MCartItem cartItem) {
//        List<MCartItem> cartList = new ArrayList<>();
//        if (Paper.book().exist("GuestCart")) {
//            cartList = Paper.book().read("GuestCart");
//        }
//        if (cartList.size() > 0) {
//            for (int i = 0; i < cartList.size(); i++) {
//                MCartItem item = cartList.get(i);
//                if (item.getProductId().equalsIgnoreCase(cartItem.getProductId())) {
//                    cartList.remove(i);
//                    item.setQuantity(item.getQuantity() + 1);
//                    item.setPrice(item.getBasePrice() * item.getQuantity());
//                    cartList.add(i, item);
//                    Paper.book().write("GuestCart", cartList);
//                    break;
//                }
//            }
//        }
//    }
//
//    /*public static void increaseQuantity(MCartItem cartItem) {
//        LogUtil.e("increaseQuantity", cartItem.getQuantity() + "");
//        List<MCartItem> cartList = new ArrayList<>();
//        if (Paper.book().exist("GuestCart")) {
//            cartList = Paper.book().read("GuestCart");
//        }
//        if (cartList.size() > 0) {
//            for (int i = 0; i < cartList.size(); i++) {
//                MCartItem item = cartList.get(i);
//                if (item.getProductId().equalsIgnoreCase(cartItem.getProductId())) {
//                    int quantity = item.getQuantity() + 1;
//                    if (item.getBasePrice() != null) {
//                        item.setQuantity(quantity);
//                        item.setPrice(item.getBasePrice() * quantity);
//                    }
//                    cartList.remove(i);
//                    //item.setQuantity(item.getQuantity() + 1);
//                    cartList.add(i, item);
//                    Paper.book().write("GuestCart", cartList);
//                    CPApplicaiton.setItemList((ArrayList<MCartItem>) cartList);
//                    break;
//                }
//            }
//        }
//
//    }*/
//
//    public static void decreaseQuantity(MCartItem cartItem) {
//        List<MCartItem> cartList = new ArrayList<>();
//        if (Paper.book().exist("GuestCart")) {
//            cartList = Paper.book().read("GuestCart");
//        }
//        if (cartList.size() > 0) {
//            for (int i = 0; i < cartList.size(); i++) {
//                MCartItem item = cartList.get(i);
//                if (item.getProductId().equalsIgnoreCase(cartItem.getProductId())) {
//                    cartList.remove(i);
//                    item.setQuantity(item.getQuantity() - 1);
//                    item.setPrice(item.getBasePrice() * item.getQuantity());
//                    cartList.add(i, item);
//                    Paper.book().write("GuestCart", cartList);
//                    break;
//                }
//            }
//        }
//    }
//
//
//    public static void clearGuestData(final Context context) {
//        LogUtil.e("clear user data", "clearing");
//        if (Paper.book().exist("GuestCart")) {
//            Paper.book().delete("GuestCart");
//            CPApplicaiton.setItemList(null);
//        }
//        CPApplicaiton.setGuestToken(null);
//
//        StorageManager storageManager = getStorageManager(context);
//        storageManager.setIsGuestUser(false);
//
//        if (Paper.book().exist("GUEST_TOKEN")) {
//            Paper.book().delete("GUEST_TOKEN");
//        }
//        LogUtil.e("clear user data", "cleared");
//    }
//
//
//    public static void clearDescriptionUrl() {
//        CPApplicaiton.setDescriptionUrl(null);
//        if (Paper.book().exist("descriptionUrl")) {
//            Paper.book().delete("descriptionUrl");
//        }
//    }
//
//
//    public static void showPackageName(final Context context) {
//        //Toast.makeText(context, " " + context.getClass().getName(), Toast.LENGTH_LONG).show();
//        //LogUtil.e("package name : ",context.getClass().getName());
//        //LogUtil.e("class name : ",context.getClass().getSimpleName());
//    }
//
//    public static Bitmap takeScreenShot(Activity activity) {
//        View view = activity.getWindow().getDecorView();
//        view.setDrawingCacheEnabled(true);
//        view.buildDrawingCache();
//        Bitmap b1 = view.getDrawingCache();
//        Rect frame = new Rect();
//        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
//        int statusBarHeight = frame.top;
//        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
//        int height = activity.getWindowManager().getDefaultDisplay().getHeight();
//
//        Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height - statusBarHeight);
//        view.destroyDrawingCache();
//        return b;
//    }
//
//    public static Bitmap fastblur(Bitmap sentBitmap, int radius) {
//        Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
//
//        if (radius < 1) {
//            return (null);
//        }
//
//        int w = bitmap.getWidth();
//        int h = bitmap.getHeight();
//
//        int[] pix = new int[w * h];
//        Log.e("pix", w + " " + h + " " + pix.length);
//        bitmap.getPixels(pix, 0, w, 0, 0, w, h);
//
//        int wm = w - 1;
//        int hm = h - 1;
//        int wh = w * h;
//        int div = radius + radius + 1;
//
//        int r[] = new int[wh];
//        int g[] = new int[wh];
//        int b[] = new int[wh];
//        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
//        int vmin[] = new int[Math.max(w, h)];
//
//        int divsum = (div + 1) >> 1;
//        divsum *= divsum;
//        int dv[] = new int[256 * divsum];
//        for (i = 0; i < 256 * divsum; i++) {
//            dv[i] = (i / divsum);
//        }
//
//        yw = yi = 0;
//
//        int[][] stack = new int[div][3];
//        int stackpointer;
//        int stackstart;
//        int[] sir;
//        int rbs;
//        int r1 = radius + 1;
//        int routsum, goutsum, boutsum;
//        int rinsum, ginsum, binsum;
//
//        for (y = 0; y < h; y++) {
//            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
//            for (i = -radius; i <= radius; i++) {
//                p = pix[yi + Math.min(wm, Math.max(i, 0))];
//                sir = stack[i + radius];
//                sir[0] = (p & 0xff0000) >> 16;
//                sir[1] = (p & 0x00ff00) >> 8;
//                sir[2] = (p & 0x0000ff);
//                rbs = r1 - Math.abs(i);
//                rsum += sir[0] * rbs;
//                gsum += sir[1] * rbs;
//                bsum += sir[2] * rbs;
//                if (i > 0) {
//                    rinsum += sir[0];
//                    ginsum += sir[1];
//                    binsum += sir[2];
//                } else {
//                    routsum += sir[0];
//                    goutsum += sir[1];
//                    boutsum += sir[2];
//                }
//            }
//            stackpointer = radius;
//
//            for (x = 0; x < w; x++) {
//
//                r[yi] = dv[rsum];
//                g[yi] = dv[gsum];
//                b[yi] = dv[bsum];
//
//                rsum -= routsum;
//                gsum -= goutsum;
//                bsum -= boutsum;
//
//                stackstart = stackpointer - radius + div;
//                sir = stack[stackstart % div];
//
//                routsum -= sir[0];
//                goutsum -= sir[1];
//                boutsum -= sir[2];
//
//                if (y == 0) {
//                    vmin[x] = Math.min(x + radius + 1, wm);
//                }
//                p = pix[yw + vmin[x]];
//
//                sir[0] = (p & 0xff0000) >> 16;
//                sir[1] = (p & 0x00ff00) >> 8;
//                sir[2] = (p & 0x0000ff);
//
//                rinsum += sir[0];
//                ginsum += sir[1];
//                binsum += sir[2];
//
//                rsum += rinsum;
//                gsum += ginsum;
//                bsum += binsum;
//
//                stackpointer = (stackpointer + 1) % div;
//                sir = stack[(stackpointer) % div];
//
//                routsum += sir[0];
//                goutsum += sir[1];
//                boutsum += sir[2];
//
//                rinsum -= sir[0];
//                ginsum -= sir[1];
//                binsum -= sir[2];
//
//                yi++;
//            }
//            yw += w;
//        }
//        for (x = 0; x < w; x++) {
//            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
//            yp = -radius * w;
//            for (i = -radius; i <= radius; i++) {
//                yi = Math.max(0, yp) + x;
//
//                sir = stack[i + radius];
//
//                sir[0] = r[yi];
//                sir[1] = g[yi];
//                sir[2] = b[yi];
//
//                rbs = r1 - Math.abs(i);
//
//                rsum += r[yi] * rbs;
//                gsum += g[yi] * rbs;
//                bsum += b[yi] * rbs;
//
//                if (i > 0) {
//                    rinsum += sir[0];
//                    ginsum += sir[1];
//                    binsum += sir[2];
//                } else {
//                    routsum += sir[0];
//                    goutsum += sir[1];
//                    boutsum += sir[2];
//                }
//
//                if (i < hm) {
//                    yp += w;
//                }
//            }
//            yi = x;
//            stackpointer = radius;
//            for (y = 0; y < h; y++) {
//                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
//                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];
//
//                rsum -= routsum;
//                gsum -= goutsum;
//                bsum -= boutsum;
//
//                stackstart = stackpointer - radius + div;
//                sir = stack[stackstart % div];
//
//                routsum -= sir[0];
//                goutsum -= sir[1];
//                boutsum -= sir[2];
//
//                if (x == 0) {
//                    vmin[y] = Math.min(y + r1, hm) * w;
//                }
//                p = x + vmin[y];
//
//                sir[0] = r[p];
//                sir[1] = g[p];
//                sir[2] = b[p];
//
//                rinsum += sir[0];
//                ginsum += sir[1];
//                binsum += sir[2];
//
//                rsum += rinsum;
//                gsum += ginsum;
//                bsum += binsum;
//
//                stackpointer = (stackpointer + 1) % div;
//                sir = stack[stackpointer];
//
//                routsum += sir[0];
//                goutsum += sir[1];
//                boutsum += sir[2];
//
//                rinsum -= sir[0];
//                ginsum -= sir[1];
//                binsum -= sir[2];
//
//                yi += w;
//            }
//        }
//
//        Log.e("pix", w + " " + h + " " + pix.length);
//        bitmap.setPixels(pix, 0, w, 0, 0, w, h);
//
//        return (bitmap);
//    }
//}
