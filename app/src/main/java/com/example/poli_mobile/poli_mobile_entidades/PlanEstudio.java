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
public class PlanEstudio {
    
    private String Cod_Plan_Estudio;
    private String Nivel;
    private String Asignatura;
    private String PrerQ;
    private String CorQ;
    private String HTPT;
    private String HTPP;
    private String HTI;
    private String Creditos;

    public String getCod_Plan_Estudio() {
        return Cod_Plan_Estudio;
    }

    public void setCod_Plan_Estudio(String Cod_Plan_Estudio) {
        this.Cod_Plan_Estudio = Cod_Plan_Estudio;
    }

    public String getNivel() {
        return Nivel;
    }

    public void setNivel(String Nivel) {
        this.Nivel = Nivel;
    }

    public String getAsignatura() {
        return Asignatura;
    }

    public void setAsignatura(String Asignatura) {
        this.Asignatura = Asignatura;
    }

    public String getPrerQ() {
        return PrerQ;
    }

    public void setPrerQ(String PrerQ) {
        this.PrerQ = PrerQ;
    }

    public String getCorQ() {
        return CorQ;
    }

    public void setCorQ(String CorQ) {
        this.CorQ = CorQ;
    }

    public String getHTPT() {
        return HTPT;
    }

    public void setHTPT(String HTPT) {
        this.HTPT = HTPT;
    }

    public String getHTPP() {
        return HTPP;
    }

    public void setHTPP(String HTPP) {
        this.HTPP = HTPP;
    }

    public String getHTI() {
        return HTI;
    }

    public void setHTI(String HTI) {
        this.HTI = HTI;
    }

    public String getCreditos() {
        return Creditos;
    }

    public void setCreditos(String Creditos) {
        this.Creditos = Creditos;
    }
}
