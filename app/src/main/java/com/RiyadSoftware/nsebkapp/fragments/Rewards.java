package com.RiyadSoftware.nsebkapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.Rewards.RewardsPresenter;
import com.RiyadSoftware.nsebkapp.Ui.Rewards.RewardsSubView;
import com.RiyadSoftware.nsebkapp.adapters.OffersAdapter;
import com.RiyadSoftware.nsebkapp.adapters.RewardAdapter;
import com.RiyadSoftware.nsebkapp.base.BaseFragment;
import com.RiyadSoftware.nsebkapp.data.models.OffersResponseModel;
import com.RiyadSoftware.nsebkapp.data.models.RewardsResponse;
import com.RiyadSoftware.nsebkapp.data.models.awards.ReplaceAwardResponse;
import com.RiyadSoftware.nsebkapp.util.OnItemClickListener;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;
import com.RiyadSoftware.nsebkapp.view.CircleIndicator2;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Rewards extends BaseFragment implements RewardsSubView {

    @BindView(R.id.reward_recycler)
    RecyclerView reward_recycler;

    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.imagesRV)
    RecyclerView imagesRV;


    @Inject
    RewardsPresenter mRewardsPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_rewards, container, false);
        ButterKnife.bind(this, root);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activityComponent().inject(this);
        mRewardsPresenter.attachView(this
        );
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        reward_recycler.setLayoutManager(layoutManager);


        RecyclerView.LayoutManager
                layoutManager6 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        imagesRV.setLayoutManager(layoutManager6);


        mRewardsPresenter.getRewards(new SharedPrefDueDate(getActivity()).getUserLogged().getRemember_token());
    }

    @Override
    public void getOffers(OffersResponseModel offersResponseModel) {
        if (offersResponseModel.getSuccess().equalsIgnoreCase("success")) {
            OffersAdapter adapterOffer = new OffersAdapter(offersResponseModel.getData(), getContext());
            imagesRV.setAdapter(adapterOffer);

            PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
            pagerSnapHelper.attachToRecyclerView(imagesRV);

            if (getView()!=null) {
                CircleIndicator2 indicator = getView().findViewById(R.id.indicator);
                indicator.attachToRecyclerView(imagesRV, pagerSnapHelper);
                adapterOffer.registerAdapterDataObserver(indicator.getAdapterDataObserver());
            }
        } else {
            Toast.makeText(context, "" + offersResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getRewards(final RewardsResponse rewardsResponse) {
        reward_recycler.setAdapter(new RewardAdapter(getActivity(), rewardsResponse.getData(), new OnItemClickListener() {
            @Override
            public void onItemClick(RewardsResponse.Datum item) {
                //TODO call new presenter
                mRewardsPresenter.replaceAward(new SharedPrefDueDate(getActivity()).getUserLogged().getRemember_token() , item.getAward_id());

            }
        }));
    }

    @Override
    public void getReplaceAwardsResponse(ReplaceAwardResponse replaceAwardResponse) {
        Toast.makeText(context, ""+replaceAwardResponse.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }
}
