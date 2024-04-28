/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.proyectoparchis;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author ELENA
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("127.0.0.1", 44444);
            Scanner sc = new Scanner(System.in);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);

            System.out.println("Introduce tu nombre: ");
            String nombre = sc.nextLine();
            pw.println(nombre);
            
            //crear un hilo para manejar los mensajes que llegan del grupo
            Thread hiloRecepcion = new Thread(new RecepcionMensajes(socket));
            hiloRecepcion.start();
            
        }catch(IOException e){
            System.err.println("IOException. Mensaje: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        
    
    }
    
}
