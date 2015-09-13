package za.co.opsmobile.coindispense.dispense.action;

import java.util.Arrays;

import za.co.opsmobile.coindispense.dispense.store.Denomination;
import za.co.opsmobile.coindispense.dispense.store.DispenseStoreActions;
import za.co.opsmobile.coindispense.framework.action.StoreAction;

/**
 * Created by Daniel Oosthuizen on 2015/09/06.
 */
public class ValidDenominationsFetchedAction implements StoreAction<DispenseStoreActions> {
    private final Denomination[] validDenominations;

    public ValidDenominationsFetchedAction(Denomination[] validDenominations) {
        this.validDenominations = validDenominations;
    }

    @Override
    public void executeAction(DispenseStoreActions store) {
        store.initialise(validDenominations);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValidDenominationsFetchedAction that = (ValidDenominationsFetchedAction) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(validDenominations, that.validDenominations);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(validDenominations);
    }
}
