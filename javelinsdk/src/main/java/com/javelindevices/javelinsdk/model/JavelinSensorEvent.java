package com.javelindevices.javelinsdk.model;

public class JavelinSensorEvent {
    /**
     * The sensor that generated the event.
     * Can be any of the types listed in {@link com.javelindevices.javelinsdk.model.ISensor}
     */
    public int sensor;
    public String deviceAddress;

    /**
     * The length and contents of the array depends on which {@link #sensor} was returned.
     * <p>
     * {@link com.javelindevices.javelinsdk.model.ISensor#TYPE_ACCELEROMETER}:
     * <ul>
     * <li>values[0]: Acceleration on the x-axis</li>
     * <li>values[1]: Acceleration on the y-axis</li>
     * <li>values[2]: Acceleration on the z-axis</li>
     * </ul>
     * <br>
     * All values will be affected by gravity. One can isolate the force of gravity by applying a
     * high pass filter.
     * </p>
     *
     * {@link com.javelindevices.javelinsdk.model.ISensor#TYPE_GYROSCOPE}:
     * <p>
     * All values are in micro-Tesla. They measure the magnetic field in X, Y and Z axis.
     * </p>
     *
     */
    public final float[] values;

    public JavelinSensorEvent(int sensor, float[] values, String deviceAddress) {
        this.sensor = sensor;
        this.values = values;
        this.deviceAddress = deviceAddress;
    }
}
