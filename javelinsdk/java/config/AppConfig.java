package config;

/**
 * Created by Aaron on 12/17/2014.
 */
public interface AppConfig {
    public static final boolean DEBUG = false;
    /**
     * Indicates whether BleService is used as remote service.<br/>
     * It would send broadcast messages in this case.
     */
    public static final boolean REMOTE_BLE_SERVICE = false;

    public static final boolean ENABLE_RECORD_SERVICE = false;
    /**
     * Enables magnet sensor to be used while sensor fusion calculation.
     */
    public static final boolean SENSOR_FUSION_USE_MAGNET_SENSOR = false;

    public static final String BLE_DEVICE_NAME = "Javelin";
}