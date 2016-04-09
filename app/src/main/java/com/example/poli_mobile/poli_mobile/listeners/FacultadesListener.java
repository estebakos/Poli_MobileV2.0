package com.example.poli_mobile.poli_mobile.listeners;

import com.example.poli_mobile.poli_mobile_entidades.CitaMedica;
import com.example.poli_mobile.poli_mobile_entidades.Facultad;

import java.util.List;

public interface FacultadesListener {
	public void onFacultades(List<Facultad> facultades);
	public void onFacultadesError();
}
