/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dnzun
 */
public class Metodos {

    public Metodos() {
    }

    //Métodos Custom
    public static Indicador leeIndicador(String indicador) {
        Indicador indicadorTemp = new Indicador();
        int tamano;
        String nombre;
        String unidadMedida;
        String[] fechaTemp = new String[31];
        String[] valorTemp = new String[31];
        try {
            URL url = new URL("http://mindicador.cl/api" + "/" + indicador);
            InputStream entrada = url.openStream();
            JsonReader reader = Json.createReader(entrada);
            JsonObject objeto = reader.readObject();
            tamano = objeto.getJsonArray("serie").size();
            fechaTemp = new String[tamano];
            valorTemp = new String[tamano];
            for (int cont = 0; cont < objeto.getJsonArray("serie").size(); cont++) {
                fechaTemp[cont] = String.valueOf(objeto.getJsonArray("serie").getJsonObject(cont).get("fecha"));
                valorTemp[cont] = String.valueOf(objeto.getJsonArray("serie").getJsonObject(cont).get("valor"));
            }
            indicadorTemp.setCodigo(indicador);
            indicadorTemp.setNombre(String.valueOf(objeto.get("nombre")));
            indicadorTemp.setUnidadMedida(String.valueOf(objeto.get("unidad_medida")));
            indicadorTemp.setFecha(fechaTemp);
            indicadorTemp.setValor(valorTemp);
            indicadorTemp.calculaVariaciones();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return indicadorTemp;
    }
//
//    public static String obtieneNombre(String indicador) {
//        String texto = "";
//        try {
//            URL url = new URL("http://mindicador.cl/api" + "/" + indicador);
//            InputStream entrada = url.openStream();
//            JsonReader reader = Json.createReader(entrada);
//            JsonObject objeto = reader.readObject();
//            texto = objeto.getString("nombre");
//        } catch (MalformedURLException e) {
//        } catch (IOException e) {
//        }
//        return texto;
//    }
//
//    public static String obtieneUnidadMedida(String indicador) {
//        String texto = "";
//        try {
//            URL url = new URL("http://mindicador.cl/api" + "/" + indicador);
//            InputStream entrada = url.openStream();
//            JsonReader reader = Json.createReader(entrada);
//            JsonObject objeto = reader.readObject();
//            texto = objeto.getString("unidad_medida");
//        } catch (MalformedURLException e) {
//        } catch (IOException e) {
//        }
//        return texto;
//    }
//
//    public static String[] obtieneFecha(String indicador) {
//        int tamano = 0;
//        String[] fechaTemp = new String[31];
//        try {
//            URL url = new URL("http://mindicador.cl/api" + "/" + indicador);
//            InputStream entrada = url.openStream();
//            JsonReader reader = Json.createReader(entrada);
//            JsonObject objeto = reader.readObject();
//            tamano = objeto.getJsonArray("serie").size();
//            fechaTemp = new String[tamano];
//            for (int cont = 0; cont < objeto.getJsonArray("serie").size(); cont++) {
//                fechaTemp[cont] = String.valueOf(objeto.getJsonArray("serie").getJsonObject(cont).get("fecha"));
//            }
//        } catch (MalformedURLException e) {
//        } catch (IOException e) {
//        }
//        return fechaTemp;
//    }
//
//    public static String[] obtieneValor(String indicador) {
//        int tamano = 0;
//        String[] valorTemp = new String[31];
//        try {
//            URL url = new URL("http://mindicador.cl/api" + "/" + indicador);
//            InputStream entrada = url.openStream();
//            JsonReader reader = Json.createReader(entrada);
//            JsonObject objeto = reader.readObject();
//            tamano = objeto.getJsonArray("serie").size();
//            valorTemp = new String[tamano];
//            for (int cont = 0; cont < objeto.getJsonArray("serie").size(); cont++) {
//                valorTemp[cont] = String.valueOf(objeto.getJsonArray("serie").getJsonObject(cont).get("valor"));
//            }
//        } catch (MalformedURLException e) {
//        } catch (IOException e) {
//        }
//        return valorTemp;
//    }
//
//    public static int obtieneTamano(String indicador) {
//        int tamano = 0;
//        try {
//            URL url = new URL("http://mindicador.cl/api" + "/" + indicador);
//            InputStream entrada = url.openStream();
//            JsonReader reader = Json.createReader(entrada);
//            JsonObject objeto = reader.readObject();
//            tamano = objeto.getJsonArray("serie").size();
//        } catch (MalformedURLException e) {
//        } catch (IOException e) {
//        }
//        return tamano;
//    }
//

    /**
     * Método para crear la matriz de empleados a ingresar en el JTable de la
     * vista listar
     *
     * @return retorna un objeto DefaultTableModel que contiene una matriz de
     * empleados
     */
    public static DefaultTableModel llenarTabla(Indicador indicador) {
        DefaultTableModel tablemodel = new DefaultTableModel();
        String[] nombreColumnas = {"Codigo", "Nombre", "Unidad de Medida", "Fecha",
            "Valor", "Variación (día anterior)"};
        int tamano = indicador.getFecha().length;
        Object[][] dato = new String[tamano][6];
        String[] fecha = indicador.getFecha();
        String[] valor = indicador.getValor();
        double[] variacion = indicador.getVariacion();
        int cont = 0;
        while (cont < tamano) {
            dato[cont][0] = indicador.getCodigo();
            dato[cont][1] = indicador.getNombre();
            dato[cont][2] = indicador.getUnidadMedida();
            dato[cont][3] = fecha[cont];
            dato[cont][4] = valor[cont];
            dato[cont][5] = String.valueOf(variacion[cont]);
            cont++;
        }
        tablemodel.setDataVector(dato, nombreColumnas);
        return tablemodel;
    }
}
