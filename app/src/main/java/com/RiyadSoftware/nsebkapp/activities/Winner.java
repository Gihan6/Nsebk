package com.RiyadSoftware.nsebkapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.adapters.WinnersAdadper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Winner extends AppCompatActivity {


    @BindView(R.id.winner_name)
    TextView winner_name;
    @BindView(R.id.top_five_empty)
    TextView top_five_empty;
    @BindView(R.id.winners)
    RecyclerView winners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            winner_name.setText(getIntent().getStringExtra("name"));
            List<String> winnerList = getIntent().getStringArrayListExtra("winners");
            if (winnerList == null || winnerList.isEmpty()) {
                top_five_empty.setVisibility(View.VISIBLE);
            } else {
                winners.setLayoutManager(new LinearLayoutManager(this));
                winners.setAdapter(new WinnersAdadper(this, winnerList));
            }
        }


    }

    @OnClick(R.id.back_winner)
    void back_winner() {
        finish();
    }

    @OnClick(R.id.winner_button)
    void winner_button() {
//        startActivity(new Intent(this,Payment.class));
    }
}
