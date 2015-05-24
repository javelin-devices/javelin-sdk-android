package com.javelindevices.javelinsdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.util.SparseArray;



import java.lang.ref.WeakReference;
import java.util.List;

import com.javelindevices.javelinsdk.model.BleMessage;
import com.javelindevices.javelinsdk.model.BleServiceListener;
import com.javelindevices.javelinsdk.model.ISensor;
import com.javelindevices.javelinsdk.model.ISensorManager;
import com.javelindevices.javelinsdk.util.JavelinControl;

/**
 * Created by Aaron on 4/3/2015.
 */
public class JavelinSensorManager extends ISensorManager implements BleServiceListener { //extends Activity {

    private Messenger mService = null;
    private Context context;
    private ISensorManager.SensorEventListener listener;
    private static final String JAVELIN_SERVICE = "com.javelindevices.j1_sdk.JavelinService";

    public JavelinSensorManager(Context context, String deviceAddress) {
        this.context = context;
        mMessenger = new Messenger(new IncomingHandler(this));
        this.deviceAddress = deviceAddress;
    }


    /************* Client Functions  ****************************/
    @Override
    public void setListener(ISensorManager.SensorEventListener listener) {
        this.listener = listener;
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "Bound to javelin android service.");
            mService = new Messenger(service);
            Log.d(TAG, "Sending connect message");
            Bundle bundle = new Bundle();
            bundle.putString("deviceAddress", deviceAddress);
            sendMessage(BleMessage.MSG_CONNECT, 0, 0, bundle);

