package com.fred.longd.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fred.longd.R;

/*
* Copyright (C) 2015 Pierry Borges
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
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
