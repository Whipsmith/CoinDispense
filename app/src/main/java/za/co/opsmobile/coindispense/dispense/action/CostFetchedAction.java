package za.co.opsmobile.coindispense.dispense.action;

import za.co.opsmobile.coindispense.dispense.store.DispenseStoreActions;
import za.co.opsmobile.coindispense.framework.action.StoreAction;

/**
 * Created by Daniel Oosthuizen on 2015/09/07.
 */
public class CostFetchedAction implements StoreAction<DispenseStoreActions> {
    private final float cost;

    public CostFetchedAction(float cost) {
        this.cost = cost;
    }

    @Override
    public void executeAction(DispenseStoreActions store) {
        store.setCost(cost);
    }
}
