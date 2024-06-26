/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoparchis;

import java.io.PrintWriter;

/**
 *
 * @author martazariquiegui
 */
public class Ficha {

    // en esta clase actualizamos el estado de las fichas, si estan en casa o no, su posicion y si pueden ser comidas
    private Tablero tablero;

    private Color color;
    private int casilla = 0;
    private boolean enCasa;
    private boolean ganar = false;
    private boolean comible=true;
    private boolean estaPasillo;
    private int posPasillo;
    private int posAcumulada = 0;

    private Casa casa;

    public Ficha(Color color, Tablero tablero) { // cada vez que creamos una ficha está en casa y no se puede comer
        this.tablero = tablero;
        this.color = color;
        this.enCasa = true;
        this.comible = false;
        this.estaPasillo = false;
        this.posPasillo = 0;
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

    public boolean isGanar() {
        return ganar;
    }

    public void setGanar(boolean ganar) {
        this.ganar = ganar;
    }

    public boolean isComible() {
        return comible;
    }

    public void setComible(boolean comible) {
        this.comible = comible;
    }

    public int getPosPasillo() {
        return posPasillo;
    }

    public void setPosPasillo(int posPasillo) {
        this.posPasillo = posPasillo;
    }

    public Ficha getFichaDeCasilla(int casillaOcupada) {
        Ficha fichaAux = null;
        if (tablero.getEstadoCasilla(casillaOcupada) == 1) {
            //Ya habremos comprobado antes que es esa casilla hay una unica ficha, porque es para la funcion comerFicha
            for (int i = 1; i <= 68; i++) {
                if (casilla == casillaOcupada) {
                    fichaAux = new Ficha(tablero.getColorDeUnaFicha(casillaOcupada), tablero);
                    fichaAux.setCasilla(casillaOcupada);
                    fichaAux.setComible(true);
                    fichaAux.setEnCasa(false);
                    break;
                }
            }
        }
        return fichaAux;
    }

    public void sacarFichaDeCasa(int numJugador) {
        enCasa = false;
        //casa.eliminarDeCasa(this);
        switch (numJugador) {
            case 1:
                casilla = 5;
                posAcumulada = 5;
                tablero.ocuparCasilla(5, this);
                break;
            case 2:
                casilla = 22;
                posAcumulada = 22;
                tablero.ocuparCasilla(22, this);
                break;
            case 3:
                casilla = 39;
                posAcumulada = 39;
                tablero.ocuparCasilla(39, this);
                break;
            case 4:
                casilla = 56;
                posAcumulada = 56;
                tablero.ocuparCasilla(56, this);
                break;
            default:
                System.out.println("Jugador no válido");
                break;
        }
    }

    public int nuevaPos(int posInicial, int posiciones) {
        int posicionFinal = posInicial + posiciones;
        for (int i = 1; i <= posiciones; i++) {
            if (tablero.hayBarrera((posInicial + i) % 68)) {
                posicionFinal = (posInicial + i) - 1;
                return posicionFinal;
            }
        }
        return posicionFinal;
    }

    public void moverFicha(Jugador jugador, int posInicial, int posiciones) {

        for (PrintWriter writer : Servidor.getWriters()) {
            writer.println("Moviendo la ficha... ");
        }

/*CAMBIO*/ int posFinal = nuevaPos(posInicial, posiciones);
        posAcumulada = nuevaPos(posAcumulada, posiciones);
        

        if (estaPasillo == false && posAcumulada < jugador.getLimite()) {
            if (posiciones == 0) {
                mandarFichaACasa();//Significa que habra sacado 3 veces seguidad dados dobles.
            }
            //actualizamos el estado del tablero
/*          tablero.ocuparCasilla(posFinal, this); //ya actualiza la posicion del color
            tablero.quitarFichaDeCasilla(posInicial); //en la función no movemos el color porque se hace en ocuparCasilla
            //actualizamos el estado de la ficha
            casilla = posFinal;*/
/*CAMBIO*/  if (comible =! tablero.esSeguro(casilla)) {
                for (PrintWriter writer : Servidor.getWriters()) {
                    writer.println("El jugador ha comido una ficha y avanza 20 casillas... ");
                }
                moverFicha(jugador, posInicial, 20);
                posAcumulada=20;
                if (posAcumulada >= jugador.getLimite()) { //si sumando 20 entra en el pasillo
                    for (PrintWriter writer : Servidor.getWriters()) {
                        writer.println("El jugador ha entrado al pasillo");
                    }
                    posFinal=posInicial+20;
                    posPasillo = entra_pasillo(jugador.getNumero(), posFinal);
                    estaPasillo=true;
                } else {
                    
                }
            }
//            if (vaAComer(posFinal)) {
//                System.out.println("El jugador ha comido una ficha y avanza 20 casillas");
//                moverFicha(jugador, posFinal, 20, posPasillo);
//            }
        } else if (estaPasillo == false && posAcumulada >= jugador.getLimite()) { //si NO esta en pasillo Y VA A ENTRAR
            for (PrintWriter writer : Servidor.getWriters()) {
                writer.println("El jugador ha entrado al pasillo");
            }
            posPasillo = entra_pasillo(jugador.getNumero(), posFinal);
            estaPasillo = true;
        } else { //YA ESTA EN EL PASILLO
            posPasillo += posiciones;
        }
        
/*CAMBIO*/  tablero.ocuparCasilla(posFinal, this); //ya actualiza la posicion del color
            tablero.quitarFichaDeCasilla(posInicial); //en la función no movemos el color porque se hace en ocuparCasilla
            //actualizamos el estado de la ficha
            casilla = posFinal;


        if (estaPasillo && posPasillo >= 8) {
            for (PrintWriter writer : Servidor.getWriters()) {
                writer.println("¡Enhorabuena jugador " + jugador.getNombre() + " has ganado!");
            }
            Servidor.setPartidaAcabada(true);
        } else if (estaPasillo && posPasillo < 8) {
            for (PrintWriter writer : Servidor.getWriters()) {
                writer.println(jugador.getNombre() + " te faltan " + (8 - posPasillo) + " casillas para ganar");
            }
        } else {
            mostrarDatos();
        }
    }

    public void mandarFichaACasa() {
        casa.meterFichaEnACasa(this);
    }

    public boolean vaAComer(int posicion) {
        return tablero.getEstadoCasilla(posicion) == 1;
    }

    public int entra_pasillo(int numJugador, int nueva_posicion) {
        switch (numJugador) {
            case 1:
                if (nueva_posicion > 68) {
                    estaPasillo = true;
                    nueva_posicion = nueva_posicion - 68;
                }
                break;
            case 2:
                if (nueva_posicion > 17) {
                    estaPasillo = true;
                    nueva_posicion = nueva_posicion - 17;
                }
                break;
            case 3:
                if (nueva_posicion > 34) {
                    estaPasillo = true;
                    nueva_posicion = nueva_posicion - 34;
                }
                break;
            case 4:
                if (nueva_posicion > 51) {
                    estaPasillo = true;
                    nueva_posicion = nueva_posicion - 51;
                }
                break;
        }
        return nueva_posicion;
    }

    public void mostrarDatos() {
        for (PrintWriter writer : Servidor.getWriters()) {
            writer.println("Color: " + getColor());
        }
        if (casilla == 0) {
            for (PrintWriter writer : Servidor.getWriters()) {
                writer.println("Casilla: 68");
            }
        } else {
            for (PrintWriter writer : Servidor.getWriters()) {
                writer.println("Casilla: " + casilla);
            }
        }
    }

}
