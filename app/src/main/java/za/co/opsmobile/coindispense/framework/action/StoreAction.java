package za.co.opsmobile.coindispense.framework.action;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public interface StoreAction<K> {
    void executeAction(K store);
}
