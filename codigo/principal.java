/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquinaexpendedora;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author Usuario
 */
public class principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Se crea la maquina
        MaquinaExpendedora m1 = new MaquinaExpendedora("definicion.txt");
        
        //Se lee el input de entrada
        InputStreamReader viaEntrada = new InputStreamReader(System.in);
        BufferedReader leer = new BufferedReader(viaEntrada);
        System.out.println("Escribe la cadena.");
        String entrada = null;
        try {
            entrada = leer.readLine();
        } catch(IOException a) {
            
        }
        
        //Se imprimen los estados de partida
        System.out.print("ESTADOS DE PARTIDA (inicial + cadena vacia): {");
        Iterator est1 = m1.getEstadosActuales().iterator();
        while(est1.hasNext()) {
            System.out.print(est1.next());
            if(est1.hasNext()) {
                System.out.print(" + ");
            }
        }
        System.out.print("}\n");
        
        //Se divide el input en caracteres y se le pasan a la maquina
        for (int i = 0; i<entrada.length(); i++) {
            ArrayList<Estado> estados = m1.procesarInput(entrada.charAt(i));
            
            System.out.print("ENTRA " + entrada.charAt(i) + ": {");
            Iterator est = estados.iterator();
            while(est.hasNext()) {
                System.out.print(est.next());
                if(est.hasNext()) {
                    System.out.print(" + ");
                }
            }
            System.out.print("}\n");
        }
    }
    
}
