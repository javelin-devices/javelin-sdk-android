package com.javelindevices.javelinsdk.model;

import android.hardware.Sensor;

import com.javelindevices.javelinsdk.util.JavelinControl;

/**
 * Created by Aaron on 12/18/2014.
 */
public interface ISensor {
    /**
     * A constant describing an accelerometer sensor type. See
     * {@link android.hardware.SensorEvent#values SensorEvent.values} for more
     * details.
     */
    public static final int TYPE_ACCELEROMETER = Sensor.TYPE_ACCELEROMETER;

    /**
     * A constant describing a magnetic field sensor type. See
     * {@link android.hardware.SensorEvent#values SensorEvent.values} for more
     * details.
     */
    public static final int TYPE_MAGNETIC_FIELD = Sensor.TYPE_MAGNETIC_FIELD;

    /** A constant describing a gyroscope sensor type */
    public static final int TYPE_GYROSCOPE = Sensor.TYPE_GYROSCOPE;

    public static final int TYPE_TEMPERATURE = Sensor.TYPE_AMBIENT_TEMPERATURE;

    public static final int TYPE_BATTERY = 200;

    public static final int TYPE_AUDIO_STREAM = 201;

    public static final int TYPE_AUDIO_ENERGY = 202;

    public static final int TYPE_LED = 203;

    public static final int TYPE_VIBRATOR = 204;

    public static final int TYPE_QUATERNION = 205;

    public static final int VIB_TYPE_SINE = JavelinControl.VibrationControl.VIB_PULSE.getIndex();
    public static final int VIB_TYPE_SAWTOOTH = JavelinControl.VibrationControl.VIB_SAW_TOOTH.getIndex();
    public static final int VIB_TYPE_SOLID = JavelinControl.VibrationControl.VIB_SOLID.getIndex();
    public static final int VIB_TYPE_TRIANGLE = JavelinControl.VibrationControl.VIB_TRIANGLE.getIndex();

    public static final int LED_TYPE_SINE = JavelinControl.LedControl.LED_PULSE.getIndex();
    public static final int LED_TYPE_SAWTOOTH = JavelinControl.LedControl.LED_SAW_TOOTH.getIndex();
    public static final int LED_TYPE_SOLID = JavelinControl.LedControl.LED_SOLID.getIndex();
    public static final int LED_TYPE_TRIANGLE = JavelinControl.LedControl.LED_TRIANGLE.getIndex();

    public static final int LED_TOGGLE_SINGLE = JavelinControl.LedControl.LED_PULSE.getIndex();
    public static final int LED_TOGGLE_REPEAT_START = JavelinControl.LedControl.LED_SAW_TOOTH.getIndex();
    public static final int VIB_TOGGLE_SINGLE = JavelinControl.VibrationControl.VIB_PULSE.getIndex();
    public static final int VIB_TOGGLE_REPEAT_START = JavelinControl.VibrationControl.VIB_SAW_TOOTH.getIndex();
    public static final int AUDIO_QUALITY_MEDIUM = JavelinControl.AudioStreamControl.AUDIO_STREAM_MED_QUALITY.getIndex();
    public static final int AUDIO_QUALITY_HIGH = JavelinControl.AudioStreamControl.AUDIO_STREAM_HIGH_QUALITY.getIndex();
}