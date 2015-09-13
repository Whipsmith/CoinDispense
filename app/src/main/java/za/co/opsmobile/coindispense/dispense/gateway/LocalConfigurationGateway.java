package za.co.opsmobile.coindispense.dispense.gateway;

import retrofit.Callback;
import retrofit.Response;
import za.co.opsmobile.coindispense.dispense.action.DispenseActionCreator;
import za.co.opsmobile.coindispense.dispense.store.Denomination;

/**
 * Created by Daniel Oosthuizen on 2015/09/06.
 */
public class LocalConfigurationGateway implements ConfigurationGateway {

    @Override
    public void getValidDenominations(final DispenseActionCreator dispenseActionCreator) {
        dispenseActionCreator.onValidDenominationsFetched(getValidDenominations());
    }

    private Denomination[] getValidDenominations() {
        return new Denomination[]{
                new Denomination(.5f, Denomination.CurrencyTypes.COIN)
                , new Denomination(.1f, Denomination.CurrencyTypes.COIN)
                , new Denomination(.25f, Denomination.CurrencyTypes.COIN)
                , new Denomination(.5f, Denomination.CurrencyTypes.COIN)
                , new Denomination(2f, Denomination.CurrencyTypes.COIN)
                , new Denomination(5f, Denomination.CurrencyTypes.COIN)
                , new Denomination(10f, Denomination.CurrencyTypes.NOTE)
                , new Denomination(20f, Denomination.CurrencyTypes.NOTE)
                , new Denomination(50f, Denomination.CurrencyTypes.NOTE)
                , new Denomination(100f, Denomination.CurrencyTypes.NOTE)
        };
    }
}
