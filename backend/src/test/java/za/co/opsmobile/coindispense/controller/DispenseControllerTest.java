package za.co.opsmobile.coindispense.controller;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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
        BigDecimal cost = new BigDecimal(50f);
        BigDecimal payment = new BigDecimal(100f);
        BigDecimal expected = payment.subtract(cost);
        DispenseController controllerUnderTest = new DispenseController();

        CalculateChangeRequest request = new CalculateChangeRequest();
        request.setCost(cost);
        request.setPayment(payment);

        BigDecimal actual = BigDecimal.ZERO;
        ChangeResult changeResult = controllerUnderTest.calculateChange(request);
        HashMap<BigDecimal, Integer> payments = changeResult.getPayments();
        for (BigDecimal key : payments.keySet()) {
            actual = actual.add(
                    key.multiply(BigDecimal.valueOf(payments.get(key)))
            );
        }

        assertEquals(expected, actual);
    }

    @Test
         public void exactChangeFor1rand5centsOn75cents() {
        BigDecimal cost = new BigDecimal(.75);
        BigDecimal payment = new BigDecimal(1.05, new MathContext(3, RoundingMode.HALF_DOWN));
        BigDecimal expected = payment.subtract(cost);
        DispenseController controllerUnderTest = new DispenseController();

        CalculateChangeRequest request = new CalculateChangeRequest();
        request.setCost(cost);
        request.setPayment(payment);

        BigDecimal actual = BigDecimal.ZERO;
        ChangeResult changeResult = controllerUnderTest.calculateChange(request);
        HashMap<BigDecimal, Integer> payments = changeResult.getPayments();
        for (BigDecimal key : payments.keySet()) {
            actual = actual.add(
                    key.multiply(BigDecimal.valueOf(payments.get(key)))
            );
        }

        assertEquals(expected, actual);
    }

    @Test
    public void exactChangeFor100randsOn9rand30cents() {
        BigDecimal cost = new BigDecimal(9.3, new MathContext(3, RoundingMode.HALF_DOWN));
        BigDecimal payment = new BigDecimal(100, new MathContext(3, RoundingMode.HALF_DOWN));
        BigDecimal expected = payment.subtract(cost);
        DispenseController controllerUnderTest = new DispenseController();

        CalculateChangeRequest request = new CalculateChangeRequest();
        request.setCost(cost);
        request.setPayment(payment);

        BigDecimal actual = BigDecimal.ZERO;
        ChangeResult changeResult = controllerUnderTest.calculateChange(request);
        HashMap<BigDecimal, Integer> payments = changeResult.getPayments();
        for (BigDecimal key : payments.keySet()) {
            actual = actual.add(
                    key.multiply(BigDecimal.valueOf(payments.get(key)))
            );
        }

        assertEquals(expected, actual);
    }
}