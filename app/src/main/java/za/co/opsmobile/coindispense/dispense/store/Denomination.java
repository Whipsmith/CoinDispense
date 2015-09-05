package za.co.opsmobile.coindispense.dispense.store;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public class Denomination {

    private final double value;
    private final CurrencyTypes currencyType;

    public Denomination(double value, CurrencyTypes currencyType) {
        this.value = value;
        this.currencyType = currencyType;
    }

    public double getValue(int count) {
        return value * count;
    }


    public enum CurrencyTypes {
        NOTE,
        COIN
    }
}
