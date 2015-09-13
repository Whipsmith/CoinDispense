package za.co.opsmobile.coindispense.framework.logging;

/**
 * Created by Daniel Oosthuizen on 2015/09/06.
 */
public abstract class CoinDispenseError extends Throwable{
    public CoinDispenseError(String detailMessage) {
        super(detailMessage);
    }

    public CoinDispenseError(Throwable cause) {
        super(cause);
    }

    public abstract String getUserMessage();
}
