/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author damia
 */
public class Cronometro implements Runnable {

    private Thread tCronometro;

    public static int milisegundos, segundos, minutos;

    JLabel label;

    public Cronometro(JLabel lbl) {
        label = lbl;
        tCronometro = new Thread(this);
        tCronometro.start();
        milisegundos = 0;
        segundos = 0;
        minutos = 0;
    }

    private String formatearTiempo() {
        String fecha = "";
        String mil, min, sec;

        milisegundos += 2;
        if (milisegundos > 999) {
            milisegundos = 0;
            segundos++;
        }

        if (segundos > 59) {
            segundos = 0;
            minutos++;
        }

        if (milisegundos <= 9) {
            mil = "000" + milisegundos;
        } else if (milisegundos <= 99) {
            mil = "00" + milisegundos;
        } else if (milisegundos <= 999) {
            mil = "0" + milisegundos;
        }else{
            mil = ""+milisegundos;
        }
        
        if (segundos <= 9) {
            sec = "0" + segundos;
        }else{
            sec = ""+ segundos;
        }
        
        if (minutos <= 9) {
            min = "0" + minutos;
        }else{
            min = ""+ minutos;
        }

        fecha = min + ":" + sec + ":" + mil;

        return fecha;
    }

    public void detener() {
        tCronometro.interrupt();
    }

    @Override
    public void run() {
        try {
            while (!tCronometro.isInterrupted()) {
                label.setText(formatearTiempo());
                Thread.sleep(1);
            }
        } catch (InterruptedException ex) {
        }
    }
}
