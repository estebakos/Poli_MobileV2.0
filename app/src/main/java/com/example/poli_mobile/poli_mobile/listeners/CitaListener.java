package com.example.poli_mobile.poli_mobile.listeners;

import java.util.List;

import com.example.poli_mobile.poli_mobile_entidades.CitaMedica;

public interface CitaListener {
	public void CitaLista(List<CitaMedica> lCita);
	public void CitaFallida();
}
