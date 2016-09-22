package com.javelindevices.javelinsdk.model;

public class JavelinSensorEvent {
    /**
     * The sensor that generated the event.
     * Can be any of the types listed in {@link com.javelindevices.javelinsdk.model.ISensor}
     */
    public int sensor;

    /**
     * The length and contents of the array depends on which {@link #sensor} was returned.
     * The default sampling rate of the accelerometer and gyroscope is 100Hz.
     *
     * <p>
     * {@link com.javelindevices.javelinsdk.model.ISensor#TYPE_ACCELEROMETER}:
     * <p>
     * All values are in g's. 1g = 9.8m/s^2. They measure acceleration in X, Y and Z axis.
     * </p>
     * <p>
     * The values[] array length is 9 which stores 3 data values at the same point in time for each axis in the values[] array.
     * Example: {x1,y1,z1} are sampled at the same point in time, {x2,y2,z2} are sampled at the same point
     * in time, etc...
     * <p/>
     * <ul>
     * <li>values[0]: Acceleration on the x-axis x1</li>
     * <li>values[1]: Acceleration on the y-axis y1</li>
     * <li>values[2]: Acceleration on the z-axis z1</li>
     * <li>values[3]: Acceleration on the x-axis x2</li>
     * <li>values[4]: Acceleration on the y-axis y2</li>
     * <li>values[5]: Acceleration on the z-axis z2</li>
     * <li>values[6]: Acceleration on the x-axis x3</li>
     * <li>values[7]: Acceleration on the y-axis y3</li>
     * <li>values[8]: Acceleration on the z-axis z3</li>
     * </ul>
     * <br>
     * All values will be affected by gravity. One can isolate the force of gravity by applying a
     * high pass filter.
     * </p>
     *
     * {@link com.javelindevices.javelinsdk.model.ISensor#TYPE_GYROSCOPE}:
     * <p>
     * All values are in degrees/second. They measure the angular rate in X, Y and Z axis.
     * </p>
     * {@link com.javelindevices.javelinsdk.model.ISensor#TYPE_MAGNETIC_FIELD}:
     * <p>
     * All values are in micro-Tesla. They measure the magnetic field in X, Y and Z axis.
     * </p>
     *
     */
    public final float[] values;

    public JavelinSensorEvent(int sensor, float[] values) {
        this.sensor = sensor;
        this.values = values;
    }
}
