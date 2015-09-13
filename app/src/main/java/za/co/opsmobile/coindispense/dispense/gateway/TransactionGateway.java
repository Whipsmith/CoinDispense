package za.co.opsmobile.coindispense.dispense.gateway;

import za.co.opsmobile.coindispense.dispense.action.DispenseActionCreator;

/**
 * Created by Daniel Oosthuizen on 2015/09/07.
 */
public interface TransactionGateway {
    void getCost(DispenseActionCreator dispenseActionCreator);
}
