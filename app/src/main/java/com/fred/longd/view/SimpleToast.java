package com.fred.longd.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fred.longd.R;

public class SimpleToast {

    private static LayoutInflater mInflater;
    private static Toast mToast;
    private static View mView;

    public static void longShow(Context context, String msg) {
        initToast(context, msg);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.show();
    }

    public static void shortShow(Context context, String msg) {
        initToast(context, msg);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }

    private static void initToast(Context context, String message) {

        mInflater = LayoutInflater.from(context);
        mView = mInflater.inflate(R.layout.toast, null);
        TextView toastMessage = (TextView) mView.findViewById(R.id.toast_message);
        toastMessage.setText(message);
        mToast = new Toast(context);
        mToast.setView(mView);
        mToast.setGravity(Gravity.CENTER, 0, 200);
    }
}
