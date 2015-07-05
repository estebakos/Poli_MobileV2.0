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
public class Programa {
    
    private String Cod_programa;
    private String Nom_programa;
    private String Duracion;
    private String Presentacion;
    private String Mision;
    private String Vision;
    private String Perfil_Profesional;
    private String Perfil_Ocupacional;
    private String Metodologia;
    private String Contacto;
    private String Competencias;
    private List <PlanEstudio> PlanEstudio;

    public String getCod_programa() {
        return Cod_programa;
    }

    public void setCod_programa(String Cod_programa) {
        this.Cod_programa = Cod_programa;
    }

    public String getNom_programa() {
        return Nom_programa;
    }

    public void setNom_programa(String Nom_programa) {
        this.Nom_programa = Nom_programa;
    }

    public String getDuracion() {
        return Duracion;
    }

    public void setDuracion(String Duracion) {
        this.Duracion = Duracion;
    }

    public String getPresentacion() {
        return Presentacion;
    }

    public void setPresentacion(String Presentacion) {
        this.Presentacion = Presentacion;
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

    public String getPerfil_Profesional() {
        return Perfil_Profesional;
    }

    public void setPerfil_Profesional(String Perfil_Profesional) {
        this.Perfil_Profesional = Perfil_Profesional;
    }

    public String getPerfil_Ocupacional() {
        return Perfil_Ocupacional;
    }

    public void setPerfil_Ocupacional(String Perfil_Ocupacional) {
        this.Perfil_Ocupacional = Perfil_Ocupacional;
    }

    public String getMetodologia() {
        return Metodologia;
    }

    public void setMetodologia(String Metodologia) {
        this.Metodologia = Metodologia;
    }

    public String getContacto() {
        return Contacto;
    }

    public void setContacto(String Contacto) {
        this.Contacto = Contacto;
    }

    public String getCompetencias() {
        return Competencias;
    }

    public void setCompetencias(String Competencias) {
        this.Competencias = Competencias;
    }

    public List <PlanEstudio> getPlanEstudio() {
        return PlanEstudio;
    }

    public void setPlanEstudio(List <PlanEstudio> PlanEstudio) {
        this.PlanEstudio = PlanEstudio;
    }
            
}
