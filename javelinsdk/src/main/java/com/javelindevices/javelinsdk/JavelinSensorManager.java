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
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.javelindevices.javelinsdk.model.BleMessage;
import com.javelindevices.javelinsdk.model.BleServiceListener;
import com.javelindevices.javelinsdk.model.ISensor;
import com.javelindevices.javelinsdk.model.ISensorManager;
import com.javelindevices.javelinsdk.model.JavelinSensorEvent;
import com.javelindevices.javelinsdk.util.JavelinControl;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class JavelinSensorManager extends ISensorManager implements BleServiceListener {

    private Messenger mService = null;
    private Context context;
    private JavelinEventListener listener;
    private static final String JAVELIN_SERVICE = "com.javelindevices.j1_sdk.JavelinService";
    private boolean connected = false;

    // For streamable (notification-based) sensors only.
    private HashMap<Integer, ArrayList<JavelinEventListener>> sensorListenersMap = new HashMap<Integer, ArrayList<JavelinEventListener>>();

    private static JavelinSensorManager mSensorManager;

    // Singleton for those that would like to use one instead. There might be some memory savings.
    public static JavelinSensorManager getJavelinSensorManager(Context context, String deviceAddress) {
        if(mSensorManager == null) {
            mSensorManager = new JavelinSensorManager(context, deviceAddress);
        }
        return mSensorManager;
    }
    /**
     * Create a JavelinSensorManager object
     *
     * @param context       an android Activity
     * @param deviceAddress the address of the javelin device to connect to.
     */
    public JavelinSensorManager(Context context, String deviceAddress) {
        this.context = context;
        mMessenger = new Messenger(new IncomingHandler(this));
        this.deviceAddress = deviceAddress;

        sensorListenersMap.put(ISensor.TYPE_ACCELEROMETER, new ArrayList<JavelinEventListener>());
        sensorListenersMap.put(ISensor.TYPE_MAGNETIC_FIELD, new ArrayList<JavelinEventListener>());
        sensorListenersMap.put(ISensor.TYPE_GYROSCOPE, new ArrayList<JavelinEventListener>());
        sensorListenersMap.put(ISensor.TYPE_TEMPERATURE, new ArrayList<JavelinEventListener>());
        sensorListenersMap.put(ISensor.TYPE_BATTERY, new ArrayList<JavelinEventListener>());
        sensorListenersMap.put(ISensor.TYPE_AUDIO_STREAM, new ArrayList<JavelinEventListener>());
        sensorListenersMap.put(ISensor.TYPE_AUDIO_ENERGY, new ArrayList<JavelinEventListener>());
        sensorListenersMap.put(ISensor.TYPE_QUATERNION, new ArrayList<JavelinEventListener>());
    }

    // Holds all control messages prior to connecting to the javelin service and javelin peripheral.
    LinkedList<Message> messageQueue = new LinkedList<Message>();

    //TODO: deprecate. We could try to instead use the context passed in the constructor and
    // try to cast that to a JavelinEventListener.
    @Override
    public void setListener(JavelinEventListener listener) {
        this.listener = listener;
    }
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "Bound to javelin android service. Attempting to connect to " + deviceAddress);
            mService = new Messenger(service);
            Bundle bundle = new Bundle();
            bundle.putString("deviceAddress", deviceAddress);
            sendMessage(BleMessage.MSG_CONNECT, 0, 0, bundle);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            connected = false;
            Log.d(TAG, "Javelin service disconnected!");
            mService = null;
        }
    };

    private void sendMessage(int message) {
        sendMessage(message, -1, -1, null);
    }

    private void sendMessage(int message, int arg1, int arg2, Object obj) {

        Message msg = Message.obtain(null, message);
        if (msg != null) {
            msg.replyTo = mMessenger;
            msg.arg1 = arg1;
            msg.arg2 = arg2;
            msg.obj = obj;

            // We can send connect messages but only if we already bound to the service
            // For all other messages, we put them in the queue if service is null or we aren't connected.
            if (msg.what != BleMessage.MSG_CONNECT && (mService == null || !connected)) {
                // Log.d(TAG, "Adding message" + msg.what + " to the queue since we're not yet connected");
                messageQueue.add(msg);
            } else {
                sendMessage(msg);
            }
        } else {
            mService = null;
        }
    }

    private void sendMessage(Message msg) {
        try {
            mService.send(msg);
        } catch (RemoteException e) {
            Log.w(TAG, "Error communicating with the Javelin service", e);
            mService = null;
        }
    }

    /**
     * Android L (lollipop, API 21) introduced a new problem when trying to invoke implicit intent,
     * "java.lang.IllegalArgumentException: Service Intent must be explicit"
     * <p/>
     * If you are using an implicit intent, and know only 1 target would answer this intent,
     * This method will help you turn the implicit intent into the explicit form.
     * <p/>
     * Inspired from SO answer: http://stackoverflow.com/a/26318757/1446466
     *
     * @param context
     * @param implicitIntent - The original implicit intent
     * @return Explicit Intent created from the implicit original intent
     */
    private static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
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
                        Bundle b = (Bundle) msg.obj;
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


    /**
     * ************* Sensor related functions *******************
     */

    private static final String TAG = JavelinSensorManager.class.getSimpleName();

    // Component Settings
    private final String deviceAddress;
    private final SparseArray<Boolean> sensors = new SparseArray<Boolean>();
    private final Handler uiThreadHandler = new Handler(Looper.getMainLooper());

    @Override
    public void enable() {
        // Pair up a messenger with its listener.
        Intent mServiceIntent = new Intent(JAVELIN_SERVICE);
        mServiceIntent = createExplicitFromImplicitIntent(context, mServiceIntent);

        ComponentName componentName = context.startService(mServiceIntent);
        if(componentName == null) {
            Log.d(TAG, "Service started for the first time");
        }
        context.bindService(mServiceIntent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void disable() {
        sendMessage(BleMessage.MSG_DISCONNECT);

        messageQueue.clear();
        try {
            context.unbindService(mConnection);
        } catch (Exception e) {
        //    Log.w(TAG, "Something went wrong while unbinding the service.");
        //    e.printStackTrace();
        }
        mService = null;
    }

    @Override
    public void registerListener(JavelinEventListener listener, int sensorType) {
        sensorListenersMap.get(sensorType).add(listener);
        enableJavelinSensor(sensorType, true);
    }


    @Override
    public void unregisterListener(JavelinEventListener listener, int sensorType) {
        if (sensorListenersMap.get(sensorType).size() > 0) {
            sensorListenersMap.get(sensorType).remove(listener);
        } else {
            Log.e(TAG, "Disabled a sensor for a listener that wasn't registered to it already.");
        }
        enableJavelinSensor(sensorType, false);
    }

    @Override
    public void unregisterAll() {
        // This will remove all listeners from the application and disable all sensors for it.
        for (ArrayList<JavelinEventListener> listeners : sensorListenersMap.values()) {
            listeners.clear();
        }
        sendMessage(BleMessage.MSG_SENSOR_UNREGISTER_ALL);
    }

    public void onSensorChanged(int sensor, float[] data) {
        if (data != null) {
            ArrayList<JavelinEventListener> listeners = sensorListenersMap.get(sensor);
            for (JavelinEventListener l : listeners) {
                l.onSensorChanged(new JavelinSensorEvent(sensor, data));
            }
        }
    }

    // Once we are connected, we'll let the listener know our bond status.
    @Override
    public void onConnected() {
        connected = true;
        Log.d(TAG, "Connected to a Javelin device");

        // Start sending the messages we had queued up:
        Log.d(TAG, "Client message queue size: " + messageQueue.size());
        for (Message msg : messageQueue) {
            sendMessage(msg);
        }
        messageQueue.clear();

        listener.onSensorManagerConnected();
        updateBondStateUI();
    }

    @Override
    public void onDisconnected() {
        connected = false;
        listener.onSensorManagerDisconnected();
        updateBondStateUI();
    }

    public void readRssi() {
        sendMessage(BleMessage.MSG_READ_RSSI);
    }

    @Override
    public void onReadRemoteRssi(int rssi) {
        setAcceleromGyroRate(70); // TODO: Might want to move this default to the service.
        listener.onReadRemoteRssi(rssi);
    }

    private void updateBondStateUI() {
        uiThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                if (listener != null) {
                    // listener.onBondStatusUpdated(...)
                }
            }
        });
    }

    @Override
    public void onServiceDiscovered() {
    }

    @Override
    public void onDataAvailable(String serviceUuid, String characteristicUUid, String text, byte[] data) {
    }

    private void enableJavelinSensor(int sensorType, boolean enable) {
        int what = -1;
        if (enable) {
            what = BleMessage.MSG_REGISTER_SENSOR;
        } else {
            what = BleMessage.MSG_UNREGISTER_SENSOR;
        }
        sendMessage(what, sensorType, 0, null);
    }

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
            command = JavelinControl.LedControl.LED_ON.getIndex();
        } else {
            command = JavelinControl.LedControl.LED_OFF.getIndex();
        }
        sendMessage(BleMessage.MSG_SET_CONTROL, BleMessage.TYPE_LED, command, null);
    }

    @Override
    public void setLedIntensity(int intensity) {
        sendMessage(BleMessage.MSG_SET_INTENSITY, BleMessage.TYPE_LED, intensity, null);
    }

    @Override
    public void setLedBlinkType(int blinkType) {
        sendMessage(BleMessage.MSG_SET_TYPE, BleMessage.TYPE_LED, blinkType, null);
    }

    @Override
    public void setLedBlinkRate(int rate) {
        sendMessage(BleMessage.MSG_SET_RATE, BleMessage.TYPE_LED, rate, null);
    }

    // Note: A value of 255 will make it blink infinitely
    // Default value is 255.
    // This is also a setting, and will only actually blink once vibrationEnable() is called.
    @Override
    public void setLedBlinkNumber(int times) {
        sendMessage(BleMessage.MSG_SET_PULSE, BleMessage.TYPE_LED, times, null);
    }

    public void vibrationEnable(boolean enable) {
        int command = -1;
        if (enable) {
            command = JavelinControl.VibrationControl.VIB_ON.getIndex();
        } else {
            command = JavelinControl.VibrationControl.VIB_OFF.getIndex();
        }
        sendMessage(BleMessage.MSG_SET_CONTROL, BleMessage.TYPE_VIBRATOR, command, null);
    }

    @Override
    public void setVibrationIntensity(int intensity) {
        sendMessage(BleMessage.MSG_SET_INTENSITY, BleMessage.TYPE_VIBRATOR, intensity, null);
    }

    @Override
    public void setVibrationType(int type) {
        sendMessage(BleMessage.MSG_SET_TYPE, BleMessage.TYPE_VIBRATOR, type, null);
    }

    @Override
    public void setVibrationRate(int rate) {
        sendMessage(BleMessage.MSG_SET_RATE, BleMessage.TYPE_VIBRATOR, rate, null);
    }

    @Override
    public void setVibrationRepeatNumber(int times) {
        sendMessage(BleMessage.MSG_SET_PULSE, BleMessage.TYPE_VIBRATOR, times, null);
    }

    public void setAudioQuality(int quality) {
        //    AudioStream.setAudioQuality(quality);
    }

    /**
     * It's not going to be exact, but it will attempt to approximate given sample rate
     * @param rate the sampling rate to use.
     */
    @Override
    public void setAcceleromGyroRate(int rate) {
        // The rate is set by
        double defaultRate = 100; // the default gyro and accelerom sampling rate.
        int divisor = (int)Math.round(defaultRate / rate);
        Log.d(TAG, "Setting IMU sample rate to " + defaultRate / divisor);
        sendMessage(BleMessage.MSG_SET_SAMPLING_RATE, ISensor.TYPE_ACCELEROMETER, divisor, null);
    }

    @Override
    public void setAcceleromGyroSampleRateDivisor(int divisor) {
        sendMessage(BleMessage.MSG_SET_SAMPLING_RATE, ISensor.TYPE_ACCELEROMETER, divisor, null);
    }

    @Override
    public void setAccelerometerFullScaleRange(int range) {
        sendMessage(BleMessage.MSG_SET_FULL_SCALE_RANGE, ISensor.TYPE_ACCELEROMETER, range, null);
    }

    @Override
    public void setGyroscopeFullScaleRange(int range) {
        sendMessage(BleMessage.MSG_SET_FULL_SCALE_RANGE, ISensor.TYPE_GYROSCOPE, range, null);
    }
}