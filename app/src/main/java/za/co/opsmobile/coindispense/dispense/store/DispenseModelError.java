package za.co.opsmobile.coindispense.dispense.store;

import za.co.opsmobile.coindispense.framework.logging.ModelError;

/**
 * Created by Daniel Oosthuizen on 2015/09/06.
 */
public class DispenseModelError extends ModelError {

    private String userMessage;

    public DispenseModelError(String detailMessage) {
        super(detailMessage);
        userMessage = detailMessage;

    }

    public DispenseModelError(Throwable cause) {
        super(cause);
    }

    @Override
    public String getUserMessage() {
        return userMessage;
    }
}
