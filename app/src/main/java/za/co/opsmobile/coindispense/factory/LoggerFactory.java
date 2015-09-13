package za.co.opsmobile.coindispense.factory;

import za.co.opsmobile.coindispense.framework.logging.CoinDispenseLogger;
import za.co.opsmobile.coindispense.framework.logging.DefaultLogger;

/**
 * Created by Daniel Oosthuizen on 2015/09/06.
 */
public class LoggerFactory {
    public static CoinDispenseLogger getLogger() {
        return new DefaultLogger();
    }
}
