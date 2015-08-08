package com.javelindevices.javelinsdk.model;


import com.javelindevices.javelinsdk.util.SensorManagerException;

/**
 * Contains the Javelin's API
 */
public abstract class ISensorManager {

    public interface JavelinEventListener {
        /**
         * Indicates whether there was an update from a sensor
         *
         * @param sensorType the sensor type described in {@link com.javelindevices.javelinsdk.model.ISensor}
         * @param values array containing the data for that sensor
         */
        public void onSensorChanged(int sensorType, float[] values);

        /**
         * Called when a connection has been established to the javelin device / service
         */
        public void onSensorManagerConnected();

        /**
         * Called when the application has disconnected from the javelin device / service
         */
        public void onSensorManagerDisconnected();

        /**
         * @param rssi The connection strength between the javelin device and the central device.
         */
        public void onReadRemoteRssi(int rssi);
    }

    protected JavelinEventListener listener;

    /**
     * Allows the android activity to receive sensor events.
     * To properly receive these events, the caller must extend the ISensorManager.SensorEventListener class
     *
     * @param listener the {@link com.javelindevices.javelinsdk.model.ISensorManager.JavelinEventListener}
     */
    public void setListener(JavelinEventListener listener) {
        this.listener = listener;
    }

    /**
     * Connects to the javelin device. If this is called without setting a listener then no events
     * will be received.
     *
     * @throws SensorManagerException
     */
    public abstract void enable() throws SensorManagerException;

    /**
     * Disconnects the javelin device. This should always be called when not planning to use anymore
     * javelin data.
     */
    public abstract void disable();

    /**
     * Reads the signal strength from the javelin device to the central device.
     */
    public abstract void readRssi();

    /**
     * Enable notifications from a sensor.
     * @param sensorType The sensor to enable.
     */
    public abstract void registerListener(JavelinEventListener listener, int sensorType);

    /**
     * Disables notifications and data from a sensor for the given listener
     * If other listeners are still registered to the sensor, they'll still receive data.
     *
     * A sensor should be unregistered when no more data
     * is needed from it in order to save battery life on the javelin device.
     * @param sensorType a type defined in {@link com.javelindevices.javelinsdk.model.ISensor}
     */
    public abstract void unregisterListener(JavelinEventListener listener, int sensorType);

    /**
     * Removes all listeners, stopping sensor notifications.
     *
     * This does not disconnect the application from the javelin service.
     */
    public abstract void unregisterAll();

    /**
     * Bonds the application to the javelin device.
     * @return boolean indicating whether bonding was successful.
     */
    public abstract boolean createBond();

    /**
     * Removes the bond from the application to the javelin device.
     * @return
     */
    public abstract boolean removeBond();

    /**
     * LED on/off switch. Will cancel any blinking taking place, if any.
     * @param enable
     */
    public abstract void ledEnable(boolean enable);

    /**
     * Sets the LED intensity on a scale of 1 - 100, 100 being the strongest intensity.
     * @param intensity the intensity of the LED from 1 to 100 inclusive.
     */
    public abstract void setLedIntensity(int intensity);

    /**
     * Sets the kind of blinking type to enable, such as LED_TYPE_SAWTOOTH, LED_TYPE_SOLID, LED_TYPE_TRIANGLE.
     * @param blinkType Integer constant describing the kind of blinking type to enable.
     *             This can be any of the LED_TYPE contants from {@link com.javelindevices.javelinsdk.model.ISensor}
     */
    public abstract void setLedBlinkType(int blinkType);

    /**
     * Sets the LED's blinking rate for the PULSE, TRIANGLE, and SAWTOOTH led types.
     * @param rate the pulsation rate of the LED.
     */
    public abstract void setLedBlinkRate(int rate);

    /**
     * Switches the component on and off for the set number of times at the current rate, pulsation type,
     * and intensity.
     * @param times how many times to blink the LED.
     */
    public abstract void ledBlink(int times);

    /**
     * Vibrator on / off switch. Will override any vibrations taking place.
     * @param enable
     */
    public abstract void vibrationEnable(boolean enable);

    /**
     * Sets the intensity of the vibration from 1 to 100 inclusive, 100 being the strongest intensity.
     *
     * @param intensity the intensity of vibration from 1 to 100 inclusive.
     */
    public abstract void setVibrationIntensity(int intensity);

    /**
     * Sets the vibration type to use
     * @param type one of the VIB_TYPE constants in {@link com.javelindevices.javelinsdk.model.ISensor}
     */
    public abstract void setVibrationType(int type);

    /**
     * Sets the vibration rate for the PULSE, TRIANGLE, and SAWTOOTH vibration types.
     * @param rate an integer indicating the number of vibrations per second.
     */
    public abstract void setVibrationRate(int rate);

    /**
     * Vibrates a set number of times if the SOLID, PULSE, TRIANGLE or SAWTOOTH vibration type is set.
     * If the SOLID vibration type is set, it will vibrate for that many seconds.
     *
     * @param times the number of times to vibrate
     */
    public abstract void vibrate(int times);


    public abstract void setAudioQuality(int quality);

    /**
     * Sets the sampling rate of the accelerometer and gyroscope
     * @param rate the sampling rate from TODO: write range
     */
    public abstract void setAcceleromGyroRate(int rate);

}

