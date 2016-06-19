package com.javelindevices.javelinsdk.model;

/**
 * Created by Aaron on 12/17/2014.
 */
public interface BleServiceListener {

    /**
     * <b>This method is called on separate from Main thread.</b>
     */
    public void onConnected();

    /**
     * <b>This method is called on separate from Main thread.</b>
     */
    public void onDisconnected();

    /**
     * <b>This method is called on separate from Main thread.</b>
     */
    public void onServiceDiscovered(String deviceAddress);

    /**
     * <b>This method is called on separate from Main thread.</b>
     */
    public void onDataAvailable(String serviceUuid, String characteristicUUid, String text, byte[] data, String deviceAddress);


    public void onReadRemoteRssi(int rssi);
}
