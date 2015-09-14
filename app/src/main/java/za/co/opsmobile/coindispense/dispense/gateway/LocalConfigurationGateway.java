package za.co.opsmobile.coindispense.dispense.gateway;

import za.co.opsmobile.coindispense.dispense.action.DispenseActionCreator;

/**
 * Created by Daniel Oosthuizen on 2015/09/06.
 */
public class LocalConfigurationGateway implements ConfigurationGateway {

    @Override
    public void getValidDenominations(final DispenseActionCreator dispenseActionCreator) {
        dispenseActionCreator.onValidDenominationsFetched(getValidDenominations());
    }

    private Float[] getValidDenominations() {
        return new Float[]{.05f, .1f, .25f, .5f, 1f, 2f, 5f, 10f, 20f, 50f, 100f};
    }
}
