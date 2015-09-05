package za.co.opsmobile.coindispense.framework.store;

import za.co.opsmobile.coindispense.framework.action.StoreActionEvent;
import za.co.opsmobile.coindispense.framework.dipatcher.Dispatcher;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public abstract class Store {

    private final Dispatcher dispatcher;

    public Store(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    protected void emitStoreChanged() {
        dispatcher.emitChange(getStoreChangedEvent());
    }

    protected abstract StoreChangedEvent getStoreChangedEvent();

    protected void emitStoreError(String errorMessage) {
        dispatcher.emitError(getStoreErrorEvent(errorMessage));
    }

    protected abstract StoreErrorEvent getStoreErrorEvent(String errorMessage);

    public interface StoreChangedEvent {}

    public abstract class StoreErrorEvent{
        private final String errorMessage;

        public StoreErrorEvent(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
