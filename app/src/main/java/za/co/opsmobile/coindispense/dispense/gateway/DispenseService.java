package za.co.opsmobile.coindispense.dispense.gateway;

import retrofit.Call;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import za.co.opsmobile.coindispense.CalculateChangeRequest;
import za.co.opsmobile.coindispense.ChangeResult;
import za.co.opsmobile.coindispense.dispense.store.Denomination;
import za.co.opsmobile.coindispense.dispense.store.PaymentTransaction;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public interface DispenseService {
    @POST("calculateChange")
    Call<ChangeResult> calculateChange(CalculateChangeRequest request, Callback<PaymentTransaction> cb);

}
