package za.co.opsmobile.coindispense.login.action;

import android.text.Editable;

import za.co.opsmobile.coindispense.framework.dipatcher.Dispatcher;
import za.co.opsmobile.coindispense.framework.logging.CoinDispenseError;
import za.co.opsmobile.coindispense.login.gateway.LoginGateway;
import za.co.opsmobile.coindispense.login.gateway.RetrofitLoginGateway;
import za.co.opsmobile.coindispense.login.store.LoginStoreModelChangedEvent;

/**
 * Created by Daniel Oosthuizen on 2015/09/14.
 */
public class LoginActionCreator {

    private final Dispatcher dispatcher;
    private final LoginGateway loginGateway;

    public LoginActionCreator(Dispatcher dispatcher, LoginGateway loginGateway) {
        this.dispatcher = dispatcher;
        this.loginGateway = loginGateway;
    }

    public void sendError(CoinDispenseError error) {
        dispatcher.emitError(error);
    }

    public void onLoginSuccessful() {
        dispatcher.emitChange(new LoginStoreModelChangedEvent());
    }

    public void login(String username, String password) {
        loginGateway.login(username, password, this);
    }
}
