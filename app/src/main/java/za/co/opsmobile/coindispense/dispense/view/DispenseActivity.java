package za.co.opsmobile.coindispense.dispense.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

import butterknife.Bind;
import butterknife.ButterKnife;
import za.co.opsmobile.coindispense.R;
import za.co.opsmobile.coindispense.dispense.action.DispenseActionCreator;
import za.co.opsmobile.coindispense.dispense.store.Denomination;
import za.co.opsmobile.coindispense.dispense.store.DispenseModel;
import za.co.opsmobile.coindispense.dispense.store.DispenseModelChangedEvent;
import za.co.opsmobile.coindispense.dispense.store.PaymentTransaction;
import za.co.opsmobile.coindispense.dispense.view.adapter.PaymentOptionsAdapter;
import za.co.opsmobile.coindispense.factory.ActionCreatorFactory;
import za.co.opsmobile.coindispense.factory.ModelFactory;
import za.co.opsmobile.coindispense.framework.dipatcher.Dispatcher;
import za.co.opsmobile.coindispense.framework.logging.CoinDispenseError;

public class DispenseActivity extends AppCompatActivity {


    @Bind(R.id.priceView)
    TextView priceView;
    @Bind(R.id.paymentTotalView)
    TextView paymentTotalView;
    @Bind(R.id.paymentOptions)
    RecyclerView paymentOptions;
    @Bind(R.id.snackbarPosition)
    CoordinatorLayout snackbarPosition;


    private DispenseModel model;
    private DispenseActionCreator actionCreator;
    private Dispatcher dispatcher;
    private PaymentOptionsAdapter paymentOptionsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispense);
        ButterKnife.bind(this);
        initDependencies();
        paymentOptionsAdapter = new PaymentOptionsAdapter(new ArrayList<Denomination>());
        paymentOptions.setAdapter(paymentOptionsAdapter);
    }

    private void initDependencies() {
        dispatcher = Dispatcher.getDefault();
        actionCreator = ActionCreatorFactory.getDispenseActionCreator(dispatcher);
        model = ModelFactory.get(dispatcher).getDispenseModel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setScreenValues();
    }

    private void setScreenValues() {
        PaymentTransaction change = model.getChange();
        if (change != null) {
            navigateToResultScreen(change);
            return;
        }

        ArrayList<Denomination> validDenominations = model.getValidDenominations();
        if (validDenominations == null) {
            actionCreator.initialiseValidDenominations();
            return;
        } else {
            setValidDenominations(validDenominations);
        }


        Float cost = model.getCost();
        if (cost == null) {
            actionCreator.fetchCost();
            return;
        } else {
            setCost(cost);
        }

        PaymentTransaction payments = model.getPayments();
        setPayments(payments);
    }

    private void navigateToResultScreen(PaymentTransaction change) {
        Intent resultIntent = new Intent(this, DispenseResultActivity.class);
        resultIntent.putExtra(DispenseResultActivity.CHANGE_EXTRA_KEY, change);
        startActivity(resultIntent);
    }

    private void setValidDenominations(ArrayList<Denomination> validDenominations) {
        paymentOptionsAdapter.setPaymentOptions(validDenominations);
    }

    private void setCost(Float cost) {

    }

    private void setPayments(PaymentTransaction payments) {

    }

    public void onEventMainThread(DispenseModelChangedEvent dispenseModelChangedEvent) {
        setScreenValues();
    }

    public void onEventMainThread(CoinDispenseError error) {
        setScreenValues();

        String userMessage = error.getUserMessage();
        if (userMessage != null) {
            displayError(userMessage);
        }
    }

    private void displayError(String userMessage) {
        Snackbar.make(snackbarPosition, userMessage, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        dispatcher.unRegister(this);
        dispatcher.unRegister(model);
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        dispatcher.register(this);
        dispatcher.register(model);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dispense, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
