package com.fred.longd.mqtt;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author fred
 * @version v1.0
 * @since 15/9/14 下午5:00
 */
public class MyMqttCallback implements MqttCallback {

    @Override
    public void connectionLost(Throwable throwable) {
        Log.i("fred1", "MyMqttCallback-connectionLost");
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {

        Log.i("fred1", "messageArrived --------topic:" + topic + ", mqttMessage:" + mqttMessage);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
