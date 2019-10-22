package com.RiyadSoftware.nsebkapp.activities;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.Packages.PackagesPresenter;
import com.RiyadSoftware.nsebkapp.Ui.Packages.PackagesSubView;
import com.RiyadSoftware.nsebkapp.base.BaseActivity;
import com.RiyadSoftware.nsebkapp.data.models.ChargeRequest;
import com.RiyadSoftware.nsebkapp.data.models.ChargeResponse;
import com.RiyadSoftware.nsebkapp.data.models.OffersResponseModel;
import com.RiyadSoftware.nsebkapp.data.models.PackageResponse;
import com.RiyadSoftware.nsebkapp.data.models.coupon.CouponResponse;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Shipping_buy extends BaseActivity implements PackagesSubView {

    @BindView(R.id.package_name)
    TextView package_name;
    @BindView(R.id.kassaem_label)
    TextView kassaem_label;
    @BindView(R.id.points_label)
    TextView points_label;
    @BindView(R.id.buy_vg)
    ViewGroup buy_vg;

    @BindView(R.id.sarRB)
    RadioButton rb_ryal;
    @BindView(R.id.dollarRB)
    RadioButton rb_dollar;

    //---------

    @BindView(R.id.btn_tatbiq)
    Button btn_tatbiq;
    @BindView(R.id.et_sumOfBill)
    EditText et_sumOfBill;
    @BindView(R.id.et_numOFCredit)
    EditText et_CreditNum;
    @BindView(R.id.et_validateNum)
    EditText et_validateNum;
    @BindView(R.id.et_finishDate)
    EditText et_finisDate;
    @BindView(R.id.et_owner)
    EditText et_ownerOFCredit;

    //---------


    EditText editCoupon;
    Button applyCouponBtn;

    int dollarValue;
    int ryalValue;

    @Inject
    PackagesPresenter mPackagesPresenter;


    SharedPrefDueDate pref;
    int package_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_buy);
        ButterKnife.bind(this);

        activityComponent().inject(this);
        mPackagesPresenter.attachView(this);

        pref = new SharedPrefDueDate(this);

        applyCouponBtn = findViewById(R.id.button_apply_coupon);
        editCoupon = findViewById(R.id.edit_enter_coupon);

        final PackageResponse.Datum model = (PackageResponse.Datum) getIntent().getSerializableExtra("data");
        package_name.setText("" + model.getTitle());
        points_label.setText("" + model.getPoints());
        kassaem_label.setText("" + model.getCoupons());
        et_sumOfBill.setText(""+model.getCost());
        rb_ryal.setChecked(true);
        ryalValue=Integer.parseInt(model.getCost());

        package_id = model.getId();

        applyCouponBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = null;
                if (pref.getUserLogged().getRemember_token() != null)
                    token = pref.getUserLogged().getRemember_token();

                if (editCoupon.getText().length() != 0)
                    mPackagesPresenter.getCouponResultPresenter(token, editCoupon.getText().toString(), package_id);

            }
        });
        buy_vg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPackagesPresenter.Charge(new ChargeRequest(new
                        SharedPrefDueDate(Shipping_buy.this).getUserLogged().getRemember_token(),
                        model.getId()));
            }
        });

        rb_ryal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_sumOfBill.setText(String.valueOf(ryalValue));

            }
        });
        rb_dollar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dollarValue= (int) (ryalValue*0.266667);
                et_sumOfBill.setText(String.valueOf(dollarValue));


            }
        });

    }

    @OnClick(R.id.back_shipping_buy)
    void back_shipping_buy() {
        finish();
    }


    @Override
    public void getOffers(OffersResponseModel offersResponseModel) {

    }

    @Override
    public void getPackages(PackageResponse packageResponse) {

    }

    @Override
    public void afterBuy(ChargeResponse packageResponse) {
        if (packageResponse.getSuccess().equalsIgnoreCase("success")) {
            SharedPrefDueDate pref = new SharedPrefDueDate(this);
            pref.setUserPointsFromPk(packageResponse.getPointsCount()+"");
            pref.setUserCouponsFromPk(packageResponse.getTicketsCount()+"");

            showDialog();
        }
    }

    @Override
    public void getCouponResult(CouponResponse couponResponse) {
        if (couponResponse.getErrors() == null) {
            if (couponResponse.getData().getAdd_points() != null) {
                int pointsAfterCoupon = (Integer.parseInt(points_label.getText().toString()) + Integer.parseInt(couponResponse.getData().getAdd_points().toString()));
                points_label.setText("" + pointsAfterCoupon);

            }
            if (couponResponse.getData().getAdd_tickets() != null) {
                int ticketsAfterCoupon = (Integer.parseInt(kassaem_label.getText().toString()) + Integer.parseInt(couponResponse.getData().getAdd_tickets().toString()));
                kassaem_label.setText("" + ticketsAfterCoupon);
            }

//            if (couponResponse.getData().getn)


        }

    }

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
                dialog.dismiss();
                finish();
            }
        });

        dialog_txt.setText(getString(R.string.part_done));

        dialog.show();
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }
}
