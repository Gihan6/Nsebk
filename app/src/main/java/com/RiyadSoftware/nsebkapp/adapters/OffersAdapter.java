package com.RiyadSoftware.nsebkapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.RiyadSoftware.nsebkapp.data.models.HomeModel;
import com.RiyadSoftware.nsebkapp.data.models.OffersResponseModel;
import com.RiyadSoftware.nsebkapp.util.Constant;
import com.bumptech.glide.Glide;
import com.RiyadSoftware.nsebkapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.ViewHolder> {

    private List<HomeModel.Advertisement> contents;
    private Context mContext;

    public OffersAdapter(List<HomeModel.Advertisement> contents, Context mContext) {
        this.contents = contents;
        this.mContext = mContext;
    }






    @Override
    public int getItemCount() {
        return contents==null?0:contents.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;


        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_offers, parent, false);
        return new ViewHolder(view);


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        if (mContext != null) {
            if (contents.get(position).getImage() != null) {
               holder.image.setVisibility(View.VISIBLE);
               holder.videoView.setVisibility(View.GONE);
                Glide.with(mContext).load(
                        contents.get(position).getImage()).into(holder.image);
                holder.image.setScaleType(ImageView.ScaleType.FIT_XY);

            }
            else {
                holder.image.setVisibility(View.GONE);
                holder.videoView.setVisibility(View.VISIBLE);
                MediaController mediaController = new MediaController(mContext);
                mediaController.setAnchorView(holder.videoView);
                //Location of Media File
                Uri uri = Uri.parse(contents.get(position).getVideo());
                //Starting VideView By Setting MediaController and URI
                holder.videoView.setMediaController(mediaController);
                holder.videoView.setVideoURI(uri);
                holder.videoView.requestFocus();
                holder.videoView.start();
            }
        }

        }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.activityImage)ImageView image;
        @BindView(R.id.vdVw)
        VideoView videoView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}