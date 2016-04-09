package com.example.poli_mobile.poli_mobile.utilidades;

import android.widget.RelativeLayout;

import com.example.poli_mobile.poli_mobile.listeners.LoginListenerTwo;
import com.example.poli_mobile.poli_mobile.listeners.loginTeacherListener;
import com.example.poli_mobile.poli_mobile_entidades.Facultad;
import com.example.poli_mobile.poli_mobile_entidades.Programa;

import java.util.ArrayList;
import java.util.List;

public class ApplicationSession {

	public static String SessionStudent = "";
	public static String SessionTeacher = "";
	public static LoginListenerTwo lListener;
	public static loginTeacherListener lTeacherListener;
	public static int position = 0;
	public static RelativeLayout rlayout = null;
	private static Facultad facultad = null;
	private static List<Programa> programas = new ArrayList<>();
	private static Programa programa;

	public static Programa getPrograma() {
		return programa;
	}

	public static void setPrograma(Programa programa) {
		ApplicationSession.programa = programa;
	}

	public static List<Programa> getProgramas() {
		return programas;
	}

	public static void setProgramas(List<Programa> programas) {
		ApplicationSession.programas = programas;
	}

	public static Facultad getFacultad() {
		return facultad;
	}

	public static void setFacultad(Facultad facultad) {
		ApplicationSession.facultad = facultad;
	}

	private static String baseURL = "http://52.5.56.177:8080/APP_Polimovil";
	private static String NAMESPACE = "http://Ws.APP_Polimovil.com.co/";
	private static String token;

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

	public static void setToken(String token) {
		ApplicationSession.token = token;
	}

	public static String getToken() {
		return token;
	}
}
