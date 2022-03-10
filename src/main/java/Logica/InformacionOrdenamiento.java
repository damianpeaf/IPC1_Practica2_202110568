/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author damia
 */
public class InformacionOrdenamiento {
   public String algoritmo;
   public String velocidad;
   public String tipo;
   public int duracion;

    public InformacionOrdenamiento(int algoritmo, int velocidad, int tipo) {
        //Informacion de la ejecucion
        if (algoritmo == 0) {
            this.algoritmo ="Burbuja";
        } else if (algoritmo == 1) {
            this.algoritmo ="Seleccion";
        } else if (algoritmo == 2) {
            this.algoritmo ="Insercion";
        }

        if (tipo == 0) {
            this.tipo="Ascedente";
        } else if (tipo == 1) {
             this.tipo ="Descentende";
        }

        if (velocidad == 0) {
            this.velocidad ="Rapida";
            duracion = 100;
        } else if (velocidad == 1) {
            this.velocidad = "Media";
            duracion = 700;
        } else if (velocidad == 2) {
            this.velocidad= "Lenta";
            duracion = 1500;
        }
    }
   
   
}
