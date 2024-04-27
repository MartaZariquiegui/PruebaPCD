/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.proyectoparchis;

import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class JuegoParchis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Bienvenidos al juego del parchis");
        Tablero tablero = new Tablero();
        Scanner sc = new Scanner(System.in);
        System.out.println("Cuantos jugadores van jugar?: ");
        int numJugadores = Integer.parseInt(sc.next());
        System.out.println("Ahora toca introducir los nombres de los jugadores");
        for(Color col : Color.values()){
            if( numJugadores > 0){
                int numJug = Color.values().length - numJugadores;
                Ficha ficha = new Ficha(col, tablero);
                System.out.println("Introduce el nombre del jugador " + numJug + ":");
                String nombre = sc.next();
                Jugador jug = new Jugador(numJug, nombre, col, ficha, tablero);
                System.out.println("Jugador " + numJug + ":" + "Color " + col.toString());
            }
            else{
                break;
            }
            numJugadores = numJugadores - 1;
        } //Para crear tantas ficahs como jugadores haya (1 ficha por jugador)
        
        System.out.println("¡A JUGAR!");
        
        while(tablero != null){
            
        }
        
        
        
        
    }
    
}
