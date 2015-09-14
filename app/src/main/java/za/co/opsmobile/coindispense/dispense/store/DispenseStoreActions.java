package za.co.opsmobile.coindispense.dispense.store;

import java.util.HashMap;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public interface DispenseStoreActions {
    void initialise(Float[] validDenominations);

    void addPayment(Float denomination);

    void setChange(HashMap<Float, Integer> changeTransaction);

    void setCost(float cost);
}
