package za.co.opsmobile.coindispense.factory;

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
            instance = new ModelFactory(new DispenseStore(dispatcher));
        }

        return instance;
    }


    private ModelFactory(DispenseModel dispenseModel) {
        this.dispenseModel = dispenseModel;
    }

    public DispenseModel getDispenseModel() {
        return dispenseModel;
    }
}
