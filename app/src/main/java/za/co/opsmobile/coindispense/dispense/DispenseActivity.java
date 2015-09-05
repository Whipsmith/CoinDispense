package za.co.opsmobile.coindispense.dispense;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import za.co.opsmobile.coindispense.ModelFactory;
import za.co.opsmobile.coindispense.R;
import za.co.opsmobile.coindispense.dispense.action.DispenseActionCreator;
import za.co.opsmobile.coindispense.dispense.store.DispenseModel;
import za.co.opsmobile.coindispense.framework.dipatcher.Dispatcher;

public class DispenseActivity extends AppCompatActivity {

    private DispenseModel model;
    private DispenseActionCreator actionCreator;
    private Dispatcher dispatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispense);
        initDependencies();
    }

    private void initDependencies() {
        dispatcher = Dispatcher.getDefault();
        actionCreator = new DispenseActionCreator(dispatcher);
        model = ModelFactory.get(dispatcher).getDispenseModel();
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
