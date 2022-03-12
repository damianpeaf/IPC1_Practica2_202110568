/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Vista.Practica2;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author damia
 */
public class Reporte {

    private final String rutaGuardado = "E:\\NetBeansProjects\\Practica2\\reportesGenerados\\";
    private Ordenamiento ordenamiento;
    String reporte = "";

    public Reporte(Ordenamiento ord) {
        ordenamiento = ord;
        crearReporte();
    }

    private void crearReporte() {
        reporte += crearEncabezado("Reporte: " + ordenamiento.tituloGrafica);
        reporte += "<body>"
                + datosPersonales
                + generarDetalles()
                + generarEstadoInicial()
                + generarEstadoFinal()
                + "</body>\n"
                + "\n"
                + "</html>";

        generarArchivo();
    }

    private void generarArchivo() {
        try {

            String direccion = rutaGuardado + ordenamiento.tituloGrafica + "Reporte.html";

            File file = new File(direccion);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(reporte);
            bw.close();
            
            Desktop.getDesktop().open(file);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final String datosPersonales = "<div class=\"datosPersonales\">\n"
            + "        <h1>Damian Ignacio Pe\u00f1a Afre</h1>\n"
            + "        <h2>202110568</h2>\n"
            + "    </div>";

    private String crearTabla(String[] encabezados, DatosFila[] datosCuerpo) {
        String tabla = "\n<table>";

        //Encabezados
        String encabezadoTabla = "\n\t<thead>\n\t\t<tr>";
        for (String encabezado : encabezados) {
            encabezadoTabla = encabezadoTabla + "\n\t\t\t<th>" + encabezado + "</th>";
        }
        encabezadoTabla = encabezadoTabla + "\n\t\t</tr>\n\t</thead>";

        //cuerpo de tabla
        String cuerpoTabla = "\n\t<tbody>";
        for (int i = 0; i < datosCuerpo.length; i++) {
            String fila = "\n\t\t<tr>";
            fila = fila + "\n\t\t\t<td>" + datosCuerpo[i].getTitulo() + "</td>";
            fila = fila + "\n\t\t\t<td>" + datosCuerpo[i].getNota() + "</td>";
            fila = fila + "\n\t\t</tr>";
            cuerpoTabla = cuerpoTabla + fila;
        }
        cuerpoTabla = cuerpoTabla + "\n\t</tbody>";

        tabla = tabla + encabezadoTabla + cuerpoTabla + "\n</table>";

        return tabla;
    }

    private String generarDetalles() {
        return "    <div class=\"dobleColumna\">\n"
                + "        <div>\n"
                + "            <h2>Detalle Ordenamiento</h2>\n"
                + "            <p><span class=\"resaltar\">Algoritmo: </span> " + ordenamiento.info.algoritmo + "</p>\n"
                + "            <p><span class=\"resaltar\">Tipo: </span> " + ordenamiento.info.tipo + "</p>\n"
                + "            <p><span class=\"resaltar\">Velocidad: </span> " + ordenamiento.info.velocidad + "</p>\n"
                + "        </div>\n"
                + "        <div>\n"
                + "            <h2>Detalle de ejecucion</h2>\n"
                + "            <p><span class=\"resaltar\">Tiempo: </span> " + ordenamiento.minutos + ":" + ordenamiento.segundos + ":" + ordenamiento.milisegundos + "</p>\n"
                + "            <p><span class=\"resaltar\">Pasos: </span> " + ordenamiento.pasos + "</p>\n"
                + "        </div>\n"
                + "    </div>";
    }

    private String generarEstadoInicial() {

        String[] encabezados = {ordenamiento.datos.tituloBarras, ordenamiento.datos.tituloNumeracion};

        return "<div>\n"
                + "        <h2>Estado Inicial</h2>\n"
                + crearTabla(encabezados, Practica2.datosGraficaInicial.datosFilas)
                + "        <img src=\"../imagenesGeneradas/" + ordenamiento.tituloGrafica + "Inicial.png\" alt=\"\">\n"
                + "    </div>";
    }

    private String generarEstadoFinal() {

        String[] encabezados = {ordenamiento.datos.tituloBarras, ordenamiento.datos.tituloNumeracion};

        return "<div>\n"
                + "        <h2>Estado Final</h2>\n"
                + crearTabla(encabezados, ordenamiento.datos.datosFilas)
                + "        <img src=\"../imagenesGeneradas/" + ordenamiento.tituloGrafica + "Final.png\" alt=\"\">\n"
                + "    </div>";
    }

    private String crearEncabezado(String titulo) {
        String encabezado = "<!DOCTYPE html>\n"
                + "<html lang=\"es\">\n"
                + "<head>\n"
                + "\t<meta charset=\"UTF-8\">\n"
                + "\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "\t<title>" + titulo + "</title>\n"
                + "\t<!-- Fuentes de google -->\n"
                + "\t<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n"
                + "\t<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n"
                + "\t<link href=\"https://fonts.googleapis.com/css2?family=Fredoka:wght@300&display=swap\" rel=\"stylesheet\">\n"
                + "\t<link rel=\"stylesheet\" href=\"./css/estilos.css\">"
                + "</head>\n";
        return encabezado;
    }

}
