package za.co.opsmobile.coindispense.login.gateway;


import retrofit.Call;
import retrofit.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by Daniel Oosthuizen on 2015/09/14.
 */
public interface LoginService {

    @FormUrlEncoded
    @POST("/login")
    Call<String> login(@Field("username") String username, @Field("password") String password);
}
