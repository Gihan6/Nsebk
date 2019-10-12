package com.RiyadSoftware.nsebkapp.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.Home.HomePresenter;
import com.RiyadSoftware.nsebkapp.Ui.Home.HomeView;
import com.RiyadSoftware.nsebkapp.adapters.CurrentDealsAdapter;
import com.RiyadSoftware.nsebkapp.adapters.DealsAdapter;
import com.RiyadSoftware.nsebkapp.adapters.OffersAdapter;
import com.RiyadSoftware.nsebkapp.base.BaseFragment;
import com.RiyadSoftware.nsebkapp.data.models.AddFavRequest;
import com.RiyadSoftware.nsebkapp.data.models.AddFavResponse;
import com.RiyadSoftware.nsebkapp.data.models.HomeModel;
import com.RiyadSoftware.nsebkapp.data.models.HomeRequest;
import com.RiyadSoftware.nsebkapp.data.models.currency.CurrencyResponse;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;
import com.RiyadSoftware.nsebkapp.view.CircleIndicator2;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.RiyadSoftware.nsebkapp.util.Constant.MY_DEAL;

public class MainFragment extends BaseFragment implements HomeView, DealsAdapter.AddToFavInterface, CurrentDealsAdapter.AddToFavInterface2 {

    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.imagesRV)
    RecyclerView imagesRV;
    @BindView(R.id.parent_main)
    ScrollView parent;
    @BindView(R.id.home_recyclerview)
    RecyclerView home_recyclerview;
    //init the recylerView
    DealsAdapter mProductAdapter /*adapterFuture, adapterEnded, adapterOnline, adapterMy*/;

    CurrentDealsAdapter currentDealsAdapter;
    @BindView(R.id.no_data_holder)
    ViewGroup no_data_holder;

    @BindView(R.id.indicator)
    CircleIndicator2 indicator;
    @BindView(R.id.my_deals)
    TextView my_deals;
    @BindView(R.id.ended_deals)
    TextView ended_deals;
    @BindView(R.id.future_deals)
    TextView future_deals;
    @BindView(R.id.current_deals)
    TextView current_deals;
    @BindView(R.id.linear_icon)
    ImageView linear_icon;

    @BindView(R.id.linear_vg)
    ViewGroup linear_vg;
    @BindView(R.id.grid_vg)
    ViewGroup grid_vg;

    @BindView(R.id.onlineRV)
    RecyclerView onlineRV;
    @BindView(R.id.onlineempty)
    TextView onlineempty;

    @BindView(R.id.futureRV)
    RecyclerView futureRV;
    @BindView(R.id.futureempty)
    TextView futureempty;
    @BindView(R.id.endedRV)
    RecyclerView endedRV;
    @BindView(R.id.endedempty)
    TextView endedempty;
    @BindView(R.id.myProductRV)
    RecyclerView myProductRV;
    @BindView(R.id.mydealsempty)
    TextView mydealsempty;

    @BindView(R.id.linear_deals)
    LinearLayout linearDeals;
    @BindView(R.id.swipeToRefreshEnd)
    SwipeRefreshLayout refreshOnline;


    HomeModel mHomeModel;

    //shared pref
    SharedPrefDueDate pref;

    //the offers
    OffersAdapter adapterOffer;

    @Inject
    HomePresenter mHomePresenter;
    private boolean gridView = true;

    BroadcastReceiver mBroadcastReceiver;

    Handler handler = new Handler();
    Runnable runnable;
    Timer timer = new Timer();


    List<ImageView> dots;
    private ImageView dot;
    private int currentPage = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate tmy_dealshe layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activityComponent().inject(this);
        mHomePresenter.attachView(this);
        pref = new SharedPrefDueDate(getContext());

        if (pref.getLanguage().equals("ar")) {
            linearDeals.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        } else {
            linearDeals.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }

        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (!isAdded())
                    return;

                if (MY_DEAL.equals(intent.getAction())) {
                    mHomePresenter.getHomeData(new HomeRequest(pref.getUserLogged().getRemember_token()));
                }
            }
        };
        IntentFilter filter = new IntentFilter(MY_DEAL);
        LocalBroadcastManager.getInstance(getActivity())
                .registerReceiver(mBroadcastReceiver, filter);

        mProductAdapter = new DealsAdapter(getContext(), null, this, gridView);
        home_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        home_recyclerview.setAdapter(mProductAdapter);
        home_recyclerview.setNestedScrollingEnabled(false);

        mHomePresenter.getHomeData(new HomeRequest(pref.getUserLogged().getRemember_token()));

