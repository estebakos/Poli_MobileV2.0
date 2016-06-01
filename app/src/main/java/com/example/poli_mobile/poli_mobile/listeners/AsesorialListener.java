package com.example.poli_mobile.poli_mobile.listeners;

import com.example.poli_mobile.poli_mobile_entidades.ComprobantePago;
import com.example.poli_mobile.poli_mobile_entidades.asesoriaAcademica;

import java.util.List;

public interface AsesorialListener {
	public void Asesoria(List<asesoriaAcademica> asesoriaAcademicas);
	public void AsesoriaFail();
}
