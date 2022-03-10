/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author damia
 */
public class DatosGrafica {

    public DatosGrafica() {

    }

    private String datosSinFormato;
    public String tituloNumeracion;
    public String tituloBarras;
    public DatosFila[] datosFilas;
    private int tipo;

    public DatosGrafica(String datos, int ti) {
        datosSinFormato = datos;
        tipo = ti;
        formatearDatos();
    }

    private void formatearDatos() {
        String[] arregloAux1 = datosSinFormato.split("\n");

        datosFilas = new DatosFila[arregloAux1.length - 1];

        int contadorAux = 0;
        boolean encontrado = false;
        for (String fila : arregloAux1) {

            String[] arregloAux2 = fila.split(",");

            if (!encontrado) {

                //encabezados
                tituloBarras = arregloAux2[0].trim();
                tituloNumeracion = arregloAux2[1].trim();

                encontrado = true;

            } else {


                String tema = arregloAux2[0].trim();
                int nota = Integer.parseInt(arregloAux2[1].trim());
                
                
                // clase - valor
                datosFilas[contadorAux] = new DatosFila(tema, nota);
                contadorAux++;
            }
        }
        
    }

    public static CategoryDataset crearDataSet(DatosGrafica datos) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    
        String descripcion = "";
        
        if (datos.tipo==0) {
            descripcion = "Ascendente";
        }else{
            descripcion = "Descendente";
        }
        
        for (DatosFila datoColumna : datos.datosFilas) {
            dataset.addValue(datoColumna.getNota(), datoColumna.getTitulo(), descripcion);
            //dataset.addValue(1, "MAte", "pedro");

        }

        
        return dataset;

    }
}
