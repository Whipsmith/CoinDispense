package za.co.opsmobile.coindispense.framework.logging;

import android.util.Log;

/**
 * Created by Daniel Oosthuizen on 2015/09/06.
 */
public class DefaultLogger implements CoinDispenseLogger {
    @Override
    public void oneEventAsync(CoinDispenseError error) {
        Log.d(error.getClass().getSimpleName(), getLogString(error));
    }

    private String getLogString(CoinDispenseError error) {
        String newLine = "\n";
        StringBuilder sb = new StringBuilder(error.getUserMessage())
        .append(newLine)
        .append(String.format("Cause: [%s]", error.getCause().getMessage()));
        return sb.toString();
    }
}
