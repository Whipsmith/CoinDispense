package za.co.opsmobile.coindispense.dispense.gateway;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import za.co.opsmobile.coindispense.dispense.action.DispenseActionCreator;

/**
 * Created by Daniel Oosthuizen on 2015/09/06.
 */
public class LocalConfigurationGateway implements ConfigurationGateway {

    @Override
    public void getValidDenominations(final DispenseActionCreator dispenseActionCreator) {
        dispenseActionCreator.onValidDenominationsFetched(getValidDenominations());
    }

    private BigDecimal[] getValidDenominations() {
        return new BigDecimal[]{
                new BigDecimal(100, new MathContext(3, RoundingMode.HALF_DOWN)),
                new BigDecimal(50, new MathContext(3, RoundingMode.HALF_DOWN)),
                new BigDecimal(20, new MathContext(3, RoundingMode.HALF_DOWN)),
                new BigDecimal(10, new MathContext(3, RoundingMode.HALF_DOWN)),
                new BigDecimal(5, new MathContext(3, RoundingMode.HALF_DOWN)),
                new BigDecimal(2, new MathContext(3, RoundingMode.HALF_DOWN)),
                new BigDecimal(1, new MathContext(3, RoundingMode.HALF_DOWN)),
                new BigDecimal(.5, new MathContext(3, RoundingMode.HALF_DOWN)),
                new BigDecimal(.25, new MathContext(3, RoundingMode.HALF_DOWN)),
                new BigDecimal(.1, new MathContext(2, RoundingMode.HALF_DOWN)),
                new BigDecimal(.05, new MathContext(1, RoundingMode.HALF_DOWN))
        };
    }
}
