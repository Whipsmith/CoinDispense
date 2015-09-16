package za.co.opsmobile.coindispense.worker;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;

import za.co.opsmobile.coindispense.ChangeResult;

import static junit.framework.Assert.*;

/**
 * Created by Daniel Oosthuizen on 2015/09/13.
 */
public class ChangeWorkerTest {

    private BigDecimal[] denominations;

    @Before
    public void setUp() throws Exception {
        denominations = new BigDecimal[]{
                new BigDecimal(100f, new MathContext(3, RoundingMode.HALF_EVEN)),
                new BigDecimal(50f, new MathContext(3, RoundingMode.HALF_EVEN)),
                new BigDecimal(20f, new MathContext(3, RoundingMode.HALF_EVEN)),
                new BigDecimal(10f, new MathContext(3, RoundingMode.HALF_EVEN)),
                new BigDecimal(5f, new MathContext(3, RoundingMode.HALF_EVEN)),
                new BigDecimal(2f, new MathContext(3, RoundingMode.HALF_EVEN)),
                new BigDecimal(1f, new MathContext(3, RoundingMode.HALF_EVEN)),
                new BigDecimal(.5f, new MathContext(3, RoundingMode.HALF_EVEN)),
                new BigDecimal(.25f, new MathContext(3, RoundingMode.HALF_EVEN)),
                new BigDecimal(.1f, new MathContext(3, RoundingMode.HALF_EVEN)),
                new BigDecimal(.05f, new MathContext(3, RoundingMode.HALF_EVEN))
        };
    }

    @Test
    public void exactChangeFor100Rands() {
        ChangeWorker changeWorker = new ChangeWorker(denominations, BigDecimal.valueOf(100f));
        ChangeResult result = changeWorker.calculateChange();
        HashMap<BigDecimal, Integer> actual = result.getPayments();

        HashMap<BigDecimal, Integer> expected = new HashMap<>();
        expected.put(new BigDecimal(100f, new MathContext(3, RoundingMode.HALF_EVEN)), 1);

        assertEquals(expected, actual);
    }

    @Test
    public void exactChangeForAll() {
        BigDecimal total = BigDecimal.ZERO;
        HashMap<BigDecimal, Integer> expected = new HashMap<>();
        for (BigDecimal denomination : denominations) {
            total = total.add(denomination);
            expected.put(denomination, 1);
        }

        ChangeWorker changeWorker = new ChangeWorker(denominations, total);
        ChangeResult result = changeWorker.calculateChange();
        HashMap<BigDecimal, Integer> actual = result.getPayments();

        assertEquals(expected, actual);
    }

    @Test
    public void exactChangeFor100randAnd5Cents() {
        ChangeWorker changeWorker = new ChangeWorker(denominations, BigDecimal.valueOf(100.05f));

        ChangeResult result = changeWorker.calculateChange();
        HashMap<BigDecimal, Integer> actual = result.getPayments();

        HashMap<BigDecimal, Integer> expected = new HashMap<>();
        expected.put(new BigDecimal(100f, new MathContext(3, RoundingMode.HALF_EVEN)), 1);
        expected.put(new BigDecimal(.05, new MathContext(3, RoundingMode.HALF_EVEN)), 1);

        assertEquals(expected, actual);
    }

    @Test
    public void exactChangeFor30cents() {
        ChangeWorker changeWorker = new ChangeWorker(denominations, BigDecimal.valueOf(.30f));

        ChangeResult result = changeWorker.calculateChange();
        HashMap<BigDecimal, Integer> actual = result.getPayments();

        HashMap<BigDecimal, Integer> expected = new HashMap<>();
        expected.put(new BigDecimal(.25, new MathContext(3, RoundingMode.HALF_EVEN)), 1);
        expected.put(new BigDecimal(.05, new MathContext(3, RoundingMode.HALF_EVEN)), 1);

        assertEquals(expected, actual);
    }
}