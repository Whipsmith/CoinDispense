package za.co.opsmobile.coindispense.dispense.action;

import java.util.ArrayList;
import java.util.HashMap;

import za.co.opsmobile.coindispense.dispense.gateway.ConfigurationGateway;
import za.co.opsmobile.coindispense.dispense.gateway.DispenseGateway;
import za.co.opsmobile.coindispense.dispense.gateway.TransactionGateway;
import za.co.opsmobile.coindispense.dispense.store.Payment;
import za.co.opsmobile.coindispense.dispense.store.PaymentTransaction;
import za.co.opsmobile.coindispense.framework.dipatcher.Dispatcher;
import za.co.opsmobile.coindispense.framework.logging.CoinDispenseError;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public class DispenseActionCreator {

    private final Dispatcher dispatcher;
    private final DispenseGateway dispenseGateway;
    private final ConfigurationGateway configurationGateway;
    private final TransactionGateway transactionGateway;

    public DispenseActionCreator(Dispatcher dispatcher, DispenseGateway dispenseGateway, ConfigurationGateway configurationGateway, TransactionGateway transactionGateway) {
        this.dispatcher = dispatcher;
        this.dispenseGateway = dispenseGateway;
        this.configurationGateway = configurationGateway;
        this.transactionGateway = transactionGateway;
    }

    public void fetchCost() {
        transactionGateway.getCost(this);
    }

    public void onCostFetched(float cost) {
        CostFetchedAction costFetchedAction = new CostFetchedAction(cost);
        dispatcher.dispatchAction(new DispenseStoreActionEvent(costFetchedAction));
    }

    public void initialiseValidDenominations() {
        configurationGateway.getValidDenominations(this);
    }

    public void onValidDenominationsFetched(Float[] validDenominations) {
        ValidDenominationsFetchedAction validDenominationsFetchedAction = new ValidDenominationsFetchedAction(validDenominations);
        dispatcher.dispatchAction(new DispenseStoreActionEvent(validDenominationsFetchedAction));
    }

    public void addPayment(Float denomination) {
        AddPaymentAction addPaymentAction = new AddPaymentAction(denomination);
        dispatcher.dispatchAction(new DispenseStoreActionEvent(addPaymentAction));
    }

    public void calculateChange(ArrayList<Payment> payments, Float cost) {
        PaymentTransaction changeTransaction = new PaymentTransaction(payments);
        dispenseGateway.calculateChange(changeTransaction, cost, this);
    }

    public void onChangeCalculated(HashMap<Float, Integer> change) {
        ChangeCalculatedAction changeCalculatedAction = new ChangeCalculatedAction(change);
        dispatcher.dispatchAction(new DispenseStoreActionEvent(changeCalculatedAction));
    }

    public void sendError(CoinDispenseError error) {
        dispatcher.emitError(error);
    }
}
