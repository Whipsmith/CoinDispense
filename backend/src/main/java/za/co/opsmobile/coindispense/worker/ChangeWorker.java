package za.co.opsmobile.coindispense.worker;


import java.math.BigDecimal;
import java.util.HashMap;

import za.co.opsmobile.coindispense.ChangeResult;

/**
 * Created by Daniel Oosthuizen on 2015/09/13.
 */
public class ChangeWorker {
    private final BigDecimal[] denominations;
    private final BigDecimal change;


    public ChangeWorker(BigDecimal[] denominations, BigDecimal change) {
        this.denominations = denominations;
        this.change = change;
    }

    public ChangeResult calculateChange() {
        ChangeResult result = new ChangeResult();
        int position = 0;
        return addPayments(result, position);
    }

    private ChangeResult addPayments(ChangeResult result, int i) {
        if (i >= denominations.length) {
            return result;
        }

        BigDecimal payment = denominations[i];
        while (getAmountOutstanding(result, change).compareTo(payment) >= 0) {
            result.addPayment(payment);
        }
        i++;
        addPayments(result, i);
        return result;
    }

    private BigDecimal getAmountOutstanding(ChangeResult result, BigDecimal change) {
        HashMap<BigDecimal, Integer> payments = result.getPayments();
        BigDecimal total = BigDecimal.ZERO;
        for (BigDecimal denomination : payments.keySet()) {
            total = total.add(
                    denomination.multiply(new BigDecimal(payments.get(denomination)))
            );
        }
        return change.subtract(total);
    }


}
