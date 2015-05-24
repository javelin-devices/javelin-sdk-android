package com.javelindevices.javelinsdk.model;

/**
 * Messages used to communicate between a client application and the remote JavelinService
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
    public static final int MSG_SENSOR_UNREGISTER_ALL = 11;
    public static final int MSG_SENSOR_RATE_CHANGED = 12;
    public static final int MSG_BOND_CREATE = 13;
    public static final int MSG_BOND_DESTROY = 14;


    public static final long SCAN_PERIOD = 3000;
    public static final String KEY_MAC_ADDRESSES = "KEY_MAC_ADDRESSES";
    public static final String DEVICE_NAME = "J1";
}
