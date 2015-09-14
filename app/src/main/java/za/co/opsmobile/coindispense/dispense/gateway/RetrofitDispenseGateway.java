package za.co.opsmobile.coindispense.dispense.gateway;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.Executors;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import za.co.opsmobile.coindispense.CalculateChangeRequest;
import za.co.opsmobile.coindispense.ChangeResult;
import za.co.opsmobile.coindispense.dispense.action.DispenseActionCreator;
import za.co.opsmobile.coindispense.dispense.store.PaymentTransaction;
import za.co.opsmobile.coindispense.framework.logging.GatewayError;
import za.co.opsmobile.coindispense.framework.security.SecurityUtil;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public class RetrofitDispenseGateway implements DispenseGateway {
    private final DispenseService dispenseService;

    public RetrofitDispenseGateway(String restUrl, final SecurityUtil securityUtil) {



        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(securityUtil.getInterceptor());

        Retrofit retrofit = new Retrofit.Builder()
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .baseUrl(restUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dispenseService = retrofit.create(DispenseService.class);
    }


    @Override
    public void calculateChange(PaymentTransaction transaction, Float cost, final DispenseActionCreator dispenseActionCreator) {
        CalculateChangeRequest request = new CalculateChangeRequest();
        request.setCost(cost);
        request.setPayment(transaction.getValue());

        Call<ChangeResult> changeResultCall = dispenseService.calculateChange(request);
        changeResultCall.enqueue(new Callback<ChangeResult>() {
            @Override
            public void onResponse(Response<ChangeResult> response) {
                dispenseActionCreator.onChangeCalculated(response.body().getPayments());
            }

            @Override
            public void onFailure(Throwable t) {
                dispenseActionCreator.sendError(new DispenseGatewayError(t));
            }
        });
    }


    private class DispenseGatewayError extends GatewayError {
        public DispenseGatewayError(String detailMessage) {
            super(detailMessage);
        }

        public DispenseGatewayError(Throwable cause) {
            super(cause);
        }

    }
}
