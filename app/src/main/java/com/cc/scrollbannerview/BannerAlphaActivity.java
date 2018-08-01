package com.cc.scrollbannerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class BannerAlphaActivity extends AppCompatActivity {

    private ScrollView mScroll;
    private View mBanner;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_alpha);
        initView();
    }

    private void initView() {
        mScroll = (ScrollView) findViewById(R.id.scroll);
        mScroll.setBackground(getResources().getDrawable(R.drawable.banner));  // 设置Banner
        mBanner = (View) findViewById(R.id.banner);

        mTv = findViewById(R.id.tv);
        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BannerAlphaActivity.this, BannerViewActivity.class));
            }
        });
    }

}
