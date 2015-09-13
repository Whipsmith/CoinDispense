package za.co.opsmobile.coindispense.dispense.store;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public interface DispenseStoreActions {
    void initialise(Denomination[] validDenominations);

    void addPayment(Denomination denomination);

    void setChange(PaymentTransaction changeTransaction);

    void setCost(float cost);
}
