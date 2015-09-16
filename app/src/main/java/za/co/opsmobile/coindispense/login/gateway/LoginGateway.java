package za.co.opsmobile.coindispense.login.gateway;

import za.co.opsmobile.coindispense.login.action.LoginActionCreator;

/**
 * Created by Daniel Oosthuizen on 2015/09/14.
 */
public interface LoginGateway {
    void login(String username, String password, LoginActionCreator loginActionCreator);
}
