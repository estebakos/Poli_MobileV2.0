package com.example.poli_mobile.poli_mobile.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poli_mobile.R;
import com.example.poli_mobile.poli_mobile.network.NetworkManager;
import com.example.poli_mobile.poli_mobile.utilidades.AppContext;
import com.example.poli_mobile.poli_mobile.utilidades.PoliPreferences;
import com.example.poli_mobile.poli_mobile_entidades.CitaMedica;

import java.util.List;
/**
 * Created by TEKUSPC4 on 22/09/15.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener, NetworkUiListener {

    private EditText etDomain, etUser, etPassword;
    private Button btnSignIn;
    private CheckBox chkRememberPassword;
    private NetworkManager nManager;
    private PoliPreferences _axPrefs;
    private ProgressDialog barProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppContext.setContext(this);
        setContentView(R.layout.activity_login);

        _axPrefs = new PoliPreferences(this);

        nManager = NetworkManager.getNManagerInstance();
        nManager.addNetworkUiListener(this);

        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(this);

        etDomain = (EditText) findViewById(R.id.etLoginDate);
        etUser = (EditText) findViewById(R.id.etLoginUser);
        etPassword = (EditText) findViewById(R.id.etLoginPassword);
        etPassword.setOnEditorActionListener(new DoneOnEditorActionListener());

        chkRememberPassword = (CheckBox) findViewById(R.id.chkRememberPassword);
    }

    private void Authenticate() {
        restoreDefaultHintEditTextsColor();
        if (validateFields()) {
            launchDialog();
            nManager.Authenticate(etUser.getText().toString(), etPassword.getText().toString());
        } else {
            showTooltip(getString(R.string.str_complete_fields));
            changeHintEditTextsColor();
        }
    }

    private void changeHintEditTextsColor() {

        if (etUser.getText().toString().equals("")) {
            etUser.setHintTextColor(Color.RED);
        }
        if (etPassword.getText().toString().equals("")) {
            etPassword.setHintTextColor(Color.RED);
        }
    }

    private void restoreDefaultHintEditTextsColor() {
        etDomain.setHintTextColor(Color.parseColor("#808080"));
        etUser.setHintTextColor(Color.parseColor("#808080"));
        etPassword.setHintTextColor(Color.parseColor("#808080"));
    }

    private boolean validateFields() {
        if (etUser.getText().toString().equals("") || etPassword.getText().toString().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    private void showTooltip(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignIn:
                Authenticate();
                break;
            default:
                break;
        }
    }

    @Override
    public void onAuthenticate() {
        stopDialog();
        _axPrefs.storageRememberPassword(chkRememberPassword.isChecked());
        Intent mainIntent = new Intent().setClass(
                LoginActivity.this, MenuEstudiante  .class);
        startActivity(mainIntent);
        finish();
    }

    @Override
    public void onAuthenticationFail() {
        stopDialog();
        showTooltip(getString(R.string.str_incorrect_credentials));
    }


    @Override
    public void onGetDeviceListFail() {

    }

    @Override
    public void onObtenerCitas(List<CitaMedica> lCitas) {

    }

    private void launchDialog() {
        barProgressDialog = ProgressDialog.show(this, getString(R.string.str_please_wait), getString(R.string.str_signing_in), true);
        barProgressDialog.setCancelable(false);
        barProgressDialog.setCanceledOnTouchOutside(false);

    }

    private void stopDialog() {
        if(barProgressDialog != null) {
            barProgressDialog.hide();
            barProgressDialog.cancel();
        }
    }


    class DoneOnEditorActionListener implements TextView.OnEditorActionListener {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == 0) {
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                Authenticate();
                return true;
            }
            return false;
        }
    }
}
