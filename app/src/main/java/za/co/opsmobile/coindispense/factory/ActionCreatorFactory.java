package za.co.opsmobile.coindispense.factory;

import android.content.Context;

import za.co.opsmobile.coindispense.dispense.action.DispenseActionCreator;
import za.co.opsmobile.coindispense.dispense.gateway.ConfigurationGateway;
import za.co.opsmobile.coindispense.dispense.gateway.DispenseGateway;
import za.co.opsmobile.coindispense.dispense.gateway.TransactionGateway;
import za.co.opsmobile.coindispense.framework.dipatcher.Dispatcher;

/**
 * Created by Daniel Oosthuizen on 2015/09/06.
 */
public class ActionCreatorFactory {
    public static DispenseActionCreator getDispenseActionCreator(Dispatcher dispatcher, Context context) {
        DispenseGateway dispenseGateway = GatewayFactory.getDispenseGateway(context);
        ConfigurationGateway configurationGateway = GatewayFactory.getConfigurationGateway();
        TransactionGateway transactionGateway = GatewayFactory.getTransactionGateway();
        return new DispenseActionCreator(dispatcher, dispenseGateway, configurationGateway, transactionGateway);
    }
}
