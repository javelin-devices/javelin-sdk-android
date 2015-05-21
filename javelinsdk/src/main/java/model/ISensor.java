package model;

import android.hardware.Sensor;

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


    public float getMaxRange();

    public int getType();
}