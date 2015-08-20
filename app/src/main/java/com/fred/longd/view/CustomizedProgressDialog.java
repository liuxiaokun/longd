package com.fred.longd.view;

/**
 * @author Fred Liu(liuxiaokun0410@gmail.com)
 * @version 1.0
 * @since 2015/8/20
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.fred.longd.R;

public class CustomizedProgressDialog extends ProgressDialog {

    private AnimationDrawable mAnimation;
    private Context mContext;
    private ImageView mImageView;
    private String mLoadingTip;
    private TextView mLoadingTv;

    public CustomizedProgressDialog(Context context, String content) {
        super(context);
        this.mContext = context;
        this.mLoadingTip = content;
        setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {

        mAnimation = (AnimationDrawable) mImageView.getDrawable();
        mLoadingTv.setText(mLoadingTip);

    }

    private void initView() {
        setContentView(R.layout.progress_dialog);
        mLoadingTv = (TextView) findViewById(R.id.loadingTv);
        mImageView = (ImageView) findViewById(R.id.loadingIv);
    }

    @Override
    public void show() {
        super.show();
        mAnimation.start();
    }
}

