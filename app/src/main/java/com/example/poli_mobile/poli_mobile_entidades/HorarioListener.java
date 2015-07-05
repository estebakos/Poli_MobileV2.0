package com.example.poli_mobile.poli_mobile_entidades;

import java.util.List;

import com.example.poli_mobile_entidades.HorarioSemestreActual;

public interface HorarioListener {
	public void HorarioListo(List<HorarioSemestreActual> lHorario);
	public void HorarioFallido();
}
