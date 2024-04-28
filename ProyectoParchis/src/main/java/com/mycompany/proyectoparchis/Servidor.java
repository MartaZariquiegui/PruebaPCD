/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.proyectoparchis;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *
 * @author ELENA
 */
public class Servidor {

    private static List<Jugador> jugadores = Collections.synchronizedList(new ArrayList<>());
    private static List<Ficha> fichas = Collections.synchronizedList(new ArrayList<>());
    private static Set<PrintWriter> writers = new CopyOnWriteArraySet<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Tablero tablero = new Tablero();
        Dado dados = new Dado();

        List<Color> colores = new ArrayList<>();
        for (Color color : Color.values()) {
            colores.add(color);
        }
        List<Socket> sockets = new ArrayList<>(); //lista para almacenar los sockets de los jugadores

        try {
            ServerSocket serversocket = new ServerSocket(44444);
            for (int i = 0; i < 4; i++) {
                System.out.println("Esperando jugadores...");
                Socket jugadorsocket = serversocket.accept();
                sockets.add(jugadorsocket);
                Ficha ficha = new Ficha(colores.get(i),tablero);
                fichas.add(ficha);
                Thread hilojugador = new Thread(new HiloJugador(jugadorsocket, i, colores.get(i),tablero,ficha));
                hilojugador.start();
                hilojugador.join();
            }
            System.out.println("Se han unido los cuatro jugadores");
            for (int i = 0; i < jugadores.size(); i++) {
                PrintWriter out = new PrintWriter(sockets.get(i).getOutputStream(), true);
                writers.add(out);
            } //añado todos los jugadores como escritores
            for (int rondas = 0; rondas < 2; rondas++) {
                for (int i = 0; i < jugadores.size(); i++) {
                    Jugador jugador = jugadores.get(i);
                    Thread hiloJuego = new Thread(new HiloJuego(sockets.get(i), jugador, fichas.get(i),dados));
                    hiloJuego.start();
                    hiloJuego.join();
                }
            }

        } catch (IOException e) {
            System.err.println("IOException. Mensaje: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (InterruptedException ex) {
            System.err.println("IOException. Mensaje: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public static List<Jugador> getJugadores() {
        return jugadores;
    }

    public static void setJugadores(List<Jugador> jugadores) {
        Servidor.jugadores = jugadores;
    }

    public static Set<PrintWriter> getWriters() {
        return writers;
    }

    public static void setWriters(Set<PrintWriter> writers) {
        Servidor.writers = writers;
    }

    public static List<Ficha> getFichas() {
        return fichas;
    }

    public static void setFichas(List<Ficha> fichas) {
        Servidor.fichas = fichas;
        }
    
}
