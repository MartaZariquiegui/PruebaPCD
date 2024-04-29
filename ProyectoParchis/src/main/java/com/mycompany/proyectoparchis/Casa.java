/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoparchis;

import java.util.ArrayList;

/**
 *
 * @author alumno
 */
public class Casa {
    private ArrayList<Ficha> fichasEnCasa;
    private int numFichas;
    private Color color;
    //private Tablero tablero;

    public Casa(int numFichas, Color color, Tablero tablero) {
        this.numFichas = numFichas;
        this.color = color;
        fichasEnCasa = new ArrayList<>();
        for(int i=1; i<=this.numFichas; i++){
            Ficha ficha = new Ficha(color, tablero);
            fichasEnCasa.add(ficha);
        } 
    }

    public int getNumFichas() {
        return numFichas;
    }

    public Color getColor() {
        return color;
    }
    
    public boolean casaVacia() {
        return fichasEnCasa.isEmpty();
    }
    
    public void eliminarDeCasa(Ficha ficha){
        if(!casaVacia()){
            fichasEnCasa.remove(ficha);
        }
    }
    
    
    public void meterFichaEnACasa(Ficha ficha) {
        if (fichasEnCasa.size() < numFichas){
            fichasEnCasa.add(ficha);
            ficha.setCasilla(0);
            ficha.setEnCasa(true);
            ficha.setComible(false);
        }
    }
    
    
}
