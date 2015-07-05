package com.example.poli_mobile.poli_mobile_entidades;

import java.io.Serializable;
import java.util.Date;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

@SuppressWarnings("serial")
public class Noticia implements Serializable {

	private String titulo;
	private String contenido;
	private String resumen;
	private String enlace;
	private Drawable imagen;
	private Date fecha;

	public void setTitulo(String title) {
		this.titulo = title;
	}

	public void setContenido(String content) {
		this.contenido = content;
	}
	
	public void setResumen(String text) {
		this.resumen = text;
	}

	public void setEnlace(String url) {
		this.enlace = url;
	}

	public void setImage(Drawable image) {
		this.imagen = image;
	}

	public void setFecha(Date date) {
		this.fecha = date;
	}

	public String getTitulo() {
		return this.titulo;
	}
	
	public String getContenido() {
		return this.contenido;
	}

	public String getResumen() {
		return this.resumen;
	}

	public String getEnlace() {
		return this.enlace;
	}

	public Drawable getImagen() {
		return this.imagen;
	}

	public Date getFecha() {
		return this.fecha;
	}
}
