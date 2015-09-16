package za.co.opsmobile.coindispense.dispense.store;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;

import za.co.opsmobile.coindispense.dispense.action.DispenseStoreActionEvent;
import za.co.opsmobile.coindispense.framework.dipatcher.Dispatcher;
import za.co.opsmobile.coindispense.framework.logging.ModelError;
import za.co.opsmobile.coindispense.framework.store.Store;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public class DispenseStore extends Store implements DispenseStoreActions, DispenseModel {

    private HashMap<Float, Integer> payments;
    private PaymentTransaction change;
    private Float cost;

    public DispenseStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    public void onEventBackgroundThread(DispenseStoreActionEvent event) {
        event.performAction(this);
    }

    @Override
    public void initialise(Float[] validDenominations) {
        payments = new HashMap<>(validDenominations.length);
        change = null;
        for (Float denomination : validDenominations) {
            payments.put(denomination, 0);
        }
        emitStoreChanged();
    }

    @Override
    protected StoreModelChangedEvent getStoreChangedEvent() {
        return new DispenseModelChangedEvent();
    }

    @Override
    protected ModelError getStoreErrorEvent(Throwable cause) {
        return new DispenseModelError(cause);
    }

    @Override
    protected ModelError getStoreErrorEvent(String errorMessage) {
        return new DispenseModelError(errorMessage);
    }

    @Override
    public void addPayment(Float denomination) {
        if (payments != null && payments.containsKey(denomination)) {
            payments.put(denomination, payments.get(denomination) + 1);
            change = null;
            emitStoreChanged();
        } else {
            emitStoreError("The rand note value is incorrect");
        }
    }

    @Override
    public void setChange(HashMap<Float, Integer> transactions) {

        PaymentTransaction changeTransaction;
        if (transactions == null) {
            changeTransaction = null;
        } else {
            changeTransaction = getPaymentTransaction(transactions);
        }


        if (this.change == null || !this.change.equals(changeTransaction)) {
            change = changeTransaction;
            emitStoreChanged();
        }

    }

    @NonNull
    private PaymentTransaction getPaymentTransaction(HashMap<Float, Integer> transactions) {
        ArrayList<Payment> paymentsArray = new ArrayList<>(transactions.size());
        for (Float denominationValue : transactions.keySet()) {
            Float denomination = new Float(denominationValue);
            Payment payment = new Payment(transactions.get(denominationValue), denomination);
            paymentsArray.add(payment);
        }
        return new PaymentTransaction(paymentsArray);
    }

    @Override
    public void setCost(float cost) {
        if (this.cost == null || this.cost != cost) {
            this.cost = cost;
            emitStoreChanged();
        }
    }

    @Override
    public PaymentTransaction getPayments() {
        return new PaymentTransaction(payments);
    }

    @Override
    public PaymentTransaction getChange() {
        return change;
    }

    @Override
    public ArrayList<Float> getValidDenominations() {
        if (payments == null || payments.size() == 0) {
            return null;
        }
        return new ArrayList<>(payments.keySet());
    }

    @Override
    public Float getCost() {
        return cost;
    }

}
