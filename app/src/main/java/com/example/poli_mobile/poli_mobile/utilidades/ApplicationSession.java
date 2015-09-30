package com.example.poli_mobile.poli_mobile.utilidades;

import android.widget.RelativeLayout;

import com.example.poli_mobile.poli_mobile.listeners.LoginListenerTwo;
import com.example.poli_mobile.poli_mobile.listeners.loginTeacherListener;

public class ApplicationSession {

	public static String SessionStudent = "";
	public static String SessionTeacher = "";
	public static LoginListenerTwo lListener;
	public static loginTeacherListener lTeacherListener;
	public static int position = 0;
	public static RelativeLayout rlayout = null;

	private static String baseURL = "http://10.100.187.142:8080/APP_Polimovil/";
	private static String NAMESPACE = "http://Ws.APP_Polimovil.com.co/";

	public static String getNAMESPACE() {
		return NAMESPACE;
	}

	public static void setNAMESPACE(String NAMESPACE) {
		ApplicationSession.NAMESPACE = NAMESPACE;
	}

	public static String getBaseURL() {
		return baseURL;
	}

	public static void setBaseURL(String baseURL) {
		ApplicationSession.baseURL = baseURL;
	}
}
