package com.example.poli_mobile.poli_mobile.listeners;

import com.example.poli_mobile.poli_mobile_entidades.AutoevalacionDocente;
import com.example.poli_mobile.poli_mobile_entidades.ProgramacionParcial;

import java.util.List;

public interface AutoEvalListener {
	public void AutoEvalLista(List<AutoevalacionDocente> autoevalacionDocentes);
	public void AutoEvalProblema();
}
