package com.cc.scrollbannerview;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private NestedScrollView mScroll;
    private ScrollBannerView mBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }

}