package za.co.opsmobile.coindispense.dispense.store;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public interface DispenseStoreActions {
    void initialise(BigDecimal[] validDenominations);

    void addPayment(BigDecimal denomination);

    void setChange(HashMap<BigDecimal, Integer> changeTransaction);

    void setCost(BigDecimal cost);
}
