package com.RiyadSoftware.nsebkapp.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.finishDeal.FinishDealPrsenter;
import com.RiyadSoftware.nsebkapp.Ui.finishDeal.FinishDealView;
import com.RiyadSoftware.nsebkapp.adapters.DealDetailsImagesAdapter;
import com.RiyadSoftware.nsebkapp.adapters.WinnersAdadper;
import com.RiyadSoftware.nsebkapp.base.BaseActivity;
import com.RiyadSoftware.nsebkapp.data.models.Finishdeal.FinishDealRequest;
import com.RiyadSoftware.nsebkapp.data.models.Finishdeal.FinishDealResponse;
import com.RiyadSoftware.nsebkapp.models.WinerModel;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;
import com.RiyadSoftware.nsebkapp.view.CircleIndicator2;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FinishDeal extends BaseActivity implements FinishDealView {

    @BindView(R.id.priceTV)
    TextView priceTV;
    @BindView(R.id.minPriceTV)
    TextView minPriceTV;
    @BindView(R.id.pointsTV)
    TextView pointsTV;

    @BindView(R.id.tv_winnerName)
    TextView tv_winnerName;
    @BindView(R.id.tv_goodLuck)
    TextView tv_goodLuck;
    @BindView(R.id.btn_buy)
    Button btn_buy;


    @BindView(R.id.imagesRV_buy)
    RecyclerView imagesRV;
    @BindView(R.id.rv_winnerNames)
    RecyclerView rv_winner;
    @BindView(R.id.indicator_buy)
    CircleIndicator2 indicator;


    WinnersAdadper adadper;
    RecyclerView.LayoutManager layoutManager;

    AlertDialog alertDialog;

    Handler handler = new Handler();
    Runnable runnable;
    Timer timer = new Timer();
    @Inject
    FinishDealPrsenter prsenter;

    int deal_id;
    String pointNumbers = null;
    private int currentPage = 0;
    List<WinerModel> winners;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_deal);
        ButterKnife.bind(this);
        activityComponent().inject(this);

        initAdapter();

        deal_id = getIntent().getIntExtra("data", 0);
        pointNumbers = getIntent().getStringExtra("points");
        if (deal_id == 0) {
            Toast.makeText(this, getString(R.string.error_in_data), Toast.LENGTH_SHORT).show();
            return;
        }
        prsenter.attachView(this);
        prsenter.getFinishDealData(new FinishDealRequest(new SharedPrefDueDate(this).getUserLogged().getRemember_token(),
                deal_id));


        pointsTV.setText(pointNumbers);
    }

    void initAdapter() {
        layoutManager = new LinearLayoutManager(this);
        rv_winner.setLayoutManager(layoutManager);
        adadper = new WinnersAdadper(this, winners);
    }


    @OnClick(R.id.btn_buy)
    void buyDeal() {
        showPaymentDialog();


    }


    @Override
    public void getFinishDealData(FinishDealResponse response) {

        //Handel all view here

        //if i am winner show button of
        btn_buy.setVisibility(View.VISIBLE);


    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {
        btn_buy.setVisibility(View.VISIBLE);

    }

    //Wait for image
    private void handleDealImages() {
        final List<String> list = new ArrayList<>();
//        for (int i = 0; i < mDealDetailsResponse.getData().getDealDetails().getImages().size(); i++) {
//            list.add(mDealDetailsResponse.getData().getDealDetails().getImages().get(i).toString());
//        }
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

    private void showPaymentDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_payment);
        dialog.setCancelable(true);

        Button btn_cancel = dialog.findViewById(R.id.close);
        Button btn_pay = dialog.findViewById(R.id.btn_pay);
        EditText et_creditNum=dialog.findViewById(R.id.et_numOFCredit);
        EditText et_finishDate=dialog.findViewById(R.id.et_finishDate);
        EditText et_validateNun=dialog.findViewById(R.id.et_validateNum);
        EditText et_name=dialog.findViewById(R.id.et_numOFCredit);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"cancel",Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Should pay",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
