package za.co.opsmobile.coindispense.framework.logging;

/**
 * Created by Daniel Oosthuizen on 2015/09/06.
 */
public abstract class GatewayError extends CoinDispenseError {

    public GatewayError(String detailMessage) {
        super(detailMessage);
    }

    public GatewayError(Throwable cause) {
        super(cause);
    }

    @Override
    public String getUserMessage() {
        return "A network error has occurred.";
    }
}
