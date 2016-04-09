package com.example.poli_mobile.poli_mobile.listeners;

import com.example.poli_mobile.poli_mobile_entidades.CalendarioAcademico;
import com.example.poli_mobile.poli_mobile_entidades.Facultad;

import java.util.List;

public interface CalendarioListener {
	public void onCalendario(List<CalendarioAcademico> calendario);
	public void onCalendarioError();
}
