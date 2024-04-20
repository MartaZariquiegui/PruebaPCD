/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoparchis;

import java.util.Random;

/**
 *
 * @author martazariquiegui
 */
public class Dado {
    Random random = new Random();
    private int numero;

    public Dado(int numero) {
        this.numero = numero;
    }
    
    public int lanzar() {
        numero = random.nextInt(numero)+1;
        System.out.println("El numero sacado del dado es "+numero);
        return numero;
    }
}
