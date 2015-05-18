package model;


import com.javelindevices.javelin_sdk.SensorManagerException;

/**
 * Created by Aaron on 12/18/2014.
 */
public abstract class ISensorManager {

    public interface SensorEventListener {
        /**
         * Indicates whether there was an update from a sensor
         *
         * @param sensorType accelerometer, gyroscope, magnetometer, or temperature sensor
         * @param values array of data for that sensor
         */
        public void onSensorChanged(int sensorType, float[] values);

        public void onSensorManagerConnected();

        public void onSensorManagerDisconnected();

        public void onReadRemoteRssi(int rssi);

        /**
         * Indicates the current bonding state and the previous state of the Ble device.
         *
         * //@param state any of BluetoothDevice.BOND_BONDED,
         *              BluetoothDevice.BOND_BONDING,
         *              BluetoothDevice.BOND_NONE,
         *              BluetoothDevice.BOND_ERROR
         */
        //public void onBondingChanged(int state, int previousState);

        //public void onBondCreated();

        //public void onBondRemoved();

        //TODO: add onBondConnecting();
    }

    protected SensorEventListener listener;

    public void setListener(SensorEventListener listener) {
        this.listener = listener;
    }

    public abstract void enable() throws SensorManagerException;

    public abstract void disable();

    public abstract void readRssi();

    public abstract void registerSensor(int sensorType);

    public abstract void unregisterSensor(int sensorType);

    public abstract void unregisterAll();

    public abstract boolean createBond();

    public abstract boolean removeBond();

    public abstract void ledEnable(boolean enable);

    public abstract void ledStopRepeat();

    public abstract void ledStartBlinking(int setting);

    public abstract void vibrationEnable(boolean enable);

    public abstract void vibrationStopRepeat();

    public abstract void vibrationStartRepeat(int setting);

    public abstract short[] getAudio();

    public abstract void setAudioQuality(int quality);

    public abstract void startDFU();

}

