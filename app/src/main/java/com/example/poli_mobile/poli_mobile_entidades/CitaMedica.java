/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.poli_mobile.poli_mobile_entidades;

/**
 *
 * @author Casa
 */
public class CitaMedica {
    
    private String Cod_cita_medica;
    private String Nom_medico;
    private String Ape_medico;
    private String Ubi_consultorio;
    private String Hora;
    private String Dia;
    private String Estado;
    private String Descripcion ;
    public String getCod_cita_medica() {
    return Cod_cita_medica;
    }

    public void setCod_cita_medica(String Cod_cita_medica) {
        this.Cod_cita_medica = Cod_cita_medica;
    }

    public String getNom_medico() {
        return Nom_medico;
    }

    public void setNom_medico(String Nom_medico) {
        this.Nom_medico = Nom_medico;
    }

    public String getApe_medico() {
        return Ape_medico;
    }

    public void setApe_medico(String Ape_medico) {
        this.Ape_medico = Ape_medico;
    }

    public String getUbi_consultorio() {
        return Ubi_consultorio;
    }

    public void setUbi_consultorio(String Ubi_consultorio) {
        this.Ubi_consultorio = Ubi_consultorio;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }

    public String getDia() {
        return Dia;
    }

    public void setDia(String Dia) {
        this.Dia = Dia;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
}
