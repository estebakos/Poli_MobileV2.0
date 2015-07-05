/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.poli_mobile.poli_mobile_entidades;

import java.util.List;

/**
 *
 * @author Casa
 */
public class Facultad {
    
    private String Cod_facultad;
    private String Nom_facultad;
    private String Generalidades;
    private String Mision;
    private String Vision;
    private String Valores;
    private String Fundamentos;
    private String Principios;
    private List<Programa> Programas;

    public String getCod_facultad() {
        return Cod_facultad;
    }

    public void setCod_facultad(String Cod_facultad) {
        this.Cod_facultad = Cod_facultad;
    }

    public String getNom_facultad() {
        return Nom_facultad;
    }

    public void setNom_facultad(String Nom_facultad) {
        this.Nom_facultad = Nom_facultad;
    }

    public String getGeneralidades() {
        return Generalidades;
    }

    public void setGeneralidades(String Generalidades) {
        this.Generalidades = Generalidades;
    }

    public String getMision() {
        return Mision;
    }

    public void setMision(String Mision) {
        this.Mision = Mision;
    }

    public String getVision() {
        return Vision;
    }

    public void setVision(String Vision) {
        this.Vision = Vision;
    }

    public String getValores() {
        return Valores;
    }

    public void setValores(String Valores) {
        this.Valores = Valores;
    }

    public String getFundamentos() {
        return Fundamentos;
    }

    public void setFundamentos(String Fundamentos) {
        this.Fundamentos = Fundamentos;
    }

    public String getPrincipios() {
        return Principios;
    }

    public void setPrincipios(String Principios) {
        this.Principios = Principios;
    }

    public List<Programa> getProgramas() {
        return Programas;
    }

    public void setProgramas(List<Programa> Programas) {
        this.Programas = Programas;
    }
}
