package za.co.opsmobile.coindispense;

import java.util.HashMap;

/**
 * Created by Daniel Oosthuizen on 2015/09/13.
 */
public class ChangeResult {
    private HashMap<Float, Integer> payments;

    public ChangeResult() {
        payments = new HashMap<>();
    }

    public HashMap<Float, Integer> getPayments() {
        return payments;
    }

    public void setPayments(HashMap<Float, Integer> payments) {
        this.payments = payments;
    }

    public void addPayment(float payment) {
        if (payments.containsKey(payment)) {
            payments.put(payment, payments.get(payment) + 1);
        } else {
            payments.put(payment, 1);
        }
    }
}
