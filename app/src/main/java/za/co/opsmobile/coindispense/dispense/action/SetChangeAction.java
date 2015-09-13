package za.co.opsmobile.coindispense.dispense.action;

import java.util.ArrayList;

import za.co.opsmobile.coindispense.dispense.store.DispenseStoreActions;
import za.co.opsmobile.coindispense.dispense.store.Payment;
import za.co.opsmobile.coindispense.dispense.store.PaymentTransaction;
import za.co.opsmobile.coindispense.framework.action.StoreAction;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public class SetChangeAction implements StoreAction<DispenseStoreActions> {
    private final PaymentTransaction changeTransaction;

    public SetChangeAction(ArrayList<Payment> payments) {
        this.changeTransaction = new PaymentTransaction(payments);
    }

    @Override
    public void executeAction(DispenseStoreActions store) {
        store.setChange(changeTransaction);
    }
}
