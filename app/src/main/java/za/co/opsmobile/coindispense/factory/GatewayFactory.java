package za.co.opsmobile.coindispense.factory;

import za.co.opsmobile.coindispense.dispense.gateway.ConfigurationGateway;
import za.co.opsmobile.coindispense.dispense.gateway.DispenseGateway;
import za.co.opsmobile.coindispense.dispense.gateway.LocalConfigurationGateway;
import za.co.opsmobile.coindispense.dispense.gateway.RandomTransactionGateway;
import za.co.opsmobile.coindispense.dispense.gateway.RetrofitDispenseGateway;
import za.co.opsmobile.coindispense.dispense.gateway.TransactionGateway;

/**
 * Created by Daniel Oosthuizen on 2015/09/06.
 */
public class GatewayFactory {
    public static DispenseGateway getDispenseGateway() {
        return new RetrofitDispenseGateway();
    }

    public static ConfigurationGateway getConfigurationGateway() {
        return new LocalConfigurationGateway();
    }

    public static TransactionGateway getTransactionGateway() {
        return new RandomTransactionGateway();
    }
}
