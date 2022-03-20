/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import static Vista.Practica2.datosGrafica;
import Vista.Simulacion;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

/**
 *
 * @author damia
 */
public class Ordenamiento implements Runnable {

    //Informacion del csv
    public DatosGrafica datos;

    //Informacion de la simulacion
    public InformacionOrdenamiento info;
    public int tipo, velocidad, algoritmo;
    public String tituloGrafica;
    public int pasos = 0;
    //tiempo
    int minutos, segundos, milisegundos;

    //Thread del ordenamiento
    private Thread tOrdenamiento;

    //Archivo de imagen
    public File imagenGrafica;
    JFreeChart graficaBarrasFinal;

    //Elementos graficos de la simulacion
    public JPanel panelContenedorGrafica;
    public JLabel labelPasos;

    public Ordenamiento(DatosGrafica datos, int algoritmo, int tipo, int velocidad, String titulo) {
        this.datos = datos;
        this.tipo = tipo;
        this.algoritmo = algoritmo;
        this.tituloGrafica = (titulo.equals("")) ? "Grafica" : titulo;

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

        generarImagen();

        milisegundos = Cronometro.milisegundos;
        segundos = Cronometro.segundos;
        minutos = Cronometro.minutos;
        
        Reporte reporte = new Reporte(this);

        JOptionPane.showMessageDialog(null, "Ordenamiento finalizado, generando reporte...");

    }

    private void generarImagen() {
        imagenGrafica = new File("E:\\NetBeansProjects\\Practica2\\imagenesGeneradas\\" + tituloGrafica + "Final.png");
        try {
            ChartUtils.saveChartAsJPEG(imagenGrafica, graficaBarrasFinal, 600, 600);
        } catch (IOException ex) {
        }
    }

    private void detener() {
        //TODO: ARREGLAR ESTO
        JFreeChart graficoBarras = ChartFactory.createBarChart(tituloGrafica, datosGrafica.tituloBarras, datosGrafica.tituloNumeracion, DatosGrafica.crearDataSet(datosGrafica), PlotOrientation.VERTICAL, true, true, false);
        ChartPanel panelGrafica = new ChartPanel(graficoBarras);
        panelGrafica.setPreferredSize(new Dimension(panelContenedorGrafica.getWidth(), panelContenedorGrafica.getHeight()));

        graficaBarrasFinal = graficoBarras;

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
