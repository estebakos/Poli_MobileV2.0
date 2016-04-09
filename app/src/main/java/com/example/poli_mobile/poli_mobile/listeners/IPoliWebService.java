package com.example.poli_mobile.poli_mobile.listeners;

import com.example.poli_mobile.poli_mobile_entidades.CalendarioAcademico;
import com.example.poli_mobile.poli_mobile_entidades.CitaMedica;
import com.example.poli_mobile.poli_mobile_entidades.Facultad;
import com.example.poli_mobile.poli_mobile_entidades.HorarioSemestreActual;
import com.example.poli_mobile.poli_mobile_entidades.ListaMateria;
import com.example.poli_mobile.poli_mobile_entidades.NotaMateria;
import com.example.poli_mobile.poli_mobile_entidades.ProgramacionParcial;

import java.util.List;

public interface IPoliWebService {
	void onInternetFail();
	void onHttpError(Object resp, String method);
	void onUnexpectedError();
	void onObtenerCitas(List<CitaMedica> lCita);
	void onAuthenticate(String token);
	void onGetDevicesResponseFail(int Error);
	void onGetChannelsResponseFail(int Error);
	void onUnauthorized();
	void onAuthenticationFail();
	void onFacultades(List<Facultad> lFacultades);
	void onCalendario(List<CalendarioAcademico> lCalendario);
	void HorarioListo(List<HorarioSemestreActual> webServiceEntity);
	void NotasLitas(List<NotaMateria> webServiceEntity);
	void ListaMateria(List<ListaMateria> webServiceEntity);
	void onParciales(List<ProgramacionParcial> lParcial);
}
