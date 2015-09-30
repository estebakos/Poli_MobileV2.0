package com.example.poli_mobile.poli_mobile.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.poli_mobile.R;
import com.example.poli_mobile.poli_mobile.utilidades.PoliPreferences;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity {

    private static final long SPLASH_SCREEN_DELAY = 3000;
    private PoliPreferences preferences;
    private String ActivityToOpen;
    private Timer timer;
    private TimerTask task;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (Build.VERSION.SDK_INT >= 19) {
            //si es mayor o igual a API 19 kitkat ocultamos las barras UI del sistema
            getWindow().getDecorView().setSystemUiVisibility(
                    256 //SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | 512 //SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | 1024 //SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | 2 //SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                            | 4 //SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                            | 4096 //SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        } else {
            getWindow().getDecorView().setSystemUiVisibility(
                    256 //SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | 512 //SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | 1024 //SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | 2 //SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                            | 4 //SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
            );
        }


        preferences = new PoliPreferences(this);
        token = preferences.getToken();

        task = new TimerTask() {
            @Override
            public void run() {

                if(ActivityToOpen.equals("Login"))
                {
                    Intent mainIntent = new Intent().setClass(
                            SplashActivity.this, LoginActivity.class);
                    startActivity(mainIntent);
                }
                else
                {
                    Intent mainIntent = new Intent().setClass(
                            SplashActivity.this, com.example.poli_mobile.poli_mobile.ui.Menu.class);
                    startActivity(mainIntent);
                }

                finish();
            }
        };

        // Simulate a long loading process on application startup.
        timer = new Timer();
        if(!preferences.isRememberPassword() || token == null || token.equals(""))
        {
            ActivityToOpen = "Login";
            timer.schedule(task, SPLASH_SCREEN_DELAY);
        }
        else
        {
            ActivityToOpen = "Main";
            timer.schedule(task, SPLASH_SCREEN_DELAY);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