//-------------------------------------------------------------

        refreshOnline.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                home_recyclerview.setAdapter(mProductAdapter);
                home_recyclerview.setNestedScrollingEnabled(false);
                mHomePresenter.getHomeData(new HomeRequest(pref.getUserLogged().getRemember_token()));
                refreshOnline.setRefreshing(false);

            }
        });
    }






    private void getDataForGrid() {
        displayCurrentDeals();
    }


    private void getDataForLinear() {
        displayPastDeals();
        displayMyDeals();
        displayCurrentDeals();
        displayFutureDeals();
    }

    @OnClick(R.id.my_deals)
    public void myDealsData() {
        flag = 4;
        displayMyDeals();
    }

    @OnClick(R.id.ended_deals)
    public void endedDealsData() {
        flag = 3;
        displayPastDeals();
    }

    @OnClick(R.id.future_deals)
    public void future_deals() {
        flag = 2;
        displayFutureDeals();
    }

    int flag = 0;

    @OnClick(R.id.current_deals)
    public void current_deals() {
        flag = 1;
        displayCurrentDeals();
    }


    private void displayMyDeals() {

        my_deals.setTextColor(getResources().getColor(R.color.colorAccent));
        current_deals.setTextColor(getResources().getColor(R.color.colorPrimary));
        future_deals.setTextColor(getResources().getColor(R.color.colorPrimary));
        ended_deals.setTextColor(getResources().getColor(R.color.colorPrimary));

        if (mHomeModel != null) {
            mProductAdapter = new DealsAdapter(context, mHomeModel.getData().getMy_Deals(), this, gridView);


            if (gridView) {
                if (mProductAdapter.getItemCount() == 0) {
                    no_data_holder.setVisibility(View.VISIBLE);
                    home_recyclerview.setVisibility(View.GONE);
                } else {
                    home_recyclerview.setVisibility(View.VISIBLE);
                    no_data_holder.setVisibility(View.GONE);
                    home_recyclerview.setAdapter(mProductAdapter);
                }
            } else {
                myProductRV.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                myProductRV.setAdapter(mProductAdapter);
                if (mHomeModel.getData().getMy_Deals().isEmpty()) {
                    mydealsempty.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void displayPastDeals() {

        ended_deals.setTextColor(getResources().getColor(R.color.colorAccent));
        current_deals.setTextColor(getResources().getColor(R.color.colorPrimary));
        future_deals.setTextColor(getResources().getColor(R.color.colorPrimary));
        my_deals.setTextColor(getResources().getColor(R.color.colorPrimary));

        if (mHomeModel != null) {
            mProductAdapter = new DealsAdapter(context, mHomeModel.getData().getPervious_Deals(), this, gridView);


            if (gridView) {
                if (mProductAdapter.getItemCount() == 0) {
                    no_data_holder.setVisibility(View.VISIBLE);
                    home_recyclerview.setVisibility(View.GONE);
                } else {
                    home_recyclerview.setVisibility(View.VISIBLE);
                    no_data_holder.setVisibility(View.GONE);
                    home_recyclerview.setAdapter(mProductAdapter);
                }
            } else {
                endedRV.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                endedRV.setAdapter(mProductAdapter);
                if (mHomeModel.getData().getPervious_Deals().isEmpty()) {
                    endedempty.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void displayFutureDeals() {
        future_deals.setTextColor(getResources().getColor(R.color.colorAccent));
        current_deals.setTextColor(getResources().getColor(R.color.colorPrimary));
        ended_deals.setTextColor(getResources().getColor(R.color.colorPrimary));
        my_deals.setTextColor(getResources().getColor(R.color.colorPrimary));

        if (mHomeModel != null) {
            currentDealsAdapter = new CurrentDealsAdapter(context, true,
                    mHomeModel.getData().getComing_Deals(), this, gridView);
            if (gridView) {
                if (currentDealsAdapter.getItemCount() == 0) {
                    no_data_holder.setVisibility(View.VISIBLE);
                    home_recyclerview.setVisibility(View.GONE);
                } else {
                    home_recyclerview.setVisibility(View.VISIBLE);
                    no_data_holder.setVisibility(View.GONE);
                    home_recyclerview.setAdapter(currentDealsAdapter);
                }
            } else {
                futureRV.setLayoutManager(new LinearLayoutManager(context,
                        LinearLayoutManager.VERTICAL, false));
                futureRV.setAdapter(currentDealsAdapter);
                if (mHomeModel.getData().getComing_Deals().isEmpty()) {
                    futureempty.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void displayCurrentDeals() {
        if (!isAdded()) {
            return;
        }

        current_deals.setTextColor(getResources().getColor(R.color.colorAccent));
        future_deals.setTextColor(getResources().getColor(R.color.colorPrimary));
        ended_deals.setTextColor(getResources().getColor(R.color.colorPrimary));
        my_deals.setTextColor(getResources().getColor(R.color.colorPrimary));

        if (mHomeModel != null) {
            currentDealsAdapter = new CurrentDealsAdapter(context,
                    mHomeModel.getData().getNow_Deals(), this, gridView);
            if (gridView) {
                if (currentDealsAdapter.getItemCount() == 0) {
                    no_data_holder.setVisibility(View.VISIBLE);
                    home_recyclerview.setVisibility(View.GONE);
                } else {
                    Log.e("Coutn", "" + mHomeModel.getData().getNow_Deals().size());
                    home_recyclerview.setVisibility(View.VISIBLE);
                    no_data_holder.setVisibility(View.GONE);
                    home_recyclerview.setAdapter(currentDealsAdapter);
                }
            } else {
                onlineRV.setLayoutManager(new LinearLayoutManager(context,
                        LinearLayoutManager.VERTICAL, false));
                onlineRV.setAdapter(currentDealsAdapter);
                if (mHomeModel.getData().getNow_Deals().isEmpty()) {
                    onlineempty.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public void getHomeData(HomeModel homeModel) {

        if (!isAdded()) {
            return;
        }
        if (homeModel.getSuccess().equalsIgnoreCase("success")) {
            mHomeModel = homeModel;

            switch (flag) {
                case 1:

                    displayCurrentDeals();
                    break;

                case 2:

                    displayFutureDeals();
                    break;
                case 3:

                    displayPastDeals();
                    break;
                case 4:

                    displayMyDeals();
                    break;
                default:
                    showOffers(homeModel.getData().getAdvertisements());


                    if (gridView)
                        getDataForGrid();
                    else
                        getDataForLinear();
            }

        } else if (homeModel.getSuccess().equalsIgnoreCase("logged")) {
            logout();
        } else {
            Toast.makeText(context, getString(R.string.error_in_data), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showLoader() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        loading.setVisibility(View.GONE);
    }

    @Override
    public void DealAddedToFav(AddFavResponse addFavResponse) {
        mHomePresenter.getHomeData(new HomeRequest(pref.getUserLogged().getRemember_token()));


    }

    @Override
    public void currenciesList(CurrencyResponse data) {
        pref.saveCurrencyResponse(data);
    }


    private void showOffers(final List<HomeModel.Advertisement> listOffers) {
        adapterOffer = new OffersAdapter(listOffers, getActivity());
        RecyclerView.LayoutManager
                layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false)/*{
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        }*/;
        imagesRV.setLayoutManager(layoutManager);
        imagesRV.setOnFlingListener(null);

        imagesRV.setAdapter(adapterOffer);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(imagesRV);

        runnable = new Runnable() {
            @Override
            public void run() {
                if (currentPage == listOffers.size()) {
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
        }, 500, 20000);

        indicator.attachToRecyclerView(imagesRV, pagerSnapHelper);
        adapterOffer.registerAdapterDataObserver(indicator.getAdapterDataObserver());

//        imagesRV.addon(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                currentPage = position;
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                selectDot(position, listOffers.size());
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//            }
//        });


    }

    /*public void addDots(int size) {

        dots = new ArrayList<>();
        layoutDots.removeAllViews();

        for (int i = 0; i < size; i++) {

            dot = new ImageView(context);
            dot.setImageDrawable(getResources().getDrawable(R.drawable.oval_on));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 2, 4, 2);
            layoutDots.addView(dot, params);
            layoutDots.getChildCount();
            dots.add(dot);

        }
    }

    public void selectDot(int idx, int size) {
        try {
            for (int i = 0; i < size; i++) {
                int drawableId = (i == idx) ? (R.drawable.oval_on) : (R.drawable.oval_off);
                Drawable drawable = getResources().getDrawable(drawableId);
                dots.get(i).setImageDrawable(drawable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    //    @OnClick(R.id.grid_icon)
    public void goToGridMode() {
//        gridView = true;
        grid_vg.setVisibility(View.VISIBLE);
        linear_vg.setVisibility(View.GONE);
        getDataForGrid();
    }


    @OnClick(R.id.linear_icon)
    public void goToLnearMode() {
        gridView = !gridView;
        if (gridView) {
            linear_icon.setImageResource(R.drawable.list);
            goToGridMode();

        } else {
            linear_icon.setImageResource(R.drawable.grid_icon);
            linear_vg.setVisibility(View.VISIBLE);
            grid_vg.setVisibility(View.GONE);
            getDataForLinear();

        }
    }


    @Override
    public void showEmpty() {
    }

    @Override
    public void showError() {
        Toast.makeText(context, getString(R.string.error_in_data), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addToFav(String dealID, int position) {
        mHomePresenter.addToFav(new AddFavRequest(pref.getUserLogged().getRemember_token(), dealID));
    }


}
