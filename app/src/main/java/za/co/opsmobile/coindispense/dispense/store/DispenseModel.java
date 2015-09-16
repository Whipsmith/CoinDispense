package za.co.opsmobile.coindispense.dispense.store;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public interface DispenseModel {

    PaymentTransaction getPayments();

    PaymentTransaction getChange();

    ArrayList<BigDecimal> getValidDenominations();

    BigDecimal getCost();
}
