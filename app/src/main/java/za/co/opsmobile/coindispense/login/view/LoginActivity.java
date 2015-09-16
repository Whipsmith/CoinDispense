package za.co.opsmobile.coindispense.login.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import za.co.opsmobile.coindispense.R;
import za.co.opsmobile.coindispense.dispense.view.DispenseActivity;
import za.co.opsmobile.coindispense.factory.ActionCreatorFactory;
import za.co.opsmobile.coindispense.framework.dipatcher.Dispatcher;
import za.co.opsmobile.coindispense.login.action.LoginActionCreator;
import za.co.opsmobile.coindispense.login.store.LoginStoreModelChangedEvent;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.login_button)
    Button loginButton;
    @Bind(R.id.usernameInput)
    TextInputLayout usernameInput;
    @Bind(R.id.passwordInput)
    TextInputLayout passwordInput;
    private Dispatcher dispatcher;
    private LoginActionCreator actionCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initDependencies();
    }

    private void initDependencies() {
        dispatcher = Dispatcher.getDefault();
        actionCreator = ActionCreatorFactory.getLoginActionCreator(dispatcher, getApplicationContext());
    }

    @OnClick(R.id.login_button)
    public void login() {
        String username = this.usernameInput.getEditText().getText().toString();
        String password = this.passwordInput.getEditText().getText().toString();
        if (valid(username, password)) {
            hideKeyboard();
            actionCreator.login(username, password);
        }
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private boolean valid(String username, String password) {
        boolean valid = true;
        if (username == null || username.length() == 0) {
            valid = false;
            usernameInput.setError("Required");
        } else {
            usernameInput.setError(null);
        }

        if (password == null || password.length() == 0) {
            valid = false;
            passwordInput.setError("Required");
        } else {
            passwordInput.setError(null);
        }
        return valid;
    }

    public void onEventMainThread(LoginStoreModelChangedEvent loginStoreModelChangedEvent) {
        Intent dispenseIntent = new Intent(this, DispenseActivity.class);
        startActivity(dispenseIntent);
    }


    @Override
    protected void onStop() {
        dispatcher.unRegister(this);
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        dispatcher.register(this);
    }
}
