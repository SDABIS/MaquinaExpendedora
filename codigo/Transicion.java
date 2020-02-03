/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquinaexpendedora;

/**
 *
 * @author Usuario
 */
public class Transicion {
    private char entrada;
    private Estado estadoFinal;
    private boolean CadenaVacia;

    public Transicion(char entrada, Estado estadoFinal, boolean esCadenaVacia) {
        this.entrada = entrada;
        this.estadoFinal = estadoFinal;
        this.CadenaVacia = esCadenaVacia;
    }

    public boolean esCadenaVacia() {
        return CadenaVacia;
    }

    public void setCadenaVacia(boolean CadenaVacia) {
        this.CadenaVacia = CadenaVacia;
    }
    
    public char getEntrada() {
        return entrada;
    }

    public void setEntrada(char entrada) {
        this.entrada = entrada;
    }

    public Estado getEstadoFinal() {
        return estadoFinal;
    }

    public void setEstadoFinal(Estado estadoFinal) {
        this.estadoFinal = estadoFinal;
    }
    
    
}
