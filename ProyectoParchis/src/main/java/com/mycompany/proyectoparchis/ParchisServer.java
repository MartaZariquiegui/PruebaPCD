/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoparchis;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author martazariquiegui
 */
public class ParchisServer {
    // Todos los nombres de los clientes, para que podamos comprobar
    //si hay duplicados al registrarse.
    private static Set<String> names = new HashSet<>();
    // El conjunto de todos los print writers de los clientes,
    // empleados para hacer la difusión/broadcast.
    private static Set<PrintWriter> players = new HashSet<>();

    public static void main(String[] args) throws Exception {
        System.out.println("The parhis server is running...");
        ExecutorService pool = Executors.newFixedThreadPool(500);
        try (ServerSocket listener = new ServerSocket(59001)) {
            while (true) {
                //pool.execute(new Handler(listener.accept()));
            }
        }
    }

    public static Set<String> getNames() {
        return names;
    }

    public static Set<PrintWriter> getPlayers() {
        return players;
    }
    
    
}
