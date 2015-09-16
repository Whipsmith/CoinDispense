package za.co.opsmobile.coindispense;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by Daniel Oosthuizen on 2015/09/13.
 */
public class ChangeResult {
    private HashMap<BigDecimal, Integer> payments;

    public ChangeResult() {
        payments = new HashMap<>();
    }

    public HashMap<BigDecimal, Integer> getPayments() {
        return payments;
    }

    public void setPayments(HashMap<BigDecimal, Integer> payments) {
        this.payments = payments;
    }

    public void addPayment(BigDecimal payment) {
        if (payments.containsKey(payment)) {
            payments.put(payment, payments.get(payment) + 1);
        } else {
            payments.put(payment, 1);
        }
    }
}
