package za.co.opsmobile.coindispense.dispense.store;

import android.support.annotation.NonNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;

import java.util.ArrayList;

import za.co.opsmobile.coindispense.framework.dipatcher.Dispatcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Daniel Oosthuizen on 2015/09/06.
 */
public class DispenseStoreTest {

    @Mock
    Dispatcher mockDispatcher;

    DispenseStore storeUnderTest;
    private Denomination validDenomination;
    private Denomination inValidDenomination;


    @Before
    public void setUp() {
        initMocks(this);
        storeUnderTest = new DispenseStore(mockDispatcher);
        validDenomination = new Denomination(100f, Denomination.CurrencyTypes.NOTE);
        inValidDenomination = new Denomination(70f, Denomination.CurrencyTypes.NOTE);
    }

    @After
    public void tearDown() {
        storeUnderTest = null;
        mockDispatcher = null;
    }

    @Test
    public void addValidPaymentR100ShouldEmitStoreChange() {
        storeUnderTest.initialise(new Denomination[]{validDenomination});

        verify(mockDispatcher).emitChange(Matchers.any(DispenseModelChangedEvent.class));

        storeUnderTest.addPayment(validDenomination);

        verify(mockDispatcher, times(2)).emitChange(Matchers.any(DispenseModelChangedEvent.class));
        verifyNoMoreInteractions(mockDispatcher);
    }

    @Test
    public void addValidPaymentR100ShouldReturnCorrectPayment() {
        storeUnderTest.initialise(new Denomination[]{validDenomination});

        storeUnderTest.addPayment(validDenomination);
        PaymentTransaction payments = storeUnderTest.getPayments();
        double expectedValue = validDenomination.getValue(1);

        assertEquals(expectedValue, payments.getValue(), 0.001);
    }

    @Test
    public void addPaymentWithoutInitialisingShouldEmitStoreError() {
        storeUnderTest.addPayment(inValidDenomination);

        verify(mockDispatcher).emitError(Matchers.any(DispenseModelError.class));
    }

    @Test
    public void addPaymentNotInValidDenominationShouldEmitStoreError() {
        storeUnderTest.initialise(new Denomination[]{validDenomination});

        storeUnderTest.addPayment(inValidDenomination);

        verify(mockDispatcher).emitError(Matchers.any(DispenseModelError.class));
    }

    @Test
    public void setChangeShouldReturnCorrectly() {
        PaymentTransaction expected;
        expected = getPaymentTransaction();

        storeUnderTest.setChange(expected);
        PaymentTransaction actual = storeUnderTest.getChange();

        assertEquals(expected, actual);
    }



    @Test
    public void addingPaymentAfterSettingChangeShouldClearChange() {
        storeUnderTest.initialise(new Denomination[]{validDenomination});
        storeUnderTest.addPayment(validDenomination);
        PaymentTransaction change = getPaymentTransaction();
        storeUnderTest.setChange(change);

        storeUnderTest.addPayment(validDenomination);

        assertNull(storeUnderTest.getChange());
    }



    @NonNull
    private PaymentTransaction getPaymentTransaction() {
        ArrayList<Payment> payments = new ArrayList<>();
        payments.add(new Payment(1, validDenomination));
        return new PaymentTransaction(payments);
    }

}