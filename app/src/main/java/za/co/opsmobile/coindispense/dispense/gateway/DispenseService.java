package za.co.opsmobile.coindispense.dispense.gateway;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import za.co.opsmobile.coindispense.CalculateChangeRequest;
import za.co.opsmobile.coindispense.ChangeResult;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public interface DispenseService {

    @POST("calculateChange")
    @Headers("Accept:application/json")
    Call<ChangeResult> calculateChange(@Body CalculateChangeRequest request);

}
