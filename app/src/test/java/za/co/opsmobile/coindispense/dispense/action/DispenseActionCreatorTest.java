package za.co.opsmobile.coindispense.dispense.action;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import za.co.opsmobile.coindispense.dispense.gateway.ConfigurationGateway;
import za.co.opsmobile.coindispense.dispense.gateway.DispenseGateway;
import za.co.opsmobile.coindispense.dispense.gateway.TransactionGateway;
import za.co.opsmobile.coindispense.dispense.store.Denomination;
import za.co.opsmobile.coindispense.dispense.store.DispenseModelError;
import za.co.opsmobile.coindispense.dispense.store.Payment;
import za.co.opsmobile.coindispense.dispense.store.PaymentTransaction;
import za.co.opsmobile.coindispense.framework.action.StoreActionEvent;
import za.co.opsmobile.coindispense.framework.dipatcher.Dispatcher;
import za.co.opsmobile.coindispense.framework.logging.CoinDispenseError;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Daniel Oosthuizen on 2015/09/06.
 */
public class DispenseActionCreatorTest {

    @Mock
    Dispatcher mockDispatcher;

    @Mock
    DispenseGateway dispenseGateway;

    @Mock
    ConfigurationGateway configurationGateway;

    @Mock
    TransactionGateway transactionGateway;

    DispenseActionCreator actionCreatorUnderTest;


    @Before
    public void setUp() {
        initMocks(this);
        actionCreatorUnderTest = new DispenseActionCreator(mockDispatcher, dispenseGateway, configurationGateway, transactionGateway);
    }

    @After
    public void tearDown() {
        actionCreatorUnderTest = null;
        mockDispatcher = null;
    }

    @Test
    public void testInitialiseValidDenominations(){
        actionCreatorUnderTest.initialiseValidDenominations();
        verify(configurationGateway).getValidDenominations(actionCreatorUnderTest);
    }

    @Test
    public void testOnValidDenominationsFetched(){
        Denomination[] validDenominations = new Denomination[0];
        actionCreatorUnderTest.onValidDenominationsFetched(validDenominations);
        StoreActionEvent expectedActionEvent = new DispenseStoreActionEvent(new ValidDenominationsFetchedAction(validDenominations));
        verify(mockDispatcher).dispatchAction(expectedActionEvent);
    }

    @Test
    public void testAddPayment(){
        Denomination denomination = new Denomination(10f, Denomination.CurrencyTypes.NOTE);
        actionCreatorUnderTest.addPayment(denomination);
        StoreActionEvent expectedActionEvent = new DispenseStoreActionEvent(new AddPaymentAction(denomination));
        verify(mockDispatcher).dispatchAction(expectedActionEvent);
    }

    @Test
    public void testCalculateChange(){
        ArrayList<Payment> payments = new ArrayList<>();
        actionCreatorUnderTest.calculateChange(payments);
        verify(dispenseGateway).calculateChange(new PaymentTransaction(payments), actionCreatorUnderTest);
    }

    @Test
    public void testOnChangeCalculated(){
        PaymentTransaction change = new PaymentTransaction(new ArrayList<Payment>());
        actionCreatorUnderTest.onChangeCalculated(change);
        StoreActionEvent expectedAction = new DispenseStoreActionEvent(new ChangeCalculatedAction(change));
        verify(mockDispatcher).dispatchAction(expectedAction);
    }

    @Test
    public void testSendError(){
        CoinDispenseError error = new DispenseModelError("");
        actionCreatorUnderTest.sendError(error);
        verify(mockDispatcher).emitError(error);
    }
}