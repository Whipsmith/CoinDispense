package za.co.opsmobile.coindispense.worker;


import java.util.HashMap;

import za.co.opsmobile.coindispense.ChangeResult;

/**
 * Created by Daniel Oosthuizen on 2015/09/13.
 */
public class ChangeWorker {
    private final float[] denominations;
    private final float change;


    public ChangeWorker(float[] denominations, float change) {
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

        float payment = denominations[i];
        while (getAmountOutstanding(result, change) >= payment) {
            result.addPayment(payment);
        }
        i++;
        addPayments(result, i);
        return result;
    }

    private float getAmountOutstanding(ChangeResult result, float change) {
        HashMap<Float, Integer> payments = result.getPayments();
        Float total = 0f;
        for (Float denomination : payments.keySet()) {
            total += denomination * payments.get(denomination);
        }
        return change - total;
    }


}
