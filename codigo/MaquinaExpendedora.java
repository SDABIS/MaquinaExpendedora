/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquinaexpendedora;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class MaquinaExpendedora {
    private ArrayList<Estado> estados;
    private ArrayList<Estado> estadosFinales;
    private Estado estadoInicial;
    private ArrayList<Estado> estadosActuales;
    private ArrayList<Character> inputs;
    
    public MaquinaExpendedora(String archivo) {
        //Inicializar valores
        estadosActuales = new ArrayList<>();
        estados = new ArrayList<>();
        estadosFinales = new ArrayList<>();
        inputs = new ArrayList<>();
        leerArchivo(archivo);
        //Se configuran los estados iniciales
        estadosActuales.add(estadoInicial);
        //Identico al procesamiento de la cadena vacia, pero para los estados iniciales
        ArrayList<Estado> estadosCadenaVacia = new ArrayList<>();
        int cambios;
        do {
            cambios = 0;
            for (int i = 0; i<estadosActuales.size(); i++) {
                Estado est = estadosActuales.get(i);
                for (Transicion trans : est.getTransiciones()) {
                    if(trans.esCadenaVacia()) {
                        boolean repetido = false;
                        for(Estado estado : estadosActuales) {
                            if (estado.getNombre().equals(trans.getEstadoFinal().getNombre())) {
                                repetido = true;
                            }
                        }
                        if (!repetido) {
                            estadosActuales.add(trans.getEstadoFinal());
                            cambios = 1;
                            System.out.println(trans.getEstadoFinal());
                        }
                    }
                }
            }
        } while (cambios == 1);
    }
    
    public void leerArchivo(String nombreArchivo) {
        
        //Se abre el archivo
        File doc = new File(nombreArchivo); 
        Scanner archivo = null; 
        try {
            archivo = new Scanner(doc);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
        //Primera linea: leer numero de estados y nombre de los mismos
        String texto = archivo.nextLine();
        String[] partes = texto.split(" ");
        int numeroEstados = Integer.parseInt(partes[0].split("#")[1]);
        for (int i = 0; i < numeroEstados; i++) {
            estados.add(new Estado(partes[i + 1]));
        }
        estadoInicial = estados.get(0);
        
        //Segunda linea: leer estados finales
        texto = archivo.nextLine();
        partes = texto.split(" ");
        int numeroEstadosFinales = Integer.parseInt(partes[0].split("#")[1]);
        for (int i = 0; i < numeroEstadosFinales; i++) {
            for (int j = 0; j < numeroEstados; j++) {
                if(estados.get(j).getNombre().equals(partes[i+1])) {
                    estadosFinales.add(estados.get(j));
                    estados.get(j).setEsfinal(true);
                }
            }
        }
        
        //Tercera Linea: leer numero de inputs y el nombre de los mismos
        texto = archivo.nextLine();
        partes = texto.split(" ");
        int numeroInputs = Integer.parseInt(partes[0].split("#")[1]);
        for (int i = 0; i < numeroInputs; i++) {
            inputs.add(partes[i + 1].charAt(0));
        }
        
        //Cuarta linea: en todas las confguraciones es la misma
        texto = archivo.nextLine();
        if(!texto.equals("--TABLA DE TRANSICIONES--")) {
            System.out.println("La cuarta linea está mal.");
            System.exit(1);
        }
        
        //Resto de lineas: tabla de transiciones
        for (int i = 0; i<numeroEstados; i++) {
            texto = archivo.nextLine();
            partes = texto.split("#");
            //Separamos las transiciones segun su entrada
            for (int j = 0; j < inputs.size(); j++) {
                //Para la misma transicion, los distintos estados
                String[] estadoLlegada = partes[j].split(" ");
                for (int k = 0; k < estadoLlegada.length; k++) {
                    for (Estado est : estados) {
                        if(est.getNombre().equals(estadoLlegada[k])) {
                            estados.get(i).getTransiciones().add(new Transicion(inputs.get(j), est, false));
                        }
                    }
                }
            }
            //Para la cadena vacia: es la ultima columna
            String[] estadoLlegada = partes[inputs.size()].split(" ");
            
            for (int k = 0; k < estadoLlegada.length; k++) {
                for (Estado est : estados) {
                    if(est.getNombre().equals(estadoLlegada[k])) {
                        estados.get(i).getTransiciones().add(new Transicion(' ', est, true));
                    }
                }
            }
        }
    }

    public ArrayList<Estado> getEstadosActuales() {
        return estadosActuales;
    }

    public void setEstadosActuales(ArrayList<Estado> estadosActuales) {
        this.estadosActuales = estadosActuales;
    }
    
    public ArrayList<Estado> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<Estado> estados) {
        this.estados = estados;
    }

    public ArrayList<Estado> getEstadosFinales() {
        return estadosFinales;
    }

    public void setEstadosFinales(ArrayList<Estado> estadosFinales) {
        this.estadosFinales = estadosFinales;
    }

    public Estado getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(Estado estadoInicial) {
        this.estadoInicial = estadoInicial;
    }
    
    public void anadirEstado(Estado estadoNuevo) {
        this.estadosActuales.add(estadoNuevo);
    }
    
    public ArrayList<Estado> procesarInput(char input) {
        ArrayList<Estado> nuevosEstados = new ArrayList<>();

        //Para todos los estados actuales se comprueban todas sus transiciones
        for (Estado est : estadosActuales) {
            for (Transicion trans : est.getTransiciones()) {
                //Si alguna transiciona con el input que ha llegado, se realiza la transicion
                if(trans.getEntrada() == input) {
                    boolean repetido = false;
                    for(Estado estado : nuevosEstados) {
                        if (estado.getNombre().equals(trans.getEstadoFinal().getNombre())) {
                            repetido = true;
                        }
                    }
                    if (!repetido) {
                        nuevosEstados.add(trans.getEstadoFinal());
                    }
                }
            }
        }
        
        //Clausura: Lo mismo que antes pero para la cadena vacía
        int cambios;
        ArrayList<Estado> estadosCadenaVacia = new ArrayList<>();
        do  {
            cambios = 0;
            for (Estado est : nuevosEstados) {
                for (Transicion trans : est.getTransiciones()) {
                    //Si alguna transiciona con el input que ha llegado, se realiza la transicion
                    if(trans.esCadenaVacia()) {
                        boolean repetido = false;
                        for(Estado estado : estadosCadenaVacia) {
                            if (estado.getNombre().equals(trans.getEstadoFinal().getNombre())) {
                                repetido = true;
                            }
                        }
                        if (!repetido) {
                            estadosCadenaVacia.add(trans.getEstadoFinal());
                            cambios = 1;
                        }
                    }
                }
            }
        } while (cambios == 1);
        //Los estados alcanzados por la cadena vacia se añaden
        for (Estado est2 : estadosCadenaVacia) {
            boolean repetido1 = false;
            for (Estado est3 : nuevosEstados) {
                if (est2.getNombre().equals(est3.getNombre())) {
                    repetido1 = true;
                }
            }
            if(!repetido1) {
                nuevosEstados.add(est2);
            }
        }
        estadosActuales = nuevosEstados;
        return estadosActuales;
    }
}


