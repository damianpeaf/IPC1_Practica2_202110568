/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import static Vista.Practica2.datosGrafica;
import Vista.Simulacion;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

/**
 *
 * @author damia
 */
public class Ordenamiento implements Runnable {

    public DatosGrafica datos;
    public int tipo, velocidad, algoritmo;
    private Thread tOrdenamiento;
    public String tituloGrafica;
    public InformacionOrdenamiento info;
    public int pasos = 0;

    public JPanel panelContenedorGrafica;
    public JLabel labelPasos;

    public Ordenamiento(DatosGrafica datos, int algoritmo, int tipo, int velocidad, String titulo) {
        this.datos = datos;
        this.tipo = tipo;
        this.algoritmo = algoritmo;
        this.tituloGrafica = titulo;

        info = new InformacionOrdenamiento(algoritmo, velocidad, tipo);

    }

    public void empezarSimulacion(JPanel panel, JLabel label) {
        panelContenedorGrafica = panel;
        labelPasos = label;
        tOrdenamiento = new Thread(this);
        tOrdenamiento.start();
    }

    @Override
    public void run() {

        boolean ordenado = false;

        while (!tOrdenamiento.isInterrupted()) {

            while (!ordenado) {

                if (algoritmo == 0) {
                    //Burbuja
                    ordenado = burbuja(tipo);
                } else if (algoritmo == 1) {
                    //seleccion
                    ordenado = seleccion(tipo);
                } else if (algoritmo == 2) {
                    //insercion
                    ordenado = insercion(tipo);
                }
            }

            Simulacion.cronometro.detener();
            detener();
        }

        JOptionPane.showMessageDialog(null, "Ordenamiento finalizado, generando reporte...");

    }

    private void detener() {
        //TODO: ARREGLAR ESTO
        JFreeChart graficoBarras = ChartFactory.createBarChart(tituloGrafica, datosGrafica.tituloBarras, datosGrafica.tituloNumeracion, DatosGrafica.crearDataSet(datosGrafica), PlotOrientation.VERTICAL, true, true, false);
        ChartPanel panelGrafica = new ChartPanel(graficoBarras);
        panelGrafica.setPreferredSize(new Dimension(panelContenedorGrafica.getWidth(), panelContenedorGrafica.getHeight()));

        panelContenedorGrafica.setLayout(new BorderLayout());
        panelContenedorGrafica.add(panelGrafica, BorderLayout.CENTER);
        panelContenedorGrafica.validate();

        tOrdenamiento.interrupt();
    }

    private void actualizarPanel() {
        JFreeChart graficoBarras = ChartFactory.createBarChart(tituloGrafica, datosGrafica.tituloBarras, datosGrafica.tituloNumeracion, DatosGrafica.crearDataSet(datosGrafica), PlotOrientation.VERTICAL, true, true, false);
        ChartPanel panelGrafica = new ChartPanel(graficoBarras);
        panelGrafica.setPreferredSize(new Dimension(panelContenedorGrafica.getWidth(), panelContenedorGrafica.getHeight()));

        panelContenedorGrafica.setLayout(new BorderLayout());
        panelContenedorGrafica.add(panelGrafica, BorderLayout.CENTER);
        panelContenedorGrafica.validate();

        pasos++;
        labelPasos.setText("" + pasos);

        try {
            Thread.sleep(info.duracion);
        } catch (InterruptedException ex) {
        }
    }

    private boolean burbuja(int tipo) {
        for (int x = 0; x < datos.datosFilas.length; x++) {
            for (int y = 0; y < datos.datosFilas.length - 1; y++) {
                int numeroActual = datos.datosFilas[y].getNota();
                int numeroSiguiente = datos.datosFilas[y + 1].getNota();

                boolean clausula;

                if (tipo == 0) {
                    clausula = numeroActual > numeroSiguiente;
                } else {
                    clausula = numeroActual < numeroSiguiente;

                }

                if (clausula) {
                    // Intercambiar
                    DatosFila aux = datos.datosFilas[y];
                    datos.datosFilas[y] = datos.datosFilas[y + 1];
                    datos.datosFilas[y + 1] = aux;
                }

                actualizarPanel();

            }
        }
        return true;
    }

    private boolean seleccion(int tipo) {

        for (int i = 0; i < datos.datosFilas.length - 1; i++) {
            for (int j = i + 1; j < datos.datosFilas.length; j++) {

                boolean clausula;

                if (tipo == 0) {
                    clausula = datos.datosFilas[i].getNota() > datos.datosFilas[j].getNota();
                } else {
                    clausula = datos.datosFilas[i].getNota() < datos.datosFilas[j].getNota();
                }

                if (clausula) {

                    DatosFila temporal = datos.datosFilas[i];
                    datos.datosFilas[i] = datos.datosFilas[j];
                    datos.datosFilas[j] = temporal;
                }

                actualizarPanel();

            }
        }

        return true;
    }

    private boolean insercion(int tipo) {

        if (tipo == 0) {
            for (int j = 1; j < datos.datosFilas.length; j++) {

                DatosFila actual = datos.datosFilas[j];
                int i = j - 1;

                while ((i > -1) && (datos.datosFilas[i].getNota() > actual.getNota())) {
                    datos.datosFilas[i + 1] = datos.datosFilas[i];
                    i--;

                }
                datos.datosFilas[i + 1] = actual;
                actualizarPanel();

            }
        } else {
            for (int j = 1; j < datos.datosFilas.length; j++) {

                DatosFila actual = datos.datosFilas[j];
                int i = j - 1;

                while ((i > -1) && (datos.datosFilas[i].getNota() < actual.getNota())) {
                    datos.datosFilas[i + 1] = datos.datosFilas[i];
                    i--;

                }
                datos.datosFilas[i + 1] = actual;
                actualizarPanel();

            }
        }

        return true;
    }

}
