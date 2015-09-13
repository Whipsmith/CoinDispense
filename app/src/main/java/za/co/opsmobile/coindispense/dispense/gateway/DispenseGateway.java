package za.co.opsmobile.coindispense.dispense.gateway;

import za.co.opsmobile.coindispense.dispense.action.DispenseActionCreator;
import za.co.opsmobile.coindispense.dispense.store.PaymentTransaction;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public interface DispenseGateway {

    void calculateChange(PaymentTransaction transaction, DispenseActionCreator dispenseActionCreator);
}
