package za.co.opsmobile.coindispense.dispense.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import za.co.opsmobile.coindispense.R;
import za.co.opsmobile.coindispense.dispense.action.DispenseActionCreator;
import za.co.opsmobile.coindispense.dispense.store.PaymentTransaction;
import za.co.opsmobile.coindispense.factory.ActionCreatorFactory;
import za.co.opsmobile.coindispense.framework.dipatcher.Dispatcher;

/**
 * Created by Daniel Oosthuizen on 2015/09/07.
 */
public class DispenseResultActivity extends AppCompatActivity {
    public static final String CHANGE_EXTRA_KEY = "exchange_extra_key";
    private PaymentTransaction change;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispense_result);

        Intent intent = getIntent();

        change = intent.getParcelableExtra(CHANGE_EXTRA_KEY);
        if (change == null) {
            supportFinishAfterTransition();
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        change =  intent.getParcelableExtra(CHANGE_EXTRA_KEY);
        if (change == null) {
            supportFinishAfterTransition();
        }
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}
