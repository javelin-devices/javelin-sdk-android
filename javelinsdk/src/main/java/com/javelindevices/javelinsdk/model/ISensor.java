package com.javelindevices.javelinsdk.model;

import android.hardware.Sensor;

import com.javelindevices.javelinsdk.util.JavelinControl;

public interface ISensor {

    public static final int TYPE_ACCELEROMETER = Sensor.TYPE_ACCELEROMETER;

    public static final int TYPE_MAGNETIC_FIELD = Sensor.TYPE_MAGNETIC_FIELD;

    public static final int TYPE_GYROSCOPE = Sensor.TYPE_GYROSCOPE;

    public static final int TYPE_TEMPERATURE = Sensor.TYPE_AMBIENT_TEMPERATURE;

    public static final int TYPE_BATTERY = 200;

    public static final int TYPE_AUDIO_STREAM = 201;

    public static final int TYPE_AUDIO_ENERGY = 202;



    public static final int TYPE_QUATERNION = 205;


}