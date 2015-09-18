package com.fred.longd.mqtt;

import android.content.Context;
import android.util.Log;

import com.fred.longd.utils.AppUtil;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author fred
 * @version v1.0
 * @since 15/9/14 下午4:36
 */
public class MqttClient {

    public static final String SERVER_URI = "tcp://114.80.83.168:1993";
    private static String clientId = "55cee81b467f80df";

    private MqttAndroidClient mqttAndroidClient;
    private MqttConnectOptions mqttConnectOptions;

    private static MqttClient mqttClient;

    public static MqttClient getInstance(Context context) {


        if (null == mqttClient) {

            synchronized (MqttClient.class) {

                if (null == mqttClient) {

                    mqttClient = new MqttClient(context);
                }
            }
        }

        return mqttClient;
    }

    private MqttClient(Context context) {

        mqttAndroidClient = new MqttAndroidClient(context, SERVER_URI, clientId);

        mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(false);

        mqttAndroidClient.setCallback(new MyMqttCallback());
    }


    public boolean connect() {

        boolean connected;
        IMqttToken connect;

        try {
            connect = mqttAndroidClient.connect(mqttConnectOptions, null, new MyMqttActionListener());
            connect.waitForCompletion(60000L);
            connected = connect.isComplete();
        } catch (MqttException e) {
            connected = false;
        }

        Log.i("fred1", "connect:" + connected);
        return connected;
    }


    public boolean publish(String topic, String message) {


        boolean published = false;

        if (mqttAndroidClient.isConnected()) {

            MqttMessage mqttMessage = new MqttMessage(message.getBytes());

            try {
                IMqttDeliveryToken token = mqttAndroidClient.publish(topic, mqttMessage, null, new MyMqttActionListener());
                token.waitForCompletion(60000L);
                published = token.isComplete();
            } catch (MqttException e) {
                published = false;
            }
        }

        return published;
    }
}