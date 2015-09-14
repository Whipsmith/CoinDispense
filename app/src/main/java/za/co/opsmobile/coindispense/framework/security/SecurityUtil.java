package za.co.opsmobile.coindispense.framework.security;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Daniel Oosthuizen on 2015/09/14.
 */
public class SecurityUtil {
    private String cookie;
    private String cookieKey;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public Interceptor getInterceptor() {
        return new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                if (cookie != null) {
                    originalRequest.newBuilder()
                            .addHeader("Cookie", cookieKey + "=" + cookie);
                }

                Response response = chain.proceed(chain.request());

                // Do anything with response here

                return response;
            }
        };
    }


}
