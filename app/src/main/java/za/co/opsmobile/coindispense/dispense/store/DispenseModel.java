package za.co.opsmobile.coindispense.dispense.store;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public interface DispenseModel {

    PaymentTransaction getPayments();

    PaymentTransaction getChange();

    ArrayList<Denomination> getValidDenominations();

    Float getCost();
}
