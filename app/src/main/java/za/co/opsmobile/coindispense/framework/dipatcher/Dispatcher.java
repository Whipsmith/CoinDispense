package za.co.opsmobile.coindispense.framework.dipatcher;

import de.greenrobot.event.EventBus;
import za.co.opsmobile.coindispense.framework.action.StoreActionEvent;
import za.co.opsmobile.coindispense.framework.logging.CoinDispenseError;
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

    public void emitChange(Store.StoreModelChangedEvent storeModelChangedEvent) {
        bus.post(storeModelChangedEvent);
    }

    public void emitError(CoinDispenseError error) {
        bus.post(error);
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
