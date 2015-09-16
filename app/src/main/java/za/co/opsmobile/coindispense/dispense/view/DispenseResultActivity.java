package za.co.opsmobile.coindispense.dispense.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;
import za.co.opsmobile.coindispense.R;
import za.co.opsmobile.coindispense.dispense.store.Payment;
import za.co.opsmobile.coindispense.dispense.store.PaymentTransaction;

/**
 * Created by Daniel Oosthuizen on 2015/09/07.
 */
public class DispenseResultActivity extends AppCompatActivity {
    public static final String CHANGE_EXTRA_KEY = "exchange_extra_key";
    @Bind(R.id.payments)
    TextView payments;
    @Bind(R.id.total)
    TextView total;

    DecimalFormat randFormat;
    private DecimalFormat centFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispense_result);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        PaymentTransaction change = intent.getParcelableExtra(CHANGE_EXTRA_KEY);
        if (change == null) {
            supportFinishAfterTransition();
        }
        randFormat = new DecimalFormat("R ###");
        centFormat = new DecimalFormat("#.##c");
        centFormat.setMaximumFractionDigits(2);
        centFormat.setMinimumFractionDigits(2);
        centFormat.setMaximumIntegerDigits(0);
        setChange(change);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        PaymentTransaction change = intent.getParcelableExtra(CHANGE_EXTRA_KEY);
        if (change == null) {
            supportFinishAfterTransition();
        }
        setChange(change);
    }

    private void setChange(PaymentTransaction change) {
        setPayments(change.getPayments());
        setTotal(change.getValue());
    }

    private void setPayments(ArrayList<Payment> paymentList) {
        StringBuilder sb = new StringBuilder();
        Collections.sort(paymentList, new Payment.ReverseComparator());

        for (Payment payment : paymentList) {
            Float denomination = payment.getDenomination();
            String denominationString = getDecimalFormat(denomination).format(denomination);
            String paymentString = String.format("%d x %s", payment.getCount(), denominationString);
            sb.append(paymentString)
                    .append(System.lineSeparator());
        }

        payments.setText(sb.toString());
    }

    private DecimalFormat getDecimalFormat(float denomination) {
        if (denomination >= 1) {
            return randFormat;
        } else {
            return centFormat;
        }
    }

    private void setTotal(float value) {
        total.setText(getDecimalFormat(value).format(value));
    }

}
