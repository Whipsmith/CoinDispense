package za.co.opsmobile.coindispense;

import za.co.opsmobile.coindispense.dispense.store.Denomination;
import za.co.opsmobile.coindispense.dispense.store.DispenseModel;
import za.co.opsmobile.coindispense.dispense.store.DispenseStore;
import za.co.opsmobile.coindispense.framework.dipatcher.Dispatcher;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public class ModelFactory {
    private static ModelFactory instance;
    private final DispenseModel dispenseModel;

    public static ModelFactory get(Dispatcher dispatcher) {
        if (instance == null) {
            Denomination[] validDenominations = getValidDenominations();
            instance = new ModelFactory(new DispenseStore(dispatcher, validDenominations));
        }

        return instance;
    }

    private static Denomination[] getValidDenominations() {
        return new Denomination[]{
                new Denomination(.5d, Denomination.CurrencyTypes.COIN)
                , new Denomination(.1d, Denomination.CurrencyTypes.COIN)
                , new Denomination(.25d, Denomination.CurrencyTypes.COIN)
                , new Denomination(.5d, Denomination.CurrencyTypes.COIN)
                , new Denomination(2d, Denomination.CurrencyTypes.COIN)
                , new Denomination(5d, Denomination.CurrencyTypes.COIN)
                , new Denomination(10d, Denomination.CurrencyTypes.NOTE)
                , new Denomination(20d, Denomination.CurrencyTypes.NOTE)
                , new Denomination(50d, Denomination.CurrencyTypes.NOTE)
                , new Denomination(100d, Denomination.CurrencyTypes.NOTE)
        };
    }

    private ModelFactory(DispenseModel dispenseModel) {
        this.dispenseModel = dispenseModel;
    }

    public DispenseModel getDispenseModel() {
        return dispenseModel;
    }
}
