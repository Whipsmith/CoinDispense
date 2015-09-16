package za.co.opsmobile.coindispense.login.gateway;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.concurrent.Executors;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Converter;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import za.co.opsmobile.coindispense.framework.logging.GatewayError;
import za.co.opsmobile.coindispense.framework.security.SecurityUtil;
import za.co.opsmobile.coindispense.login.action.LoginActionCreator;

/**
 * Created by Daniel Oosthuizen on 2015/09/14.
 */
public class RetrofitLoginGateway implements LoginGateway {
    private final LoginService loginService;
    private final SecurityUtil securityUtil;

    public RetrofitLoginGateway(String restUrl, final SecurityUtil securityUtil) {
        this.securityUtil = securityUtil;
        OkHttpClient client = new OkHttpClient();
        client.setCookieHandler(securityUtil.getCookieManager());

        Retrofit retrofit = new Retrofit.Builder()
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverter(String.class, new StringConverter())
                .baseUrl(restUrl)
                .client(client)
                .build();
        loginService = retrofit.create(LoginService.class);
    }


    @Override
    public void login(String username, String password, final LoginActionCreator loginActionCreator) {
        Call call = loginService.login(username, password);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Response response) {
                if (securityUtil.getCookie() != null) {
                    loginActionCreator.onLoginSuccessful();
                } else {
                    loginActionCreator.sendError(new LoginGatewayError("Login failed"));
                }
            }
            @Override
            public void onFailure(Throwable t) {
                loginActionCreator.sendError(new LoginGatewayError(t));
            }
        });
    }

    private class LoginGatewayError extends GatewayError{
        public LoginGatewayError(Throwable t) {
            super(t);
        }

        public LoginGatewayError(String detailMessage) {
            super(detailMessage);
        }
    }


    static class StringConverter implements Converter {

        @Override
        public Object fromBody(ResponseBody body) throws IOException {
            return null;
        }

        @Override
        public RequestBody toBody(Object value) {
            return null;
        }
    }
}
