package com.fred.longd.application;

import android.app.Application;
import android.util.Log;

import com.fred.longd.mqtt.MqttClient;
import com.fred.longd.utils.NetworkUtil;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        boolean connected = NetworkUtil.isConnected(this);
        Log.i("fred1", "network:" + connected);

        MqttClient.getInstance(this).connect();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
