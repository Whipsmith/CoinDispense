package za.co.opsmobile.coindispense.dispense.store;

import java.util.HashMap;

import za.co.opsmobile.coindispense.dispense.action.DispenseStoreActionEvent;
import za.co.opsmobile.coindispense.framework.dipatcher.Dispatcher;
import za.co.opsmobile.coindispense.framework.store.Store;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public class DispenseStore extends Store implements DispenseStoreActions, DispenseModel {

    private HashMap<Denomination, Integer> payments;
    private PaymentTransaction change;

    public DispenseStore(Dispatcher dispatcher, Denomination[] validDenominations) {
        super(dispatcher);
        initPayments(validDenominations);
    }

    private void initPayments(Denomination[] validDenominations) {
        payments = new HashMap<>(validDenominations.length);
        for (Denomination denomination : validDenominations) {
            payments.put(denomination, 0);
        }
    }

    public void onEventBackgroundThread(DispenseStoreActionEvent event) {
        event.performAction(this);
        emitStoreChanged();
    }

    @Override
    protected StoreChangedEvent getStoreChangedEvent() {
        return new DispenseModelChangedEvent();
    }

    @Override
    protected StoreErrorEvent getStoreErrorEvent(String errorMessage) {
        return new DispenseStoreErrorEvent(errorMessage);
    }

    @Override
    public void addPayment(Denomination denomination) {
        if (payments.containsKey(denomination)) {
            payments.put(denomination, payments.get(denomination) + 1);
            change = null;
        } else {
            emitStoreError("The rand note value is incorrect");
        }
    }

    @Override
    public void setChange(PaymentTransaction changeTransaction) {
        change = changeTransaction;
    }

    @Override
    public PaymentTransaction getPayments() {
        return new PaymentTransaction.Builder(payments).build();
    }

    @Override
    public PaymentTransaction getChange() {
        return null;
    }

    public class DispenseStoreErrorEvent extends StoreErrorEvent {
        public DispenseStoreErrorEvent(String errorMessage) {
            super(errorMessage);
        }
    }

}
