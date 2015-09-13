package za.co.opsmobile.coindispense;

import android.app.Application;

import za.co.opsmobile.coindispense.factory.LoggerFactory;
import za.co.opsmobile.coindispense.framework.dipatcher.Dispatcher;
import za.co.opsmobile.coindispense.framework.logging.CoinDispenseLogger;
import za.co.opsmobile.coindispense.login.store.LoginStore;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public class CoinDispenseApplication extends Application {

    private LoginStore loginStore;
    private Dispatcher dispatcher;
    private CoinDispenseLogger logger;

    @Override
    public void onCreate() {
        super.onCreate();
        dispatcher = Dispatcher.getDefault();
        logger = LoggerFactory.getLogger();
        dispatcher.register(logger);
        loginStore = LoginStore.get(dispatcher);
    }

    @Override
    public void onTerminate() {
        loginStore = null;
        dispatcher.unRegister(logger);
        super.onTerminate();
    }
}
