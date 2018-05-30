package com.cc.scrollbannerview;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BannerViewActivity extends AppCompatActivity {

    private NestedScrollView mScroll;
    private ScrollBannerView mBanner;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_view);
        initView();
    }

    private void initView() {
        mScroll = (NestedScrollView) findViewById(R.id.scroll);
        mScroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (mBanner != null){
                    mBanner.notifyLayoutChange();
                }
            }
        });
        mBanner = (ScrollBannerView) findViewById(R.id.banner);
        mBanner.setBannerImageResource(R.drawable.banner);

        mTv = findViewById(R.id.tv);
        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BannerViewActivity.this, BannerAlphaActivity.class));
            }
        });
    }

}