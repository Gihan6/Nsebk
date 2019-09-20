package com.RiyadSoftware.nsebkapp.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.activities.AboutUs;
import com.RiyadSoftware.nsebkapp.activities.Conditions;
import com.RiyadSoftware.nsebkapp.activities.ContactUs;
import com.RiyadSoftware.nsebkapp.activities.LanguageActivity;
import com.RiyadSoftware.nsebkapp.activities.MainCategory;
import com.RiyadSoftware.nsebkapp.activities.MyAccount;
import com.RiyadSoftware.nsebkapp.activities.MyInterests;
import com.RiyadSoftware.nsebkapp.activities.Privacy;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavMenuAdapter extends RecyclerView.Adapter<NavMenuAdapter.ViewHolder> {


    private Context context;


    private DrawerLayout drawer_layout;
    private SharedPrefDueDate pref;

    private String[] menu_items;
    UserActions mUserActions;


    public interface UserActions {
        void userLogout();
    }

    public NavMenuAdapter(Context context, DrawerLayout drawer_layout, UserActions userActions) {
        this.context = context;
        this.mUserActions = userActions;
        this.drawer_layout = drawer_layout;
        this.pref = new SharedPrefDueDate(context);
        menu_items = new String[]{context.getString(R.string.navmain), context.getString(R.string.maincategory),
                context.getString(R.string.navinterests), context.getString(R.string.navaccount)
                , context.getString(R.string.navprivacy), context.getString(R.string.navterms),
                context.getString(R.string.navabout), context.getString(R.string.navcontact), /*context.getString(R.string.settings),*/ context.getString(R.string.navlogout)};
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.nav_menu_card, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        viewHolder.nav_menu_item_textview.setText(menu_items[i]);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0) {
                    drawer_layout.closeDrawer(GravityCompat.END);
                } else if (i == 1) {
                    context.startActivity(new Intent(context, MainCategory.class));
                } else if (i == 2) {
                    context.startActivity(new Intent(context, MyInterests.class));
                } else if (i == 3) {
                    context.startActivity(new Intent(context, MyAccount.class));
                } else if (i == 4) {
                    context.startActivity(new Intent(context, Privacy.class));
                } else if (i == 5) {
                    context.startActivity(new Intent(context, Conditions.class));
                } else if (i == 6) {
                    context.startActivity(new Intent(context, AboutUs.class));
                } else if (i == 7) {
                    context.startActivity(new Intent(context, ContactUs.class));
                } else if (i == 8) {
                    mUserActions.userLogout();
//                    Intent intent = new Intent(context, LanguageActivity.class);
//                    intent.putExtra("data", 1);
//                    context.startActivity(intent);
                } else if (i == 9) {
//                    Toast.makeText(context,"jiji",Toast.LENGTH_SHORT).show();
//                    mUserActions.userLogout();

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return menu_items.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nav_menu_item_textview)
        TextView nav_menu_item_textview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
