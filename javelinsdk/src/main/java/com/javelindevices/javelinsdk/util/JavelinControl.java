package com.javelindevices.javelinsdk.util;


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
        IMU_ACCELEROMETER_CONTROL,
        IMU_GYRO_RAW_STREAM,
        IMU_MAG_RAW_STREAM,
        IMU_QUAT_STREAM,
        IMU_SAMPLE_RATE, // for gyro and accelerom
        IMU_COMPASS_SAMPLE_RATE,
        IMU_ACC_FSR,
        IMU_GYRO_FSR,
        IMU_MOTION_THRESHOLD_AWAKE,
        IMU_LOW_POWER_SAMPLE_RATE;

        public int getIndex() {
            return this.ordinal();
        }
    }

    public enum ControlMP {
        ;

        public int getIndex() {
            return this.ordinal();
        }
    }

    public enum IMUControlDef {
        IMU_WOM,
        IMU_LP_ACC,
        IMU_RAW_NORMAL;

        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
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
        CONTROL_SUCCESS,
        CONTROL_INVALID_PACKET_TYPE,
        CONTROL_INVALID_SETTING_LED,
        CONTROL_INVALID_SETTING_VIB,
        CONTROL_INVALID_SETTING_AUDIO_ENERGY,
        CONTROL_INVALID_SETTING_AUDIO_SAMPLE,
        CONTROL_INVALID_SETTING_TEMP,
        CONTROL_INVALID_SETTING_BATTERY,
        CONTROL_INVALID_SETTING_SYSTEM_TIMER,
        CONTROL_INVALID_SETTING_IMU,
        CONTROL_INVALID_SETTING_ACC_STREAM,
        CONTROL_INVALID_SETTING_GYRO_STREAM,
        CONTROL_INVALID_SETTING_MAG_STREAM,
        CONTROL_INVALID_SETTING_QUAT_STREAM,
        CONTROL_INVALID_SETTING_IMU_SAMPLE_RATE,
        CONTROL_INVALID_SETTING_MAG_SAMPLE_RATE,
        CONTROL_INVALID_SETTING_ACC_FSR,
        CONTROL_INVALID_SETTING_GYRO_FSR,
        CONTROL_INVALID_SETTING_WAKE_THRESHOLD,
        CONTROL_INVALID_SETTING_LP_ACC_SAMPLE_RATE,
        CONTROL_INVALID_SETTING_DMP_FEATURE_TAP,
        CONTROL_INVALID_SETTING_DMP_FEATURE_ORIENT;

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

    public enum QuatControl {
        OFF,
        QUAT_3X,
        QUAT_6X,
        QUAT_9X;

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
