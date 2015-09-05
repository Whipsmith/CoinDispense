package za.co.opsmobile.coindispense.dispense.action;

import za.co.opsmobile.coindispense.dispense.store.DispenseStoreActions;
import za.co.opsmobile.coindispense.framework.action.StoreAction;
import za.co.opsmobile.coindispense.framework.action.StoreActionEvent;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public class DispenseStoreActionEvent extends StoreActionEvent<DispenseStoreActions>{

    public DispenseStoreActionEvent(StoreAction<DispenseStoreActions> storeAction) {
        super(storeAction);
    }
}
