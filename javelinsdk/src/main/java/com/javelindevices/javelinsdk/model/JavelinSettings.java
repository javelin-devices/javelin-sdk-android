package com.javelindevices.javelinsdk.model;

import com.javelindevices.javelinsdk.util.JavelinControl;


public class JavelinSettings {
    public static final int VIB_TYPE_PULSE = JavelinControl.VibratorWaveformControl.VIBRATOR_PULSE.getIndex();
    public static final int VIB_TYPE_SAWTOOTH = JavelinControl.VibratorWaveformControl.VIBRATOR_SAW_TOOTH.getIndex();
    public static final int VIB_TYPE_SOLID = JavelinControl.VibratorWaveformControl.VIBRATOR_SOLID.getIndex();
    public static final int VIB_TYPE_TRIANGLE = JavelinControl.VibratorWaveformControl.VIBRATOR_TRIANGLE.getIndex();

    public static final int LED_TYPE_PULSE = JavelinControl.LedWaveformControl.LED_PULSE.getIndex();
    public static final int LED_TYPE_SAWTOOTH = JavelinControl.LedWaveformControl.LED_SAW_TOOTH.getIndex();
    public static final int LED_TYPE_SOLID = JavelinControl.LedWaveformControl.LED_SOLID.getIndex();
    public static final int LED_TYPE_TRIANGLE = JavelinControl.LedWaveformControl.LED_TRIANGLE.getIndex();

    public static final int AUDIO_QUALITY_MEDIUM = JavelinControl.AudioStreamControl.AUDIO_STREAM_MED_QUALITY.getIndex();
    public static final int AUDIO_QUALITY_HIGH = JavelinControl.AudioStreamControl.AUDIO_STREAM_HIGH_QUALITY.getIndex();
}
