package za.co.opsmobile.coindispense.login.store;

import za.co.opsmobile.coindispense.framework.dipatcher.Dispatcher;
import za.co.opsmobile.coindispense.framework.logging.ModelError;
import za.co.opsmobile.coindispense.framework.store.Store;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public class LoginStore extends Store{

    private static LoginStore instance;

    public static LoginStore get(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new LoginStore(dispatcher);
        }

        return instance;
    }

    private LoginStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    protected StoreModelChangedEvent getStoreChangedEvent() {
        return new LoginStoreModelChangedEvent();
    }

    @Override
    protected ModelError getStoreErrorEvent(Throwable cause) {
        return null;
    }

    @Override
    protected ModelError getStoreErrorEvent(String errorMessage) {
        return null;
    }


}
