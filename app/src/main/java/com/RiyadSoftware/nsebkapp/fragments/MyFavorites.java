package com.RiyadSoftware.nsebkapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.Favourite.FavMvpView;
import com.RiyadSoftware.nsebkapp.Ui.Favourite.FavPresenter;
import com.RiyadSoftware.nsebkapp.adapters.MyFavoriteAdapter;
import com.RiyadSoftware.nsebkapp.base.BaseFragment;
import com.RiyadSoftware.nsebkapp.data.models.AddFavRequest;
import com.RiyadSoftware.nsebkapp.data.models.AddFavResponse;
import com.RiyadSoftware.nsebkapp.data.models.HomeRequest;
import com.RiyadSoftware.nsebkapp.data.models.MyFavResponse;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavorites extends BaseFragment implements FavMvpView, MyFavoriteAdapter.FavActions {

    @BindView(R.id.favorite_recycler)
    RecyclerView favorite_recycler;
    MyFavoriteAdapter adapter;
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.empty_fav)
    ViewGroup no_data_holder;

    @Inject
    FavPresenter favPresenter;

    SharedPrefDueDate pref;

    List<MyFavResponse.Datum> dataList;
    int mPosition;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_favorites, container, false);
        ButterKnife.bind(this, root);

        pref = new SharedPrefDueDate(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        favorite_recycler.setLayoutManager(layoutManager);



        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activityComponent().inject(this);
        favPresenter.attachView(this);

        favPresenter.getMyFav(new HomeRequest(pref.getUserLogged().getRemember_token()));
    }


    @Override
    public void showListOfFav(MyFavResponse myFavResponse) {
        if (myFavResponse.getSuccess().equalsIgnoreCase("success")) {
            if (myFavResponse.getData() == null) {
showEmpty();            } else {
                dataList = myFavResponse.getData();
                adapter = new MyFavoriteAdapter(getActivity(),dataList);
                adapter.setFavActions(this);
                favorite_recycler.setAdapter(adapter);

            }
        } else {
            Toast.makeText(context, getString(R.string.error_in_data), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void DealAddedToFav(AddFavResponse addFavResponse) {
        dataList.remove(mPosition);
        adapter.notifyDataSetChanged();
        if (dataList.isEmpty())
            showEmpty();
    }

    @Override
    public void showEmpty() {
        no_data_holder.setVisibility(View.VISIBLE);
        favorite_recycler.setVisibility(View.GONE);

    }

    @Override
    public void showError() {

    }

    @Override
    public void deleteFav(int position , int delaID) {
        mPosition = position;
        favPresenter.addToFav(new AddFavRequest(pref.getUserLogged().getRemember_token(), String.valueOf(delaID)));
    }
}
