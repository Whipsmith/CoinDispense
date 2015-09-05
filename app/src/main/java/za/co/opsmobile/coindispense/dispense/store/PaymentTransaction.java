package za.co.opsmobile.coindispense.dispense.store;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public class PaymentTransaction {
    private final ArrayList<Payment> payments;

    public PaymentTransaction(ArrayList<Payment> payments) {
        this.payments = payments;
    }


    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public double getValue() {
        return sumPayments();
    }

    private double sumPayments() {
        double total = 0;
        for (Payment payment : payments) {
            total += payment.getValue();
        }
        return total;
    }

    public static class Builder {
        public Builder(HashMap<Denomination, Integer> payments) {
        }

        public PaymentTransaction build() {
            return null;
        }
    }
}
