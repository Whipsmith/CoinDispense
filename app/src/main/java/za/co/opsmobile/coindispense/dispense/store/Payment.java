package za.co.opsmobile.coindispense.dispense.store;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public class Payment {

    private final int count;
    private final Denomination denomination;

    public Payment(int count, Denomination denomination) {
        this.count = count;
        this.denomination = denomination;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public double getValue() {
        return denomination.getValue(count);
    }
}
