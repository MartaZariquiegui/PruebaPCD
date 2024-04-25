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

   // en esta clase actualizamos el estado de las fichas, si estan en casa o no, su posicion y si pueden ser comidas
    
    private Tablero tablero;
    
    private Color color;
    private int casilla;
    private boolean enCasa;
    private boolean comible;

    public Ficha(Color color, Tablero tablero) { // cada vez que creamos una ficha está en casa y no se puede comer
        this.tablero = tablero;
        this.color = color;
        this.enCasa = true;
        this.comible = false;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getCasilla() {
        return casilla;
    }

    public void setCasilla(int casilla) {
        this.casilla = casilla;
    }

    public boolean isEnCasa() {
        return enCasa;
    }

    public void setEnCasa(boolean enCasa) {
        this.enCasa = enCasa;
    }

    public boolean isComible() {
        return comible;
    }

    public void setComible(boolean comible) {
        this.comible = comible;
    }
    
    public void sacarFicha(int numJugador){
        enCasa = false;
        switch (numJugador) {
            case 1:
                casilla=4;
                tablero.ocuparCasilla(4,color);
                break;
            case 2:
                casilla=21;
                tablero.ocuparCasilla(21,color);
                break;
            case 3:
                casilla=38;
                tablero.ocuparCasilla(38,color);
                break;
            case 4:
                casilla=55;
                tablero.ocuparCasilla(55,color);
                break;
            default:
                System.out.println("Jugador no válido");
                break;
        }
    }
    
    public int nuevaPos(int posInicial, int posiciones){
        int posicionFinal = posInicial + posiciones;
        for (int i=1; i<=posiciones; i++){
            if (tablero.hayBarrera((posInicial+i)%68)){
                posicionFinal = (posInicial+i)-1;
                return posicionFinal%68;
            }
        }
        return posicionFinal%68;
    }
    
    public void moverFicha(int posInicial, int posiciones){
        if (posiciones==0){
            mandarFichaACasa();
        }
        int posFinal = nuevaPos(posInicial, posiciones);
        //actualizamos el estado del tablero
        tablero.ocuparCasilla(posFinal, color); //ya actualiza la posicion del color
        tablero.quitarFichaDeCasilla(posInicial); //en la función no movemos el color porque se hace en ocuparCasilla
        //actualizamos el estado de la ficha
        casilla = posFinal; 
        comible = !tablero.esSeguro(casilla);
    }
    
    public void mandarFichaACasa(){
        
    }

}
