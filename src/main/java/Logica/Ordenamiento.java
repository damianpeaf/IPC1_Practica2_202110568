/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import static Vista.Practica2.datosGrafica;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

/**
 *
 * @author damia
 */
public class Ordenamiento implements Runnable{

    private DatosGrafica datos;
    private int tipo, velocidad, algoritmo;
    private Thread tOrdenamiento;
    JPanel panelContenedorGrafica;

    public Ordenamiento(DatosGrafica datos, int tipo, int velocidad, int algoritmo, JPanel panelContenedorGrafica) {
        this.datos = datos;
        this.tipo = tipo;
        this.velocidad = velocidad;
        this.algoritmo = algoritmo;
        this.panelContenedorGrafica = panelContenedorGrafica;

        tOrdenamiento.start();

    }

    @Override
    public void run() {
        
        if (algoritmo == 0) {
            //Burbuja
        }
    }
    
    private void actualizarPanel(){
        JFreeChart graficoBarras = ChartFactory.createBarChart(titulo, datosGrafica.tituloBarras,datosGrafica.tituloNumeracion, DatosGrafica.crearDataSet(datosGrafica), PlotOrientation.VERTICAL, true, true, false);
            ChartPanel panelGrafica = new ChartPanel(graficoBarras);

            panelGrafica.setPreferredSize(new Dimension(380,334));
            
            
            panelContenedorGrafica.setLayout(new BorderLayout());
            panelContenedorGrafica.add(panelGrafica, BorderLayout.CENTER);
            panelContenedorGrafica.validate();
    }

}
