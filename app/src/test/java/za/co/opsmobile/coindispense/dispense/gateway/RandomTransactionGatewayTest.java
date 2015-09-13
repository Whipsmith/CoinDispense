package za.co.opsmobile.coindispense.dispense.gateway;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import za.co.opsmobile.coindispense.dispense.action.DispenseActionCreator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Daniel Oosthuizen on 2015/09/07.
 */
public class RandomTransactionGatewayTest {

    @Test
    public void badInconsistentRandomizerTest() {
        RandomTransactionGateway randomTransactionGateway = new RandomTransactionGateway();
        DispenseActionCreator mockActionCreator = mock(DispenseActionCreator.class);
        ArgumentCaptor<Float> costCaptor = ArgumentCaptor.forClass(Float.class);
        for (int i = 0; i < 1000; i++) {
            randomTransactionGateway.getCost(mockActionCreator);
            verify(mockActionCreator, times(i + 1)).onCostFetched(costCaptor.capture());
            Float value = costCaptor.getValue();
            assertTrue(value.compareTo(.05f) >= 0);
            assertTrue(value.compareTo(500f) <= 0);
        }
    }
}