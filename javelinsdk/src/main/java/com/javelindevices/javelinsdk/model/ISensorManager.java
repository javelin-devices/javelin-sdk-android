package com.javelindevices.javelinsdk.model;


import com.javelindevices.javelinsdk.SensorManagerException;

/**
 * Contains the Javelin's API
 */
public abstract class ISensorManager {

    public interface JavelinEventListener {

        /**
         * Indicates there was a sensor event
         * @param event  The {@link JavelinSensorEvent}
         * @see @{link ISensor}
         *
         */
        public void onSensorChanged(JavelinSensorEvent event);

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
     * Reads the signal strength from the javelin device to the central device. The result will
     * be shown on the onReadRemoteRssi() callback in {@link com.javelindevices.javelinsdk.model.ISensorManager.JavelinEventListener}.
     */
    public abstract void readRssi();

    /**
     * Enable notifications from a sensor.
     * A {@link com.javelindevices.javelinsdk.JavelinSensorManager JavelinSensorManager} will remember all registered listeners
     *
     * @param sensorType The sensor to enable.
     */
    public abstract void registerListener(JavelinEventListener listener, int sensorType);

    /**
     * Disables notifications and data from a sensor for the given listener, if it is already subscribed.
     * If other listeners are still registered to the sensor, they'll still receive data.
     * When there are no listeners subscribed to a sensor, the javelin device will turn that sensor off
     * to save battery.
     *
     * A sensor listener should be unregistered when no more data is needed from it for less battery consumption.
     * @param sensorType a type defined in {@link com.javelindevices.javelinsdk.model.ISensor}
     */
    public abstract void unregisterListener(JavelinEventListener listener, int sensorType);

    /**
     * Removes all listeners, stopping sensor updates.
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
     * @return boolean indicating whether the removal was successful.
     */
    public abstract boolean removeBond();

    /**
     * LED on/off switch. Will cancel any blinking taking place, if any.
     * <p>
     * Must set {@link #setLedBlinkType(int) setLedBlinkType(int blinkType)} before calling ledEnable(boolean)
     * </p>
     * @param enable boolean
     */
    public abstract void ledEnable(boolean enable);

    /**
     * Sets the LED intensity on a scale of 1 - 100, 100 being the strongest intensity.
     * @param intensity  the intensity of the LED from 1 to 100 inclusive.
     */
    public abstract void setLedIntensity(int intensity);

    /**
     * Sets the kind of blinking type to enable when {@link #ledEnable(boolean) ledEnable(true)} is called
     * @param blinkType Integer constant describing the kind of blinking type to enable.
     *             This can be any of the LED_TYPE contants from {@link com.javelindevices.javelinsdk.model.JavelinSettings}
     */
    public abstract void setLedBlinkType(int blinkType);

    /**
     * Sets the LED's blinking rate for the PULSE, TRIANGLE, and SAWTOOTH LED types.
     * @param rate the blinking rate, in .1Hz values. A value of 10 will make the LED blink once per second.
     */
    public abstract void setLedBlinkRate(int rate);

    /**
     * Sets how many times the LED goes on and off for the specified number of times at the current
     * rate, pulsation type, and intensity.
     *
     * Sending in the maximum value (255) will make it blink indefinitely until the LED is disabled
     * or a disconnection event happens.
     *
     * @param times how many times to blink the LED when calling the {@link #ledEnable(boolean) ledEnable}
     *              method. Allowed values are [1,255].
     */
    public abstract void setLedBlinkNumber(int times);

    /**
     * Vibrator on / off switch. This will override any vibrations taking place.
     *<p>
     * Must set {@link #setVibrationType(int) setVibrationType(int type)} before calling vibrationEnable(boolean)
     *</p>
     * @param enable boolean
     */
    public abstract void vibrationEnable(boolean enable);

    /**
     * @param intensity  the intensity of vibration from 1 to 100 inclusive.
     */
    public abstract void setVibrationIntensity(int intensity);

    /**
     * Sets the vibration type to use
     * @param type  one of the VIB_TYPE constants in {@link com.javelindevices.javelinsdk.model.ISensor}
     */
    public abstract void setVibrationType(int type);

    /**
     * Sets the vibration rate for the PULSE, TRIANGLE, and SAWTOOTH vibration types.
     * @param rate  an integer indicating the number of vibrations per 1/10th of a second (.1Hz).
     *             A value of 10 will make it vibrate once per second.
     */
    public abstract void setVibrationRate(int rate);

    /**
     * Sets the number of times to vibrate if the SOLID, PULSE, TRIANGLE or SAWTOOTH vibration type is set.
     * If the SOLID vibration type is set, it will vibrate for that many seconds, otherwise, it will
     * use the default rate of vibration or one that has been set.
     *
     * Sending in the maximum value (255) will make it blink indefinitely until the vibrator is disabled
     * or a disconnection event happens.
     *
     * @param times  the number of times to vibrate
     */
    public abstract void setVibrationRepeatNumber(int times);



    public abstract void setAudioQuality(int quality);

    /**
     * Sets the sampling rate of the accelerometer and gyroscope to the value closest to the given
     * parameter. The default rate, 100Hz, must be divisible by the desired sample rate -- otherwise it attempts
     * to approximate it.
     *
     * @param rate the sampling rate to set the gyroscope and accelerometer to.
     */
    public abstract void setAcceleromGyroRate(int rate);


    public abstract void setAccelerometerFullScaleRange(int range);

    public abstract void setGyroscopeFullScaleRange(int range);

    /**
     * Set the divisor used to divide the default sample rate up.
     * The default sample rate is 100Hz
     *
     * @param divisor
     */
    public abstract void setAcceleromGyroSampleRateDivisor(int divisor);
}

