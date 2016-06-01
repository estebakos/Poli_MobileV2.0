package com.example.poli_mobile.poli_mobile.listeners;

import com.example.poli_mobile.poli_mobile_entidades.AutoevalacionDocente;
import com.example.poli_mobile.poli_mobile_entidades.ComprobantePago;

import java.util.List;

public interface ComprobantelListener {
	public void Comprobante(List<ComprobantePago> comprobantePagos);
	public void ComprobanteFail();
}