            //mBoundService = ((LocalService.LocalBinder)service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }
    };

    public void sendMessage(int message) {
       sendMessage(message, -1, -1, null);
    }

    public void sendMessage(int message, int arg1, int arg2, Object obj) {
        try {
            Message msg = Message.obtain(null, message);
            if (msg != null) {
                msg.replyTo = mMessenger;
                msg.arg1 = arg1;
                msg.arg2 = arg2;
                msg.obj = obj;
                mService.send(msg);
            } else {
                mService = null;
            }
        } catch (Exception e) {
            Log.w(TAG, "Error communicating with the Javelin service", e);
            mService = null;
        }
    }


    /***
     * Android L (lollipop, API 21) introduced a new problem when trying to invoke implicit intent,
     * "java.lang.IllegalArgumentException: Service Intent must be explicit"
     *
     * If you are using an implicit intent, and know only 1 target would answer this intent,
     * This method will help you turn the implicit intent into the explicit form.
     *
     * Inspired from SO answer: http://stackoverflow.com/a/26318757/1446466
     * @param context
     * @param implicitIntent - The original implicit intent
     * @return Explicit Intent created from the implicit original intent
     */
    public static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);
        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            Log.d(TAG, "Could not create explicit intent");
            return null;
        }

        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);
        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);
        // Set the component to be explicit
        explicitIntent.setComponent(component);
        return explicitIntent;
    }



    Messenger mMessenger;

    private static class IncomingHandler extends Handler {
        private final WeakReference<JavelinSensorManager> mClient;

        public IncomingHandler(JavelinSensorManager activity) {
            mClient = new WeakReference<JavelinSensorManager>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            JavelinSensorManager client = mClient.get();
            if (client != null) {
                switch (msg.what) {
                    case BleMessage.MSG_CONNECT:
                        client.onConnected();
                        break;
                    case BleMessage.MSG_DISCONNECT:
                        client.onDisconnected();
                        break;
                    case BleMessage.MSG_READ_RSSI:
                        Log.d(TAG, "RSSI is " + msg.arg1);
                        client.onReadRemoteRssi(msg.arg1);
                        break;
                    case BleMessage.MSG_GET_BOND_STATE:
                        Log.d(TAG, "Bond state is " + msg.arg1);
                        break;
                    case BleMessage.MSG_REGISTER_SENSOR:
                        Log.d(TAG, "Registered sensor: " + msg.arg1);

                        break;
                    case BleMessage.MSG_UNREGISTER_SENSOR:
                        Log.d(TAG, "Unregistered sensor: " + msg.arg1);
                        break;
                    case BleMessage.MSG_SENSOR_UPDATE:
                        Bundle b = (Bundle)msg.obj;
                        client.onSensorChanged(msg.arg1, b.getFloatArray("data"));
                        break;
                    case BleMessage.MSG_SENSOR_RATE_CHANGED:
                        Log.d(TAG, "sensor " + msg.arg1 + " changed to rate: " + msg.arg2);
                        break;
                }
            }
            super.handleMessage(msg);
        }
    }








    /**************** Sensor related functions ********************/


    public void onSensorChanged(int sensor, float[] data) {
        if(data != null) {
            listener.onSensorChanged(sensor, data);
        }
    }

    private static final String TAG = JavelinSensorManager.class.getSimpleName();
    // Component Settings
    public static final int LED_TOGGLE_SINGLE = JavelinControl.LedControl.LED_PULSE.getIndex();
    public static final int LED_TOGGLE_REPEAT_START = JavelinControl.LedControl.LED_SAW_TOOTH.getIndex();
    public static final int VIB_TOGGLE_SINGLE = JavelinControl.VibrationControl.VIB_PULSE.getIndex();
    public static final int VIB_TOGGLE_REPEAT_START = JavelinControl.VibrationControl.VIB_SAW_TOOTH.getIndex();
    public static final int AUDIO_QUALITY_MEDIUM = JavelinControl.AudioStreamControl.AUDIO_STREAM_MED_QUALITY.getIndex();
    public static final int AUDIO_QUALITY_HIGH = JavelinControl.AudioStreamControl.AUDIO_STREAM_HIGH_QUALITY.getIndex();

    private final String deviceAddress;
    private final SparseArray<Boolean> sensors = new SparseArray<Boolean>();
    private final Handler uiThreadHandler = new Handler(Looper.getMainLooper());

    @Override
    public void enable() {
        // Pair up a messenger with its listener.
        Intent mServiceIntent = new Intent(JAVELIN_SERVICE);
        mServiceIntent = createExplicitFromImplicitIntent(context, mServiceIntent);
        context.bindService(mServiceIntent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void disable() {
        sendMessage(BleMessage.MSG_DISCONNECT);
        context.unbindService(mConnection);
    }

    @Override
    public void registerSensor(int sensorType) {
        sensors.put(sensorType, true);
        enableJavelinSensor(sensorType, true);
    }


    @Override
    public void unregisterSensor(int sensorType) {
        enableJavelinSensor(sensorType, false);
        sensors.remove(sensorType);
    }

    @Override
    public void unregisterAll() {
        for(int i = 0; i < sensors.size(); i++) {
            enableJavelinSensor(sensors.keyAt(i), false);
        }

        sendMessage(BleMessage.MSG_SENSOR_UNREGISTER_ALL);
    }

    // Once we are connected, we'll let the listener know our bond status.
    @Override
    public void onConnected() {
        Log.d(TAG, "connected");
        listener.onSensorManagerConnected();
        updateBondStateUI();
    }

    @Override
    public void onDisconnected() {
        Log.d(TAG, "disconnected");
        listener.onSensorManagerDisconnected();
        updateBondStateUI();
    }

    public void readRssi() {
        sendMessage(BleMessage.MSG_READ_RSSI);
    }

    @Override
    public void onReadRemoteRssi(int rssi) {
        listener.onReadRemoteRssi(rssi);
    }

    private void updateBondStateUI() {
        uiThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                if (listener != null) {
                  //  if (bleManager.getBondState() == BluetoothDevice.BOND_BONDED) {
                        //     listener.onBondCreated();
                  //  } else { //TODO: add onBondConnecting?
                        //    listener.onBondRemoved();
                 //   }
                }
            }
        });
    }

    @Override
    public void onServiceDiscovered() {
        Log.d(TAG, "Services Discovered");
        // enable all sensors that were registered previously
        // TODO: remove, as service takes care of registering app's sensors at the moment...
        // TODO: service will forget these susbcriptions if the app calls......
    }

    @Override
    public void onDataAvailable(String serviceUuid, String characteristicUUid, String text, byte[] data) {
    }

    private void enableJavelinSensor(int sensorType, boolean enable) {
        int what = -1;
        if(enable) {
            what = BleMessage.MSG_REGISTER_SENSOR;
        } else {
            what = BleMessage.MSG_UNREGISTER_SENSOR;
        }
        sendMessage(what, sensorType,0,null);
    }

    // TODO: Change return type
    public boolean createBond() {
        sendMessage(BleMessage.MSG_BOND_CREATE);
        return true;
    }

    public boolean removeBond() {
        sendMessage(BleMessage.MSG_BOND_DESTROY);
        return true;
    }

    
    public void ledEnable(boolean enable) {
        int command = -1;
        if (enable) {
            command = JavelinControl.LedControl.LED_SOLID.getIndex();
        } else {
            command = JavelinControl.LedControl.LED_OFF.getIndex();
        }
        Log.d(TAG, "enabling/disabling LED");
        sendMessage(BleMessage.MSG_SET_CONTROL, ISensor.TYPE_LED, command, null);
    }

    /**
     * @param setting LED_PULSE or LED_SAW_TOOTH
     */
    public void ledStartBlinking(int setting) {
        sendMessage(BleMessage.MSG_SET_CONTROL, ISensor.TYPE_LED, setting, null);
    }

    public void ledStopRepeat() {
        sendMessage(BleMessage.MSG_SET_CONTROL, ISensor.TYPE_LED,
                JavelinControl.LedControl.LED_TRIANGLE.getIndex(), null );
    }

    // TODO: remove the "enabling" of vibration, etc. Simply turn it on when you want to vibrate it and
    // Turn it off otherwise.
    public void vibrationEnable(boolean enable) {
        int command = -1;
        if (enable) {
            command = JavelinControl.VibrationControl.VIB_SOLID.getIndex();
        } else {
            command = JavelinControl.VibrationControl.VIB_OFF.getIndex();
        }
        sendMessage(BleMessage.MSG_SET_CONTROL, ISensor.TYPE_VIBRATOR, command, null);
    }

    /**
     * @param setting VIB_PULSE or VIB_SAW_TOOTH
     */
    public void vibrationStartRepeat(int setting) { // TODO: startRepeat(setting, intensity)
        sendMessage(BleMessage.MSG_SET_CONTROL, ISensor.TYPE_VIBRATOR, setting, null);
        sendMessage(BleMessage.MSG_SET_INTENSITY, ISensor.TYPE_VIBRATOR, 40, null);
    }


    public void vibrationStopRepeat() {
        sendMessage(BleMessage.MSG_SET_CONTROL, ISensor.TYPE_VIBRATOR,
                JavelinControl.VibrationControl.VIB_OFF.getIndex(), null);
    }

    public void setAudioQuality(int quality) {
    //    AudioStream.setAudioQuality(quality);
    }

    @Override
    public short[] getAudio() {
        return new short[0];
    }

    // TODO: remove from sdk
    @Override
    public void startDFU() {
    }

}