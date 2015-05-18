package util;

import android.bluetooth.BluetoothGatt;

/**
 * Created by Aaron on 4/11/2015.
 */
public abstract class ServiceAction {
    public enum ActionType {
        NONE,
        READ,
        NOTIFY,
        WRITE
    }

    public static final ServiceAction NULL = new ServiceAction(ActionType.NONE) {
        @Override
        public boolean execute(BluetoothGatt bluetoothGatt) {
            // it is null action. do nothing.
            return true;
        }
    };

    private final ActionType type;

    public ServiceAction(ActionType type) {
        this.type = type;
    }

    public ActionType getType() {
        return type;
    }

    /**
     * Executes action.
     *
     * @param bluetoothGatt
     * @return true - if action was executed instantly. false if action is waiting for
     * feedback.
     */
    public abstract boolean execute(BluetoothGatt bluetoothGatt);
}