package com.example.poli_mobile.poli_mobile.ui;

import com.example.poli_mobile.poli_mobile_entidades.CitaMedica;

import java.util.List;


public interface NetworkUiListener {
	void onAuthenticate();
	void onAuthenticationFail();
	void onGetDeviceListFail();
	void onObtenerCitas(List<CitaMedica> lCitas);
}
