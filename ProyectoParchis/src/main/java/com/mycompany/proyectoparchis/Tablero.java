/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoparchis;

/**
 *
 * @author martazariquiegui
 */
public class Tablero {
    private int[] casillas;

    public Tablero() {
        for(int i=0; i<68; i++){
            casillas[i] = 0;
        }
    }
    
    public int[] getSeguros(Tablero tablero){
        int[] seguros = {5,12,17,22,29,34,39,46,51,56,63,68};
        return seguros;
    }
    
    public void sacarFicha(int numJugador){
        switch (numJugador) {
            case 1:
                casillas[4] = 1;
                break;
            case 2:
                casillas[21] = 1;
                break;
            case 3:
                casillas[38] = 1;
                break;
            default:
                casillas[55] = 1;
                break;
        }
    }
    
    public void ocuparCasilla(int casillasMover, Color color){
        
    }

}
