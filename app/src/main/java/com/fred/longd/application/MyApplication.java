package com.fred.longd.application;

import android.app.Application;
import android.util.Log;

import com.fred.longd.mqtt.MqttClient;
import com.fred.longd.utils.AppUtil;
import com.fred.longd.utils.NetworkUtil;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.PrintWriter;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        boolean connected = NetworkUtil.isConnected(this);
        Log.i("fred1", "network:" + connected);
    }

    public void doConnect() throws MqttException {


        String topic = "MQTT Examples";
        String content = "Message from MqttPublishSample";
        int qos = 2;
//        String broker = "tcp://114.80.83.168:1993";
        String broker = "tcp://10.0.2.2:61616";
        String clientId = AppUtil.getClientId(this);
        System.out.println("client ID:" + clientId);
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            org.eclipse.paho.client.mqttv3.MqttClient sampleClient = new org.eclipse.paho.client.mqttv3.MqttClient(broker, clientId, persistence);
            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setCleanSession(true);
            System.out.println("Connecting to broker: " + broker);
            sampleClient.connect(mqttConnectOptions);
            System.out.println("Connected");
            System.out.println("Publishing message: " + content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            sampleClient.publish(topic, message);
            System.out.println("Message published");
            sampleClient.disconnect();
            System.out.println("Disconnected");
            System.exit(0);
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }


}
