package za.co.opsmobile.coindispense.dispense.action;

import za.co.opsmobile.coindispense.dispense.store.DispenseStoreActions;
import za.co.opsmobile.coindispense.dispense.store.PaymentTransaction;
import za.co.opsmobile.coindispense.framework.action.StoreAction;

/**
 * Created by Daniel Oosthuizen on 2015/09/06.
 */
public class ChangeCalculatedAction implements StoreAction<DispenseStoreActions> {
    private final PaymentTransaction change;

    public ChangeCalculatedAction(PaymentTransaction change) {
        this.change = change;
    }

    @Override
    public void executeAction(DispenseStoreActions store) {
        store.setChange(change);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChangeCalculatedAction that = (ChangeCalculatedAction) o;

        return change.equals(that.change);

    }

    @Override
    public int hashCode() {
        return change.hashCode();
    }
}
