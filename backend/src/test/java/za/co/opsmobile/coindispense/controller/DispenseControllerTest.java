package za.co.opsmobile.coindispense.controller;

import org.junit.Test;

import java.util.HashMap;

import za.co.opsmobile.coindispense.CalculateChangeRequest;
import za.co.opsmobile.coindispense.ChangeResult;

import static org.junit.Assert.*;

/**
 * Created by Daniel Oosthuizen on 2015/09/16.
 */
public class DispenseControllerTest {

    @Test
    public void changeShouldEqualPaymentMinusCost() throws Exception {
        float cost = 50f;
        float payment = 100f;
        float expected = payment - cost;
        DispenseController controllerUnderTest = new DispenseController();

        CalculateChangeRequest request = new CalculateChangeRequest();
        request.setCost(cost);
        request.setPayment(payment);

        float actual = 0f;
        ChangeResult changeResult = controllerUnderTest.calculateChange(request);
        HashMap<Float, Integer> payments = changeResult.getPayments();
        for (Float key : payments.keySet()) {
            actual += (key * payments.get(key));
        }

        assertEquals(expected, actual, 0.01);
    }
}