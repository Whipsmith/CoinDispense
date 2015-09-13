package za.co.opsmobile.coindispense.dispense.action;

import za.co.opsmobile.coindispense.dispense.store.Denomination;
import za.co.opsmobile.coindispense.dispense.store.DispenseStoreActions;
import za.co.opsmobile.coindispense.framework.action.StoreAction;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public class AddPaymentAction implements StoreAction<DispenseStoreActions> {

    private final Denomination denomination;

    public AddPaymentAction(Denomination denomination) {
        this.denomination = denomination;
    }

    @Override
    public void executeAction(DispenseStoreActions store) {
        store.addPayment(denomination);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddPaymentAction that = (AddPaymentAction) o;

        return denomination.equals(that.denomination);

    }

    @Override
    public int hashCode() {
        return denomination.hashCode();
    }
}
