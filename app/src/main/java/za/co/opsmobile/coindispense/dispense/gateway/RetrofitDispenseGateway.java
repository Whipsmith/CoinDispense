package za.co.opsmobile.coindispense.dispense.gateway;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import za.co.opsmobile.coindispense.CalculateChangeRequest;
import za.co.opsmobile.coindispense.dispense.action.DispenseActionCreator;
import za.co.opsmobile.coindispense.dispense.store.Denomination;
import za.co.opsmobile.coindispense.dispense.store.Payment;
import za.co.opsmobile.coindispense.dispense.store.PaymentTransaction;
import za.co.opsmobile.coindispense.framework.logging.GatewayError;

/**
 * Created by Daniel Oosthuizen on 2015/09/05.
 */
public class RetrofitDispenseGateway implements DispenseGateway {
    private final DispenseService dispenseService;

    public RetrofitDispenseGateway(String restUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .baseUrl(restUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dispenseService = retrofit.create(DispenseService.class);
    }


    @Override
    public void calculateChange(PaymentTransaction transaction, final DispenseActionCreator dispenseActionCreator) {


        CalculateChangeRequest request = new CalculateChangeRequest();
        request.setCost(transaction.getValue());
        dispenseService.calculateChange(request, new Callback<PaymentTransaction>() {
            @Override
            public void onResponse(Response<PaymentTransaction> response) {
                dispenseActionCreator.onChangeCalculated(response.body());
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
