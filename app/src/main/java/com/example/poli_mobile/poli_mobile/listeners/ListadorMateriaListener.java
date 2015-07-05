package com.example.poli_mobile.poli_mobile.listeners;

import java.util.List;

import com.example.poli_mobile.poli_mobile_entidades.ListaMateria;

public interface ListadorMateriaListener {
	public void LMateriaLista(List<ListaMateria> lMateria);
	public void LMateriaFallida();
}
