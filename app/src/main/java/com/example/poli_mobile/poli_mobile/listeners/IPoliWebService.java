package com.example.poli_mobile.poli_mobile.listeners;

import com.example.poli_mobile.poli_mobile_entidades.CitaMedica;

import java.util.List;

public interface IPoliWebService {
	void onInternetFail();
	void onHttpError(Object resp, String method);
	void onUnexpectedError();
	void onObtenerCitas(List<CitaMedica> lCita);
	void onGetDevicesResponseFail(int Error);
	void onGetChannelsResponseFail(int Error);
	void onUnauthorized();
	void onAuthenticationFail();
}
