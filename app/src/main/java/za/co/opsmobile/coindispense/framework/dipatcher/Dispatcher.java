package za.co.opsmobile.coindispense.framework.dipatcher;

import de.greenrobot.event.EventBus;
import za.co.opsmobile.coindispense.framework.action.StoreActionEvent;
import za.co.opsmobile.coindispense.framework.store.Store;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public class Dispatcher {
    private final EventBus bus;

    public static Dispatcher getDefault() {
        return new Dispatcher(EventBus.getDefault());
    }

    public Dispatcher(EventBus bus) {
        this.bus = bus;
    }

    public void emitChange(Store.StoreChangedEvent storeChangedEvent) {
        bus.post(storeChangedEvent);
    }

    public void emitError(Store.StoreErrorEvent storeErrorEvent) {
        bus.post(storeErrorEvent);
    }

    public void dispatchAction(StoreActionEvent storeActionEvent) {
        bus.post(storeActionEvent);
    }

    public void register(Object subscriber) {
        bus.register(subscriber);
    }

    public void unRegister(Object subscriber) {
        bus.unregister(subscriber);
    }


}
