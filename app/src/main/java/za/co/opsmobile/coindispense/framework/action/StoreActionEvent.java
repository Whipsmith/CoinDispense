package za.co.opsmobile.coindispense.framework.action;

import za.co.opsmobile.coindispense.framework.store.Store;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public abstract class StoreActionEvent<K> {
    private final StoreAction<K> storeAction;

    public StoreActionEvent(StoreAction<K> storeAction) {
        this.storeAction = storeAction;
    }

    public void performAction(K store) {
        storeAction.executeAction(store);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoreActionEvent<?> that = (StoreActionEvent<?>) o;

        return storeAction.equals(that.storeAction);

    }

    @Override
    public int hashCode() {
        return storeAction.hashCode();
    }
}
