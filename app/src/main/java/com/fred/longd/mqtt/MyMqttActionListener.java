package com.fred.longd.mqtt;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

/**
 * @author fred
 * @version v1.0
 * @since 15/9/14 下午5:30
 */
public class MyMqttActionListener implements IMqttActionListener {

    @Override
    public void onSuccess(IMqttToken iMqttToken) {
        Log.i("fred1", "MyMqttActionListener - onSuccess");
    }

    @Override
    public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
        Log.i("fred1", "MyMqttActionListener - onFailure");
    }
}
