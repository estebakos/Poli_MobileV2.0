package com.example.poli_mobile.poli_mobile.listeners;

import com.example.poli_mobile.poli_mobile_entidades.CitaMedica;
import com.example.poli_mobile.poli_mobile_entidades.ProgramacionParcial;

import java.util.List;

public interface ParcialListener {
	public void ParcialListo(List<ProgramacionParcial> lCita);
	public void ParcialFallido();
}
