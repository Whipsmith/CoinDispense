package za.co.opsmobile.coindispense.worker;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import za.co.opsmobile.coindispense.ChangeResult;

import static junit.framework.Assert.*;

/**
 * Created by Daniel Oosthuizen on 2015/09/13.
 */
public class ChangeWorkerTest {

    private float[] denominations;

    @Before
    public void setUp() throws Exception {
        denominations = new float[]{100f, 50f, 20f, 10f, 5f, 2f, 1f, .5f, .25f, .10f, .05f};
    }

    @Test
    public void exactChangeFor100Rands() {
        ChangeWorker changeWorker = new ChangeWorker(denominations, 100f);
        ChangeResult result = changeWorker.calculateChange();
        HashMap<Float, Integer> actual = result.getPayments();

        HashMap<Float, Integer> expected = new HashMap<>();
        expected.put(100f, 1);

        assertEquals(expected, actual);
    }

    @Test
    public void exactChangeForAll() {
        float total = 0f;
        HashMap<Float, Integer> expected = new HashMap<>();
        for (float denomination : denominations) {
            total += denomination;
            expected.put(denomination, 1);
        }

        ChangeWorker changeWorker = new ChangeWorker(denominations, total);
        ChangeResult result = changeWorker.calculateChange();
        HashMap<Float, Integer> actual = result.getPayments();

        assertEquals(expected, actual);
    }

    @Test
    public void exactChangeFor100randAnd5Cents() {
        ChangeWorker changeWorker = new ChangeWorker(denominations, 100.05f);

        ChangeResult result = changeWorker.calculateChange();
        HashMap<Float, Integer> actual = result.getPayments();

        HashMap<Float, Integer> expected = new HashMap<>();
        expected.put(100f, 1);
        expected.put(.05f, 1);

        assertEquals(expected, actual);
    }

    @Test
    public void exactChangeFor30cents() {
        ChangeWorker changeWorker = new ChangeWorker(denominations, .30f);

        ChangeResult result = changeWorker.calculateChange();
        HashMap<Float, Integer> actual = result.getPayments();

        HashMap<Float, Integer> expected = new HashMap<>();
        expected.put(.25f, 1);
        expected.put(.05f, 1);

        assertEquals(expected, actual);
    }
}