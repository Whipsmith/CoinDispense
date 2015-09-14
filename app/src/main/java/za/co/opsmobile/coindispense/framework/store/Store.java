package za.co.opsmobile.coindispense.framework.store;

import za.co.opsmobile.coindispense.framework.dipatcher.Dispatcher;
import za.co.opsmobile.coindispense.framework.logging.ModelError;

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

    protected abstract StoreModelChangedEvent getStoreChangedEvent();

    protected void emitStoreError(String errorMessage) {
        dispatcher.emitError(getStoreErrorEvent(errorMessage));
    }

    protected void emitStoreError(Throwable cause) {
        dispatcher.emitError(getStoreErrorEvent(cause));
    }

    protected abstract ModelError getStoreErrorEvent(Throwable cause);

    protected abstract ModelError getStoreErrorEvent(String errorMessage);

    public interface StoreModelChangedEvent {
    }

}
