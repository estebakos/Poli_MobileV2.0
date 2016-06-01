package com.example.poli_mobile.poli_mobile.listeners;

import com.example.poli_mobile.poli_mobile_entidades.AcercaDe;
import com.example.poli_mobile.poli_mobile_entidades.ProgramacionParcial;

import java.util.List;

public interface AcercaDeListener {
	public void AcercaDeListo(List<AcercaDe> lAcercaDe);
	public void AcercaDeFallido();
}
