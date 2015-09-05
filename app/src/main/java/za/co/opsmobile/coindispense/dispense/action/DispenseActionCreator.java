package za.co.opsmobile.coindispense.dispense.action;

import za.co.opsmobile.coindispense.dispense.store.Denomination;
import za.co.opsmobile.coindispense.dispense.store.Payment;
import za.co.opsmobile.coindispense.framework.dipatcher.Dispatcher;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public class DispenseActionCreator {

    private final Dispatcher dispatcher;

    public DispenseActionCreator(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void addPayment(Denomination denomination) {
        AddPaymentAction addPaymentAction = new AddPaymentAction(denomination);
        dispatcher.dispatchAction(new DispenseStoreActionEvent(addPaymentAction));
    }
}
