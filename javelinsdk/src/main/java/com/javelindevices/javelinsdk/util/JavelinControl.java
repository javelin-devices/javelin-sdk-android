package com.javelindevices.javelinsdk.util;


public class JavelinControl { //TODO: Could be renamed to Javelin constants

    private final int NULL_CMD = 0;
    private static final int DEFAULT_CMD = 1;

    public static final String CONTROL_CHARACTERISTIC = "00000009-dead-beef-0000-cafebabe0000";
    public static final String SERVICE_UUID = "00000000-dead-beef-0000-cafebabe0000";
    public static int NUM_BYTES = 20;

    public enum ControlComponent {
        LED_RATE, // controls the led rate in .1Hz increments. A value of 10 means that the led blinks at a rate of 1Hz. 0x00 default 1Hz
        LED_INTENSITY_PERCENT,
        LED_REPEAT_COUNT,
        LED_WAVEFORM,
        LED_CONTROL,
        VIBRATOR_RATE,
        VIBRATOR_INTENSITY_PERCENT,
        VIBRATOR_REPEAT_COUNT,
        VIBRATOR_WAVEFORM,
        VIB_CONTROL,
        AUDIO_ENERGY_INTEGRATION_COUNT, // number of samples * 10 to be integrated into audio energy calc.
        AUDIO_ENERGY,
        AUDIO_SAMPLE,
        TEMP_SAMPLE_RATE, // controls temperature sampling rate in .05Hz increments. A value of 20 means that the temp will sample once a second
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
        IMU_SAMPLE_RATE_DIV, // for gyro and accelerom
        IMU_COMPASS_SAMPLE_RATE_DIV, //sample rate = output data rate / sample_rate_div (output data rate selected by imu_acc_dlpf and imu_gyro_dlpf)
        IMU_ACC_FSR,
        IMU_GYRO_FSR,
        IMU_ACC_DLPF, // configures digital lowpass filter and output data rate for the accelerometer
        IMU_GYRO_DLPF,
        IMU_MOTION_THRESHOLD_AWAKE,
        IMU_LOW_POWER_SAMPLE_RATE;

        public int getIndex() {
            return this.ordinal();
        }
    }

    public enum ControlMP {
        DMP_CONTROL,
        DMP_QUAT_CONTROL,
        DMP_FEATURE_TAP,
        DMP_FEATURE_ORIENT;

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


    public enum LedWaveformControl {
        LED_SOLID,
        LED_PULSE,
        LED_TRIANGLE,
        LED_SAW_TOOTH;

        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }

    public enum VibratorWaveformControl {
        VIBRATOR_SOLID,
        VIBRATOR_PULSE,
        VIBRATOR_TRIANGLE,
        VIBRATOR_SAW_TOOTH;

        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }

    public enum LedControl {
        LED_OFF,
        LED_ON;

        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }



    public enum VibrationControl {
        VIB_OFF,
        VIB_ON;
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


    // In degrees per second
    public enum GYRO_FSR_VALUE {
        GYRO_FSR_250,
        GYRO_FSR_500,
        GYRO_FSR_1000,
        GYRO_FSR_2000;

        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }

    public enum ACC_DLPF_CONTROL {        // Bandwidth (Hz), Delay (ms), Noise Density (ug/rtHz), Output Rate (kHz)
        ACC_DLPF_CONFIG_1,                    // 1130,  	0.75,	250,	4
        ACC_DLPF_CONFIG_2,                    // 460,		1.94,	250,	1
        ACC_DLPF_CONFIG_3,                    // 184,		5.80,	250,	1
        ACC_DLPF_CONFIG_4,                    // 92,		7.80,	250,	1
        ACC_DLPF_CONFIG_5,                    // 41,		11.80,	250,	1
        ACC_DLPF_CONFIG_6,                    // 20,		19.80,	250,	1
        ACC_DLPF_CONFIG_7,                    // 10,		35.70,	250,	1
        ACC_DLPF_CONFIG_8,                    // 5,		    66.96,	250,	1
        ACC_DLPF_CONFIG_9;                    // 460,		1.94,	250,	1
        public int getIndex() {
            return this.ordinal() + DEFAULT_CMD;
        }
    }

    // these correspond to line numbers in the
// the table included in section 4.5 of the
// Register Map
// Config 4-9 are supported
    public enum GYRO_DLPF_CONTROL {        // Gyro Bandwidth (Hz), Gyro Delay (ms), Gyro Output Rate (kHz), Temp Bandwidth (Hz), Temp Delay (ms)
        GYRO_DLPF_CONFIG_1,                    // 8800,	    0.064,  32,     4000,	0.04
        GYRO_DLPF_CONFIG_2,                    // 3600,	    0.11,	32, 	4000,	0.04
        GYRO_DLPF_CONFIG_3,                    // 250,		0.97,	8, 		4000, 	0.04
        GYRO_DLPF_CONFIG_4,                    // 184,		2.9,	1, 		188, 	1.9
        GYRO_DLPF_CONFIG_5,                    // 92,		3.9,	1, 		98, 	2.8
        GYRO_DLPF_CONFIG_6,                    // 41,		5.9,	1, 		42, 	4.8
        GYRO_DLPF_CONFIG_7,                    // 20,		9.9,	1, 		20, 	8.3
        GYRO_DLPF_CONFIG_8,                    // 10,		17.85,	1, 		10,		13.4
        GYRO_DLPF_CONFIG_9,                    // 5,		33.48,	1, 		5, 		18.6
        GYRO_DLPF_CONFIG_10;                   // 3600,	    .017,	8, 		4000,	0.04

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
}
