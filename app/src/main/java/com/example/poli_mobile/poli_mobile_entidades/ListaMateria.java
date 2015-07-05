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
public class ListaMateria {
    
    String nombreMateria;
    String sede;
    String horario;
    String aula; 
    List<Estudiantes> estudiante;

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public List<Estudiantes> getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(List<Estudiantes> estudiante) {
        this.estudiante = estudiante;
    }
}
