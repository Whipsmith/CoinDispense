package za.co.opsmobile.coindispense.framework.logging;

/**
 * Created by Daniel Oosthuizen on 2015/09/06.
 */
public abstract class ModelError extends CoinDispenseError{

    public ModelError(String detailMessage) {
        super(detailMessage);
    }

    public ModelError(Throwable cause) {
        super(cause);
    }
}
