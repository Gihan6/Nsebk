package com.RiyadSoftware.nsebkapp.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.AddTicket.AddTicketMvpView;
import com.RiyadSoftware.nsebkapp.Ui.AddTicket.AddTicketPresenter;
import com.RiyadSoftware.nsebkapp.adapters.DealDetailsImagesAdapter;
import com.RiyadSoftware.nsebkapp.base.BaseActivity;
import com.RiyadSoftware.nsebkapp.data.models.AddTicketRequest;
import com.RiyadSoftware.nsebkapp.data.models.AddTicketResponse;
import com.RiyadSoftware.nsebkapp.data.models.DealDetailsRequest;
import com.RiyadSoftware.nsebkapp.data.models.DealDetailsResponse;
import com.RiyadSoftware.nsebkapp.util.Constant;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;
import com.RiyadSoftware.nsebkapp.view.CircleIndicator2;
import com.bumptech.glide.request.RequestOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Buy extends BaseActivity implements AddTicketMvpView {

//
//    @BindView(R.id.productIV)
//    ImageView image;

    @BindView(R.id.tv_productTitle)
    TextView tv_title;
    @BindView(R.id.dateTV)
    TextView date;
    @BindView(R.id.clearify_tv)
    TextView clearify_tv;
    @BindView(R.id.buy_button)
    Button buy_button;
    @BindView(R.id.timeTV)
    TextView time;
    @BindView(R.id.priceTV)
    TextView priceTV;
    @BindView(R.id.minPriceTV)
    TextView minPriceTV;
    @BindView(R.id.pointsTV)
    TextView pointsTV;
    @BindView(R.id.priceET)
    EditText priceET;
    @BindView(R.id.clearify_holder)
    ViewGroup clearify_holder;
    DealDetailsResponse mDealDetailsResponse;

    @BindView(R.id.info_arrow)
    ImageView info_arrow;

    @BindView(R.id.details_arrow)
    ImageView details_arrow;

    @BindView(R.id.text_price_after_decrease)
    TextView decreasePrice;


    @BindView(R.id.component_arrow)
    ImageView component_arrow;

    @BindView(R.id.info_tv)
    TextView info_tv;

    @BindView(R.id.details_tv)
    TextView details_tv;


    @BindView(R.id.component_tv)
    TextView component_tv;


    //intent data
    int deal_id;

    boolean isPartInDealBefore;


    @BindView(R.id.imagesRV_buy)
    RecyclerView imagesRV;

    @BindView(R.id.indicator_buy)
    CircleIndicator2 indicator;

    Handler handler = new Handler();
    Runnable runnable;
    Timer timer = new Timer();
    @Inject
    AddTicketPresenter addTicketPresenter;
    String pointNumbers = null;
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        ButterKnife.bind(this);

        deal_id = getIntent().getIntExtra("data", 0);
        pointNumbers = getIntent().getStringExtra("points");
        if (deal_id == 0) {
            Toast.makeText(this, getString(R.string.error_in_data), Toast.LENGTH_SHORT).show();
            return;
        }

        activityComponent().inject(this);
        addTicketPresenter.attachView(this);
        addTicketPresenter.getDealDetails
                (new DealDetailsRequest(new SharedPrefDueDate(this).getUserLogged().getRemember_token(),
                        deal_id));


        priceET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.equals(null) && s.length() != 0) {
                    decreasePrice.setText("" + (Integer.parseInt(priceET.getText().toString()) - Integer.parseInt(pointNumbers)));
                } else {
                    decreasePrice.setText("" + 0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private String currentDate(String formar) {
        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(formar);
        Log.e("currentDate", "" + formatter.format(todayDate));
        return formatter.format(todayDate);
    }

    @OnClick(R.id.back_tv)
    public void backtv() {
        finish();
    }


    @OnClick(R.id.continue_tv)
    public void clearify() {
        clearify_holder.setVisibility(View.GONE);
    }


    public String getCountOfDays(String createdDateString, String expireDateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

            Date createdConvertedDate = null, expireCovertedDate = null, todayWithZeroTime = null;

            createdConvertedDate = dateFormat.parse(createdDateString);
            expireCovertedDate = dateFormat.parse(expireDateString);

            Date today = new Date();

            todayWithZeroTime = dateFormat.parse(dateFormat.format(today));


            int cYear = 0, cMonth = 0, cDay = 0;

            if (createdConvertedDate.after(todayWithZeroTime)) {
                Calendar cCal = Calendar.getInstance();
                cCal.setTime(createdConvertedDate);
                cYear = cCal.get(Calendar.YEAR);
                cMonth = cCal.get(Calendar.MONTH);
                cDay = cCal.get(Calendar.DAY_OF_MONTH);

            } else {
                Calendar cCal = Calendar.getInstance();
                cCal.setTime(todayWithZeroTime);
                cYear = cCal.get(Calendar.YEAR);
                cMonth = cCal.get(Calendar.MONTH);
                cDay = cCal.get(Calendar.DAY_OF_MONTH);
            }
            Calendar eCal = Calendar.getInstance();
            eCal.setTime(expireCovertedDate);

            int eYear = eCal.get(Calendar.YEAR);
            int eMonth = eCal.get(Calendar.MONTH);
            int eDay = eCal.get(Calendar.DAY_OF_MONTH);

            Calendar date1 = Calendar.getInstance();
            Calendar date2 = Calendar.getInstance();

            date1.clear();
            date1.set(cYear, cMonth, cDay);
            date2.clear();
            date2.set(eYear, eMonth, eDay);

            long diff = date2.getTimeInMillis() - date1.getTimeInMillis();

            float dayCount = (float) diff / (24 * 60 * 60 * 1000);

            if (dayCount < 0) {
                return getString(R.string.deal_ended);
            } else {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String oldTime = currentDate("yyyy-MM-dd HH:mm");//Timer date 1
                Date oldDate, newDate;
                try {
                    oldDate = formatter.parse(oldTime);
                    newDate = formatter.parse(mDealDetailsResponse.getData().getDealDetails().getExpiryDate());
                    long oldLong = oldDate.getTime();
                    long NewLong = newDate.getTime();
                    long qq = NewLong - oldLong;
                    MyCount counter = new MyCount(qq, 1000);
                    counter.start();

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
            return ("" + (int) dayCount + " Days");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    @SuppressLint("StringFormatInvalid")
    private void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.buy_dialog);
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.copyFrom(dialog.getWindow().getAttributes());
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        dialog.show();
//        dialog.getWindow().setAttributes(lp);
// retrieve display dimensions
        Rect displayRectangle = new Rect();
        Window window = getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);

// inflate and adjust layout
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.buy_dialog, null);
        layout.setMinimumWidth((int) (displayRectangle.width() * 0.7f));
//        layout.setMinimumHeight((int)(displayRectangle.height() * 0.7f));
        dialog.setContentView(layout);
        TextView dialogButton = dialog.findViewById(R.id.cancel_tv);
        TextView ok_tv = dialog.findViewById(R.id.ok_tv);
        TextView dialog_txt = dialog.findViewById(R.id.dialog_txt);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ok_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPartInDealBefore) {
                    dialog.dismiss();
                    addTicketPresenter.AddTicket
                            (new AddTicketRequest(
                                    new SharedPrefDueDate(Buy.this).getUserLogged().getRemember_token(),
                                    String.valueOf(deal_id),
                                    priceET.getText().toString()));
                } else {
                    dialog.dismiss();
                    finish();
                    isPartInDealBefore = true;
                }

            }
        });

        if (isPartInDealBefore) {
            dialog_txt.setText(String.format(getString(R.string.clearify_update_string),
                    mDealDetailsResponse.getData().getDealDetails().getTenderEditCost(),
                    mDealDetailsResponse.getData().getDealDetails().getMy_ticket_points()));
            dialogButton.setVisibility(View.VISIBLE);
        } else {
            dialog_txt.setText(String.format(getString(R.string.clearify_string_sucess),
                    mDealDetailsResponse.getData().getDealDetails().getTenderCost(),
                    mDealDetailsResponse.getData().getDealDetails().getTenderCoupon()));
            dialogButton.setVisibility(View.INVISIBLE);
        }

        dialog.show();
    }

    private void showDialogForLowPrice() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.buy_dialog);
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.copyFrom(dialog.getWindow().getAttributes());
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        dialog.show();
//        dialog.getWindow().setAttributes(lp);
// retrieve display dimensions
        Rect displayRectangle = new Rect();
        Window window = getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);

