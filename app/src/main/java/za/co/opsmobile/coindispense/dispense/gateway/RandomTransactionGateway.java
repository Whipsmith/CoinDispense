package za.co.opsmobile.coindispense.dispense.gateway;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Random;

import za.co.opsmobile.coindispense.dispense.action.DispenseActionCreator;

/**
 * Created by Daniel Oosthuizen on 2015/09/07.
 */
public class RandomTransactionGateway implements TransactionGateway {

    @Override
    public void getCost(DispenseActionCreator dispenseActionCreator) {
        dispenseActionCreator.onCostFetched(generateRandomCost());
    }

    private BigDecimal generateRandomCost() {
        Random randomizer = new Random();
        int i = randomizer.nextInt(10000);
        if (i == 0) {
            i = 1;
        }

        return new BigDecimal(.05f * i, new MathContext(2, RoundingMode.HALF_DOWN));
    }
}
