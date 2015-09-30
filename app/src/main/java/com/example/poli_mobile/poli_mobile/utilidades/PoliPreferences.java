package com.example.poli_mobile.poli_mobile.utilidades;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PoliPreferences {

    /***
     * Todas las preferencias asociadas a un PreferenceFragment son alamacenadas como STRING *
     * CON EXCEPCION de los Valores Booleanos (No se porque PUT#%"#&)
     ***/

    public static final String PREF_TOKEN = "pref_arkbox_token";
    public static final String PREF_REMEMBER_PASSWORD = "pref_arkbox_remember_password";


    /**********************************************************************************************/

    /* Private Preferences */
    public static final String PREF_PRIVATE = "pref_private";


    private Context context;

    public PoliPreferences(Context context) {
        this.context = context;
    }

    public String get(String sConfig,
                      String defaultValue) {
        return get(context, sConfig, defaultValue);
    }

    public void clear() {
        clear(context);
    }

    public static void clear(Context context) {
        SharedPreferences sharePreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        sharePreferences.edit().clear().commit();

        SharedPreferences privateSharePreferences = context.getSharedPreferences(PREF_PRIVATE, Activity.MODE_PRIVATE);
        privateSharePreferences.edit().clear().commit();
    }

    private static String get(Context context, String sConfig,
                              String defaultValue) {
        SharedPreferences sharePreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sharePreferences.getString(sConfig, defaultValue);
    }

    private static String getPrivate(Context context, String sConfig, String defaultValue) {
        SharedPreferences sharePreferences = context.getSharedPreferences(PREF_PRIVATE, Activity.MODE_PRIVATE);
        return sharePreferences.getString(sConfig, defaultValue);
    }

    private Boolean get(String sConfig,
                        Boolean defaultValue) {
        return get(context, sConfig, defaultValue);
    }

    private static Boolean get(Context context, String sConfig,
                               Boolean defaultValue) {
        SharedPreferences sharePreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sharePreferences.getBoolean(sConfig, defaultValue);
    }

    private static Boolean getPrivate(Context context, String sConfig,
                                      Boolean defaultValue) {
        SharedPreferences sharePreferences = context.getSharedPreferences(PREF_PRIVATE, Activity.MODE_PRIVATE);
        return sharePreferences.getBoolean(sConfig, defaultValue);
    }

    private Integer get(String sConfig,
                        Integer defaultValue) {
        return get(context, sConfig, defaultValue);
    }

    private static Integer get(Context context, String sConfig,
                               Integer defaultValue) {
        SharedPreferences sharePreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sharePreferences.getInt(sConfig, defaultValue);
    }

    private static Integer getPrivate(Context context, String sConfig,
                                      Integer defaultValue) {
        SharedPreferences sharePreferences = context.getSharedPreferences(PREF_PRIVATE, Activity.MODE_PRIVATE);
        return sharePreferences.getInt(sConfig, defaultValue);
    }

    private void set(String sConfig, String value) {
        set(context, sConfig, value);
    }

    private static void set(Context context, String sConfig, String value) {
        SharedPreferences sharePreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor spEditor = sharePreferences.edit();
        spEditor.putString(sConfig, value);
        spEditor.commit();
    }

    private static void setPrivate(Context context, String sConfig, String value) {
        SharedPreferences sharePreferences = context.getSharedPreferences(PREF_PRIVATE, Activity.MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sharePreferences.edit();
        spEditor.putString(sConfig, value);
        spEditor.commit();
    }

    private void set(String sConfig, Boolean value) {
        set(context, sConfig, value);
    }

    private static void set(Context context, String sConfig, Boolean value) {
        SharedPreferences sharePreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor spEditor = sharePreferences.edit();
        spEditor.putBoolean(sConfig, value);
        spEditor.commit();
    }

    private static void setPrivate(Context context, String sConfig, Boolean value) {
        SharedPreferences sharePreferences = context.getSharedPreferences(PREF_PRIVATE, Activity.MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sharePreferences.edit();
        spEditor.putBoolean(sConfig, value);
        spEditor.commit();
    }

    private void set(String sConfig, Integer value) {
        set(context, sConfig, value);
    }

    private static void set(Context context, String sConfig, Integer value) {
        SharedPreferences sharePreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor spEditor = sharePreferences.edit();
        spEditor.putInt(sConfig, value);
        spEditor.commit();
    }

    private static void setPrivate(Context context, String sConfig, Integer value) {
        SharedPreferences sharePreferences = context.getSharedPreferences(PREF_PRIVATE, Activity.MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sharePreferences.edit();
        spEditor.putInt(sConfig, value);
        spEditor.commit();
    }

    /***
     * Todas las preferencias asociadas a un PreferenceFragment son alamacenadas como STRING
     ****/


    public void storageToken(String value) {

        if (value != null && !value.equals("")) {
            set(context, PREF_TOKEN, value);
        } else {
            set(context, PREF_TOKEN, "");
        }
    }

    public void storageRememberPassword(Boolean value) {
        set(context, PREF_REMEMBER_PASSWORD, value);
    }

    public String getToken() {
        return get(context, PREF_TOKEN, "");
    }

    public Boolean isRememberPassword() {
        return get(context, PREF_REMEMBER_PASSWORD, false);
    }


}
