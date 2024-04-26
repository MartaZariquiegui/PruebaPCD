/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoparchis;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author martazariquiegui
 */
public class Tablero {
    //en esta clase guardamos el estado de las casillas, en cuales hay barreras y la posicion actual de las fichas
    
    private int[] casillas = new int[68];
    private HashMap<Color, Integer> posiciones = new HashMap<>();
    private int[] seguros = {4,11,16,21,28,33,38,45,50,55,62,67};
    private ArrayList<Integer> barreras = new ArrayList<>();
    
    public Tablero() {
        for(int i=0; i<68; i++){
            casillas[i] = 0;
        }
        for (Color color : Color.values()){
            posiciones.put(color, null);
        }
    }
    
    public boolean esSeguro(int casilla){
        boolean seguro = false;
        for (int i=0; i<seguros.length; i++){
            if (seguros[i]==casilla){
                seguro = true;
            }
        }
        return seguro;
    }

    public void ocuparCasilla(int casilla, Color color){
        if (casillas[casilla]==0){
            casillas[casilla]=1;
            posiciones.put(color, casilla);
        }else if ((casillas[casilla]==1) && (esSeguro(casilla))){
            casillas[casilla]=2;
            barreras.add(casilla);
            posiciones.put(color, casilla);
        }else if((casillas[casilla]==1) && (!esSeguro(casilla))){
            //comerFicha
            posiciones.put(color, casilla);
        }else{
            System.out.println("No se puede ocupar esta casilla");
        }
    }
    
    public int getEstadoCasilla(int indice) {
        return casillas[indice];
    }
    
    public boolean hayBarrera(int casilla){
        return barreras.contains(casilla);
    }
    
    public void quitarFichaDeCasilla(int casilla){
        if (casillas[casilla]==1){
            casillas[casilla]=0;
        }else if(casillas[casilla]==2){
            casillas[casilla]=1;
            barreras.remove(casilla);
        }
    }
    
}
