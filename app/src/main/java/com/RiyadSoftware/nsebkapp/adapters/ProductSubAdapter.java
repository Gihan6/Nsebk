package com.RiyadSoftware.nsebkapp.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.activities.Buy;
import com.RiyadSoftware.nsebkapp.app.MySingleton;
import com.RiyadSoftware.nsebkapp.data.models.DealsResponseModel;
import com.RiyadSoftware.nsebkapp.util.Constant;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductSubAdapter extends RecyclerView.Adapter<ProductSubAdapter.ViewHolder> {


    private Context context;
    //the data
    private List<DealsResponseModel.Datum> data;
    private SharedPrefDueDate pref;


    public ProductSubAdapter(Context context, List<DealsResponseModel.Datum> data) {
        this.context = context;
        this.data = data;
        pref = new SharedPrefDueDate(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_product, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int postion ) {




        if (context != null)
            Glide.with(context).load(Constant.baseImage+data.get(postion).getProduct_image()).into(holder.productIV);

        holder.titleTV.setText(data.get(postion).getTitle());
//        holder.priceTV.setText(data.get(postion).getSalary()+" ريال ");
//        holder.pointsTV.setText(data.get(postion).getMin_points()+" نقاط ");
//        holder.timeTV.setText(data.get(postion).getBuy_offer_date()+"  "+data.get(postion).getBuy_offer_time());


        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Buy.class);
                intent.putExtra("data",data.get(postion).getId());
                context.startActivity(intent);


            }
        });


//        holder.likeIV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(context,"addded like ",Toast.LENGTH_LONG).show();
//                sendDataTOServer(postion);
//                holder.likeIV.setImageDrawable(context.getResources().getDrawable(R.drawable.cards_heart));
//            }
//        });




    }

    @Override
    public int getItemCount() {
        return data==null?0: data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.productParent)LinearLayout parent;
//        @BindView(R.id.priceTV)TextView priceTV;
//        @BindView(R.id.pointsTV)TextView pointsTV;
        @BindView(R.id.productIV)ImageView productIV;
        @BindView(R.id.titleTV)TextView titleTV;
//        @BindView(R.id.timeTV)TextView timeTV;
//        @BindView(R.id.likeIV)ImageView likeIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }



    /**
     * here to send  the data from the server
     */

    private void sendDataTOServer(int postion) {


//        loading.setVisibility(View.VISIBLE);
        String url = Constant.baseUrl + "/favourite";


        // here to get the county id and the birth date from the piker and the gender
        JSONObject object = new JSONObject();
        try {
            object.put("user_id", pref.getUserLogged().getId());
            object.put("product_id", data.get(postion).getId());
            object.put("product_time",System.currentTimeMillis());

        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.d("google", "this is the object  " + object.toString());

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url,
                object, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("google", "response ----->  " + response.toString());
//                loading.setVisibility(View.GONE);
                try {
                    extractJson(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("google", "error ----->   " + error.toString());
//                loading.setVisibility(View.GONE);
//                Toast.makeText(context, getString(R.string.wrong_login), Toast.LENGTH_LONG).show();
            }
        }) {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        //this comment to make the request time  is longer
//        jsObjRequest.setRetryPolicy(new RetryPolicy() {
//            @Override
//            public int getCurrentTimeout() {
//                return 50000;
//            }
//
//            @Override
//            public int getCurrentRetryCount() {
//                return 50000;
//            }
//
//            @Override
//            public void retry(VolleyError error) throws VolleyError {
//
//            }
//        });
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(context).addToRequestQueue(jsObjRequest);
    }

    /*
     * here to extract the data from the server and get it
     *
     * @param response the json object is returned from the server
     * @throws JSONException the json exception
     */
    @SuppressLint("SetTextI18n")
    private void extractJson(JSONObject response) throws JSONException {

        String status = response.getString("status");
        if (status.equals("true")) {
            Toast.makeText(context,context.getString(R.string.added),
                    Toast.LENGTH_LONG).show();
        }

    }
}
