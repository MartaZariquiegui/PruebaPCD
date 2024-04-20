/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoparchis;

/**
 *
 * @author martazariquiegui
 */
public class Ficha {

    private Tablero tablero;
    private int casilla;
    private boolean esComible;

    public Ficha(Tablero tablero, int casilla, boolean esComible) {
        this.casilla = casilla;
        this.esComible = esComible;
    }

    public int getCasilla() {
        return casilla;
    }

    public void setCasilla(int casilla) {
        this.casilla = casilla;
    }

    public boolean isEsComible() {
        return esComible;
    }

    public void setEsComible(boolean esComible) {
        this.esComible = esComible;
    }

    public void sacarFicha(int numJugador) {
        switch (numJugador) {
            case 1 ->
                tablero.ocuparCasilla(4);
            case 2 ->
                tablero.ocuparCasilla(21);
            case 3 ->
                tablero.ocuparCasilla(38);
            default ->
                tablero.ocuparCasilla(55);
        }
    }

    public void mover(int casillasMover) {

    }

}
