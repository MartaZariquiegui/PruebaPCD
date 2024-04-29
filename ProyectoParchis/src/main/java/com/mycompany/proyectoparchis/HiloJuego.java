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
    private Casa casa;

    public HiloJuego(Socket socket, Jugador jugador, Ficha ficha, Dado dados, Casa casa) {
        this.socket = socket;
        this.jugador = jugador;
        this.ficha = ficha;
        this.dados = dados;
        this.casa = casa;
    }

    @Override
    public void run() {
        for (PrintWriter writer : Servidor.getWriters()) {
            writer.println("\nEs el turno de " + jugador.getNombre());
        }
        int[] tiradaseparada = dados.tirada_separada(); 
        int tirada = tiradaseparada[0]+tiradaseparada[1];
        
        if (casa.casaVacia() == false) { //si todavia tengo que sacar la ficha de casa

            boolean num5 = false;
            if ((tiradaseparada[0]+tiradaseparada[1])==5){
                num5 = true;
            }else{
                for (int i = 0; i < tiradaseparada.length; i++) {
                    if (tiradaseparada[i] == 5) {
                        num5 = true;
                    }
                }
            }
            
            if (num5 == true) {
                ficha.sacarFichaDeCasa(jugador.getNumero());
                casa.eliminarDeCasa(ficha);
                tirada -= 5;
                for (PrintWriter writer : Servidor.getWriters()) {
                    writer.println(jugador.getNombre() + " ha sacado su ficha de casa.");
                }
            } else {
                for (PrintWriter writer : Servidor.getWriters()) {
                    writer.println(jugador.getNombre() + " no ha conseguido sacar su ficha de casa.");
                }
            }
        }
        if (casa.casaVacia()){
            ficha.moverFicha(jugador, ficha.getCasilla(), tirada, ficha.getPosPasillo());           
        }
        
    }
}
