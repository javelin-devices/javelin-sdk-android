package util;


public class JavelinControl { //TODO: Could be renamed to Javelin constants

    private final int NULL_CMD = 0;
    private static final int DEFAULT_CMD = 1;

    public static final String CONTROL_CHARACTERISTIC = "00000009-dead-beef-0000-cafebabe0000";
    public static final String SERVICE_UUID = "00000000-dead-beef-0000-cafebabe0000";
    public static int NUM_BYTES = 20;

    public enum ControlComponent {
        LED_RATE_100MS,
        LED_INTENSITY_PERCENT,
        LED_REPEAT_COUNT,
        LED_CONTROL,
        VIBRATOR_RATE_100MS,
        VIBRATOR_INTENSITY_PERCENT,
        VIBRATOR_REPEAT_COUNT,
        VIB_CONTROL,
        AUDIO_ENERGY,
        AUDIO_SAMPLE,
        TEMP,
        BATTERY,
        SYSTEM_TIME_CONTROL;

        public int getIndex() {
            return this.ordinal();
        }
    }

    public enum ControlIMU {
        IMU_CONTROL,
        IMU_SAMPLE_RATE_L,
        IMU_SAMPLE_RATE_H,
        IMU_COMPASS_SAMPLE_RATE,
        IMU_ACC_FSR,
        IMU_GYRO_FSR,
        IMU_MOTION_THRESHOLD_AWAKE,
        IMU_LOW_POWER_SAMPLE_RATE;
        public int getIndex() {
            return this.ordinal();
        }
    }

    public enum ControlDMP {
        DMP_CTRL,
        DMP_INTERRUPT_MODE,
        DMP_FIFO_RATE_L,
        DMP_FIFO_RATE_H,
        DMP_FEATURE_TAP,
        DMP_FEATURE_ORIENT,
        DMP_FEATURE_QUAT,
        DMP_FEATURE_GYRO,
        DMP_FEATURE_ACC,
        MPL_CALIB_GYRO,
        MPL_TEMPCAL_GYRO,
        MPL_CAL_COMPASS,
        MPL_COMPASS_DIST,
        MPL_QUAT;
        public int getIndex() {
            return this.ordinal();
        }
    }

    public enum ControlPacketId {
        GENERAL,
        IMU,
        IMU_MP;
        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }


    public enum ControlResult {
        SUCCESS,
        INVALID_SETTING_LED,
        INVALID_SETTING_VIB,
        INVALID_SETTING_AUDIO_ENERGY,
        INVALID_SETTING_AUDIO_SAMPLE,
        INVALID_SETTING_IMU,
        INVALID_SETTING_ACC,
        INVALID_SETTING_GYRO,
        INVALID_SETTING_MAG,
        INVALID_SETTING_TEMP,
        INVALID_SETTING_BATTERY;

        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }

    public enum LedControl {
        LED_OFF,
        LED_SOLID,
        LED_PULSE,
        LED_TRIANGLE,
        LED_SAW_TOOTH;

        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }

    public enum VibrationControl {
        VIB_OFF,
        VIB_SOLID,
        VIB_PULSE,
        VIB_TRIANGLE,
        VIB_SAW_TOOTH;

        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }

    public enum AudioStreamControl {
        AUDIO_STREAM_OFF,
        AUDIO_STREAM_MED_QUALITY,
        AUDIO_STREAM_HIGH_QUALITY;

        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }

    public enum ImuControl {
        OFF,
        ON;

        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }

    public enum AccControl {
        OFF,
        ON;

        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }

    public enum GyroControl {
        OFF,
        ON;

        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }

    public enum MagControl {
        OFF,
        ON;

        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }

    public enum TempControl {
        OFF,
        ON;

        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }

    public enum BatteryControl {
        OFF,
        ON;

        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }

    public enum SystemTimeControl {
        SYSTEM_TIMER_STOP,
        SYSTEM_TIMER_START;

        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }


    public enum IMU_CONTROL_VALUE {
        IMU_THRESH_DET,
        IMU_ACC,
        IMU_GYRO,
        IMU_MAG,
        IMU_ACC_GYRO,
        IMU_ACC_COMP,
        IMU_GYRO_MAG,
        IMU_ACC_GYRO_MAG,
        IMU_LP_ACCEL,
        IMU_TEST;
        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }



    public enum ACC_FSR_VALUE {
        ACC_FSR_2,
        ACC_FSR_4,
        ACC_FSR_8,
        ACC_FSR_16;
        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }


    public enum GYRO_FSR_VALUE {
        GYRO_FSR_250,
        GYRO_FSR_500,
        GYRO_FSR_1000,
        GYRO_FSR_2000;

        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }




    public enum LOW_POWER_ACCEL_RATE_VALUE {
        LP_ACC_SAMPLE_RATE_1_25,
        LP_ACC_SAMPLE_RATE_2_5,
        LP_ACC_SAMPLE_RATE_5,
        LP_ACC_SAMPLE_RATE_10,
        LP_ACC_SAMPLE_RATE_20,
        LP_ACC_SAMPLE_RATE_40,
        LP_ACC_SAMPLE_RATE_80,
        LP_ACC_SAMPLE_RATE_160,
        LP_ACC_SAMPLE_RATE_320,
        LP_ACC_SAMPLE_RATE_640;

        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }




    public enum DMP_CONTROL_VALUE {
        OFF,
        ON;
        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }



    public enum DMP_INTERRUPT_MODE_VALUE {
        DMP_INT_MODE_CONT,
        DMP_INT_MODE_GEST;

        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }



    public enum DMP_FEATURE_TAP_VALUE {
        OFF,
        ON;

        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }



    public enum DMP_FEATURE_ORIENT_VALUE {
        OFF,
        ON;

        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }


    public enum DMP_FEATURE_QUAT_VALUE {
        OFF,
        QUAT_3X,
        QUAT_6X;
        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }


    public enum DMP_FEATURE_GYRO_CONTROL_VALUE {
        DMP_GYRO_OFF,
        DMP_GYRO_RAW,
        DMP_GYRO_CAL;

        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }


    public enum DMP_FEATURE_ACC_CONTROL_VALUE {
        SYSTEM_TIMER_STOP,
        SYSTEM_TIMER_START;

        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }




}
