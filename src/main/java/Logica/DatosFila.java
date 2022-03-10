package Logica;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author damia
 */

public class DatosFila{
    public String titulo;
    public int nota;

    public DatosFila(){}
    
    public DatosFila(String titulo, int nota) {
        this.titulo = titulo;
        this.nota = nota;
    }
    
   

    public String getTitulo() {
        return titulo;
    }

    public int getNota() {
        return nota;
    }
    
    public String getNotaAsString() {
        return nota+"";
    }

    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
    
    
}