// inflate and adjust layout
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.buy_dialog, null);
        layout.setMinimumWidth((int) (displayRectangle.width() * 0.7f));
//        layout.setMinimumHeight((int)(displayRectangle.height() * 0.7f));
        dialog.setContentView(layout);
        TextView dialogButton = dialog.findViewById(R.id.cancel_tv);
        TextView ok_tv = dialog.findViewById(R.id.ok_tv);
        TextView dialog_txt = dialog.findViewById(R.id.dialog_txt);
        ImageView imageView = dialog.findViewById(R.id.image);
        imageView.setImageResource(R.drawable.sad_face);
        dialog.findViewById(R.id.image2).setRotation(180f);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ok_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        dialog_txt.setText(getString(R.string.price_less));
        dialogButton.setVisibility(View.GONE);

        dialog.show();
    }

    private void showDialogForAlreadyPartInDeal() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.buy_dialog);
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.copyFrom(dialog.getWindow().getAttributes());
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        dialog.show();
//        dialog.getWindow().setAttributes(lp);
// retrieve display dimensions
        Rect displayRectangle = new Rect();
        Window window = getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);

// inflate and adjust layout
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.buy_dialog, null);
        layout.setMinimumWidth((int) (displayRectangle.width() * 0.7f));
//        layout.setMinimumHeight((int)(displayRectangle.height() * 0.7f));
        dialog.setContentView(layout);
        TextView dialogButton = dialog.findViewById(R.id.cancel_tv);
        TextView ok_tv = dialog.findViewById(R.id.ok_tv);
        TextView dialog_txt = dialog.findViewById(R.id.dialog_txt);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ok_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        dialog_txt.setText(String.format(getString(R.string.already_part),
                mDealDetailsResponse.getData().getDealDetails().getMy_ticket_points()));
        dialogButton.setVisibility(View.GONE);

        dialog.show();
    }

    @OnClick(R.id.back_buy)
    void back_buy() {
        finish();
    }

    @OnClick(R.id.buy_button)
    void buy_button() {
        if (priceET.getText().toString().trim().isEmpty()) {
            Toast.makeText(Buy.this,
                    "enter price", Toast.LENGTH_LONG).show();
            return;
        }

        if (mDealDetailsResponse != null) {
            try {
                if (Integer.parseInt(priceET.getText().toString().trim())
                        < Integer.parseInt(mDealDetailsResponse.getData().getDealDetails().getInitialPrice())) {
                    showDialogForLowPrice();
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (isPartInDealBefore) {
            showDialog();
        } else {
            addTicketPresenter.AddTicket(new AddTicketRequest(new SharedPrefDueDate(this).getUserLogged().getRemember_token(),
                    String.valueOf(deal_id), priceET.getText().toString()));
        }
//        Toast.makeText(Buy.this,getString(R.string.doneprice),Toast.LENGTH_LONG).show();
//        finish();

//        startActivity(new Intent(this,Winner.class));
    }

    @SuppressLint("StringFormatInvalid")
    @Override
    public void addedToTicket(AddTicketResponse addTicketResponse) {

        if (addTicketResponse.success.equalsIgnoreCase("success")) {
            Toast.makeText(this, "" + addTicketResponse.message, Toast.LENGTH_SHORT).show();
            buy_button.setText(getString(R.string.update));

            clearify_tv.setText(String.format(getString(R.string.clearify_update_string),
                    mDealDetailsResponse.getData().getDealDetails().getTenderEditCost(),
                    mDealDetailsResponse.getData().getDealDetails().getMy_ticket_points()));

            clearify_holder.setVisibility(View.VISIBLE);


            Intent intent = new Intent(Constant.MY_DEAL);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

            if (!isPartInDealBefore)
                showDialog();
//            isPartInDealBefore = true;
//            priceET.setText("");
        } else if (addTicketResponse.errors.equals("api.deal_closed")) {
            Toast.makeText(this, getString(R.string.handleError), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "" + addTicketResponse.errors, Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick(R.id.info_holder)
    public void infoClick() {
        info_arrow.setRotation(180f);
        component_arrow.setRotation(0);
        details_arrow.setRotation(0);

        info_tv.setVisibility(View.VISIBLE);
        details_tv.setVisibility(View.GONE);
        component_tv.setVisibility(View.GONE);
    }

    @OnClick(R.id.details_holder)
    public void detailsClick() {
        info_arrow.setRotation(0);
        component_arrow.setRotation(0);
        details_arrow.setRotation(180);

        info_tv.setVisibility(View.GONE);
        details_tv.setVisibility(View.VISIBLE);
        component_tv.setVisibility(View.GONE);
    }

    @OnClick(R.id.component_holder)
    public void componentClick() {
        info_arrow.setRotation(0);
        component_arrow.setRotation(180);
        details_arrow.setRotation(0);

        info_tv.setVisibility(View.GONE);
        details_tv.setVisibility(View.GONE);
        component_tv.setVisibility(View.VISIBLE);
    }

    @SuppressLint({"StringFormatInvalid", "StringFormatMatches"})
    @Override
    public void showDetails(DealDetailsResponse dealDetailsResponse) {
        try {
            if (dealDetailsResponse.getData() == null || dealDetailsResponse.getData().getDealDetails() == null)
                return;

            mDealDetailsResponse = dealDetailsResponse;

//        if (dealDetailsResponse.getData().getDealDetails().getwi)
            RequestOptions options = new RequestOptions();
            options.fallback(R.drawable.logo);
            options.placeholder(R.drawable.logo);
           /* if (!isDestroyed())
                Glide.with(Buy.this).load(dealDetailsResponse.getData().getDealDetails().getImage()).apply(options).into(image);
                */

            handleDealImages();

            Log.e("Time", "" + dealDetailsResponse.getData().getDealDetails().getExpiryDate().split(" ")[1]);
            Log.e("Date", "" + dealDetailsResponse.getData().getDealDetails().getExpiryDate().split(" ")[0]);

            if (dealDetailsResponse.getData().getDealDetails().getMy_ticket_points() != null) {
                isPartInDealBefore = true;
                priceET.setText(dealDetailsResponse.getData().getDealDetails().getMy_ticket_points());
                buy_button.setText(getString(R.string.update));
                showDialogForAlreadyPartInDeal();
                clearify_tv.setText(String.format(getString(R.string.clearify_update_string),
                        mDealDetailsResponse.getData().getDealDetails().getTenderEditCost(),
                        mDealDetailsResponse.getData().getDealDetails().getMy_ticket_points()));
            } else {
                isPartInDealBefore = false;
                if (dealDetailsResponse.getData().getDealDetails().getTenderCost() != null) {
                    clearify_tv.setText(String.format(getString(R.string.clearify_string),
                            dealDetailsResponse.getData().getDealDetails().getTenderCost(),
                            dealDetailsResponse.getData().getDealDetails().getTenderCoupon()));
                } else {
                    clearify_tv.setText(String.format(getString(R.string.clearify_string),
                            "" + 0,
                            dealDetailsResponse.getData().getDealDetails().getTenderCoupon()));
                }

            }

            //----
            tv_title.setText(dealDetailsResponse.getData().getDealDetails().getTitle());
            //----
            time.setText(dealDetailsResponse.getData().getDealDetails().getExpiryDate().split(" ")[1]);
            date.setText(getCountOfDays(currentDate("yyyy-MM-dd"),
                    dealDetailsResponse.getData().getDealDetails().getExpiryDate().split(" ")[0]));

            info_tv.setText(dealDetailsResponse.getData().getDealDetails().getInfo());
            component_tv.setText(dealDetailsResponse.getData().getDealDetails().getTitle());
            details_tv.setText(dealDetailsResponse.getData().getDealDetails().getDisc());


            SharedPrefDueDate prefDueDate = new SharedPrefDueDate(this);
            if (prefDueDate.getCurrency().equals("2")) {
                priceTV.setText(dealDetailsResponse.getData().getDealDetails().getOriginalPrice() + " " + getString(R.string.SAR));
                minPriceTV.setText(dealDetailsResponse.getData().getDealDetails().getInitialPrice() + " " + getString(R.string.SAR));
            } else {
                priceTV.setText(dealDetailsResponse.getData().getDealDetails().getOriginalPrice() + " " + getString(R.string.dollar_currency));
                minPriceTV.setText(dealDetailsResponse.getData().getDealDetails().getInitialPrice() + " " + getString(R.string.dollar_currency));
            }

            pointsTV.setText(dealDetailsResponse.getData().getDealDetails().getPoints() == null ? 0 + "  " + getString(R.string.tickets) :
                    dealDetailsResponse.getData().getDealDetails().getPoints() + "  " + getString(R.string.tickets));


            if (dealDetailsResponse.getData().getDealDetails().getWinner_id() != null) {
//            if (new SharedPrefDueDate(this).getUserLogged().getId() ==
//                    Integer.valueOf(dealDetailsResponse.getData().getDealDetails().getWinner_id())){
//
//            }
                Intent intent = new Intent(this, Winner.class);
                intent.putExtra("name", dealDetailsResponse.getData().getDealDetails().getWinner_name());
                intent.putStringArrayListExtra("winners", (ArrayList<String>) dealDetailsResponse.getData().getDealDetails().getFive_second_users());
                startActivity(intent);
                finish();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }

    private void handleDealImages() {
        final List<String> list = new ArrayList<>();
        for (int i = 0; i < mDealDetailsResponse.getData().getDealDetails().getImages().size(); i++) {
            list.add(mDealDetailsResponse.getData().getDealDetails().getImages().get(i).toString());
        }
        DealDetailsImagesAdapter dealDetailsImagesAdapter = new DealDetailsImagesAdapter(list, this);

        RecyclerView.LayoutManager
                layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);

        imagesRV.setLayoutManager(layoutManager);
        imagesRV.setOnFlingListener(null);

        imagesRV.setAdapter(dealDetailsImagesAdapter);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(imagesRV);

        runnable = new Runnable() {
            @Override
            public void run() {
                if (currentPage == list.size()) {
                    currentPage = 0;
                }
                imagesRV.scrollToPosition(currentPage++);
            }
        };
        // This will create a new Thread
        timer = new Timer();
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 500, 4000);

        indicator.attachToRecyclerView(imagesRV, pagerSnapHelper);
        dealDetailsImagesAdapter.registerAdapterDataObserver(indicator.getAdapterDataObserver());
    }

    public class MyCount extends CountDownTimer {
        MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            date.setText(getString(R.string.deal_ended));
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = (TimeUnit.MILLISECONDS.toDays(millis)) + "  Day  "
                    + (TimeUnit.MILLISECONDS.toHours(millis) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millis)) + " hours ")
                    + (TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)) + " Minutes "
                    + (TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))) + " Seconds ");
            time.setText(/*context.getString(R.string.ends_in) + " " +*/ hms);
        }
    }
}
