/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquinaexpendedora;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Estado {
    private String nombre;
    private ArrayList<Transicion> transiciones;
    private boolean esfinal;

    public Estado(String nombre) {
        this.nombre = nombre;
        transiciones = new ArrayList<>();
        this.esfinal = false;
    }
    
    public boolean isEsfinal() {
        return esfinal;
    }

    public void setEsfinal(boolean esfinal) {
        this.esfinal = esfinal;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Transicion> getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(ArrayList<Transicion> transiciones) {
        this.transiciones = transiciones;
    }
    
    @Override
    public String toString() {
        String resultado;
        if (esfinal) {
            resultado = nombre + "(Final)";
        }
        else {
            resultado = nombre;
        }
        return resultado;
    }
}
