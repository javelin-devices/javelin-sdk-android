package com.javelindevices.javelinsdk.model;

/**
 * Messages used to communicate between a client application and the remote javelin service on the
 * official app.
 */
public class BleMessage {
    public static final int MSG_CONNECT = 1;
    public static final int MSG_DISCONNECT = 2;
    public static final int MSG_READ_RSSI = 3;
    public static final int MSG_GET_BOND_STATE = 4;
    public static final int MSG_REGISTER_SENSOR = 5;
    public static final int MSG_UNREGISTER_SENSOR = 6;

    public static final int MSG_SENSOR_UPDATE = 7;
    public static final int MSG_SET_RATE = 8;
    public static final int MSG_SET_CONTROL = 9;
    public static final int MSG_SET_INTENSITY = 10;
    public static final int MSG_SET_TYPE = 11;
    public static final int MSG_SET_PULSE = 12;
    public static final int MSG_SET_SAMPLING_RATE = 13;
    public static final int MSG_SET_FULL_SCALE_RANGE = 14;
    public static final int MSG_SET_FILTER = 15;
    public static final int MSG_TRANSMIT_SN = 16;

    public static final int MSG_SENSOR_UNREGISTER_ALL = 50;
    public static final int MSG_SENSOR_RATE_CHANGED = 51;
    public static final int MSG_BOND_CREATE = 52;
    public static final int MSG_BOND_DESTROY = 53;

    // Miscellaneous sensor types that the end user doesn't need to use
    public static final int TYPE_LED = 203;
    public static final int TYPE_VIBRATOR = 204;
}
