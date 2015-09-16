package za.co.opsmobile.coindispense.framework.security;


import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Created by Daniel Oosthuizen on 2015/09/14.
 */
public class SecurityUtil {
    private static final String SET_COOKIE_KEY = "Set-Cookie";
    private String cookie;
    private String cookieKey = "JSESSIONID";

    private static SecurityUtil instance;
    private CookieManager customCookieManager = new CustomCookieManager();
    ;

    public static SecurityUtil getInstance() {
        if (instance == null) {
            instance = new SecurityUtil();
        }
        return instance;
    }


    private SecurityUtil() {
    }

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
                if (cookie != null) {
                    Request originalRequest = chain.request();
                    Request newRequest;

                    newRequest = originalRequest.newBuilder()
                            .addHeader("Cookie", cookieKey + "=" + SecurityUtil.this.cookie)
                            .build();


                    return chain.proceed(newRequest);
                }

                return chain.proceed(chain.request());
            }
        };
    }

    public CookieManager getCookieManager() {
        return customCookieManager;
    }

    public class CustomCookieManager extends CookieManager {


        /**
         * Creates a new instance of this cookie manager accepting all cookies.
         */
        public CustomCookieManager() {
            super.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        }

        @Override
        public void put(URI uri, Map<String, List<String>> responseHeaders) throws IOException {

            super.put(uri, responseHeaders);

            if (responseHeaders == null || responseHeaders.get(SET_COOKIE_KEY) == null) {
                // No cookies in this response, simply return from this method.
                return;
            }

            // Yes, we've found cookies, inspect them for the key we're looking for.
            for (String possibleSessionCookieValues : responseHeaders.get(SET_COOKIE_KEY)) {

                if (possibleSessionCookieValues != null) {

                    for (String possibleSessionCookie : possibleSessionCookieValues.split(";")) {

                        if (possibleSessionCookie.startsWith(cookieKey) && possibleSessionCookie.contains("=")) {
                            cookie = possibleSessionCookie.split("=")[1];
                            return;
                        }
                    }
                }
            }
        }
    }
}
