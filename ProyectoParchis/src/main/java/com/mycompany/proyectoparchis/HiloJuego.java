/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoparchis;

import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author ELENA
 */
public class HiloJuego implements Runnable {
    private Socket socket;
    private Jugador jugador;
    private Ficha ficha;
    private Dado dados;
    private Tablero tablero;
    Casa casaJugador = new Casa (1,jugador.getColor(),tablero); //se juega con una ficha

    public HiloJuego(Socket socket, Jugador jugador, Ficha ficha, Dado dados){
        this.socket = socket;
        this.jugador = jugador;
        this.dados = dados;
    }
    
    @Override
    public void run() {
        for (PrintWriter writer : Servidor.getWriters()) {
            writer.println("Es el turno de " + jugador.getNombre());
        }
        int tirada = dados.tirada();
            for (PrintWriter writer : Servidor.getWriters()) {
                writer.println(jugador.getNombre() + " ha sacado " + tirada);
            }
        if (casaJugador.casaVacia()!=true) { //si todavia tengo que sacar la ficha de casa
            
            int[] tiradaseparada = dados.tirada_separada();
            boolean num5=false;
            for (int i=0;i<tiradaseparada.length;i++) {
                if (tiradaseparada[i]==5)
                    num5=true;
            }
            
            if (num5==true) {
                ficha.sacarFichaDeCasa(jugador.getNumero());
                for (PrintWriter writer : Servidor.getWriters()) {
                    writer.println(jugador.getNombre()+" ha sacado su ficha de casa.");
                }
            } else {
                for (PrintWriter writer : Servidor.getWriters()) {
                    writer.println(jugador.getNombre()+" no ha sacado su ficha de casa.");
                }
            int posicionfinal = ficha.getCasilla()+tirada;
            ficha.moverFicha(jugador, ficha.getCasilla(), tirada);
            if ((ficha.comerFicha(posicionfinal))==true) {
                ficha.mandarFichaACasa();
                for (PrintWriter writer : Servidor.getWriters()) {
                    writer.println(jugador.getNombre()+" ha comido a otra ficha."); //???? COMO INDICO A QUIEN COME
                }
            }
            //FALTA BARRERA Y DEMAS
        }
        ficha.mostrarDatos(tablero);
    }
}
