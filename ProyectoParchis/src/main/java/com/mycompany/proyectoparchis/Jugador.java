/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoparchis;

/**
 *
 * @author alumno
 */
public class Jugador {

    private int numero;
    private String nombre;
    private Color color;
    private Ficha ficha;
    private Tablero tablero;
    private int[] limites = {68,85,102,119}; //limites de casillas antes de entrar al pasillo como si el parchis no volviese al uno despues del 68

    public Jugador(int numero, String nombre, Color color, Ficha ficha, Tablero tablero) {
        this.numero = numero;
        this.nombre = nombre;
        this.color = color;
        this.ficha = ficha;
        this.tablero = tablero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public int getLimite(){
        return limites[numero-1];
    }

    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + ", color=" + color + '}';
    }
    
    
}
