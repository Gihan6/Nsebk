//package com.RiyadSoftware.nsebkapp.base;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.DialogFragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.widget.TextView;
//
//
//import com.RiyadSoftware.nsebkapp.R;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
//
///**
// * Created by clicklabs148 on 11/6/15.
// */
//@SuppressLint("ValidFragment")
//public class ConfirmationDialog extends DialogFragment {
//    private Context context;
//    String ActionName, message;
//    ProcessResult processResult;
//
//    @BindView(R.id.btn_no)
//    TextView btnNo;
//
//    @BindView(R.id.btn_yes)
//    TextView btnYes;
//
//    @BindView(R.id.txt_message)
//    TextView txt_message;
//
//
//    public ConfirmationDialog(String actionName, String message, ProcessResult processResult) {
//        ActionName = actionName;
//        this.processResult = processResult;
//        this.message = message;
//    }
//
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        this.context = context;
//    }
//
//    public static ConfirmationDialog newInstance(String actionName, String message, ProcessResult processResult) {
//        return new ConfirmationDialog(actionName, message, processResult);
//    }
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setCancelable(true);
//    }
//
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);
//        View view = inflater.inflate(R.layout.custom_popup_dialog, container);
//
//
//        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme);
//
//
//        return view;
//
//    }
//
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        ButterKnife.bind(this,view);
//        txt_message.setText(message);
//    }
//
//    @OnClick(R.id.btn_yes)
//    public void yesClicked() {
//        if (processResult != null)
//            processResult.processResult(ActionName, Constants.ACTIIN_YES);
//
//        dismiss();
//    }
//
//    @OnClick(R.id.btn_no)
//    public void noClicked() {
////        if (processResult != null)
////            processResult.processResult(ActionName, Constants.ACTIIN_NO);
//
//        dismiss();
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//
////        Window window = getDialog().getWindow();
////        window.setBackgroundDrawableResource(android.R.color.transparent);
//
//    }
//
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//    }
//
//}