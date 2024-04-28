/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoparchis;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author ELENA
 */
public class HiloJugador implements Runnable {
    private Socket socket;
    private int index;
    private Color color;
    private Tablero tablero;
    private Ficha ficha;
    
    public HiloJugador(Socket jugadorsocket, int index, Color color, Tablero tablero, Ficha ficha){
        this.socket = jugadorsocket;
        this.index = index;
        this.color = color;
        this.tablero = tablero;
        this.ficha = ficha;
    }
    
    @Override
    public void run() {
        try{
            Scanner in = new Scanner(socket.getInputStream());
            
            String nombre = in.nextLine();
            int num = index+1;
            
            Jugador jugador = new Jugador(num, nombre, color, ficha, tablero);
            
            Servidor.getJugadores().add(jugador);
        }catch(IOException e){
            System.err.println("IOException. Mensaje: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
    
}
