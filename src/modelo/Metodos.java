/**
 * Resuelve el taller número 1 del módulo 7 "Integración Módulos de Software",
 * Programa CORFO "Mil Programadores".
 *
 * @autor Daniel Zúñiga Correa, 2017-12-24 (yyyy-mm-dd)
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
 * Clase que contiene los métodos que permiten consumir la información contenida
 * en http://mindicador.cl, y crear la tabla a utilizar en el elemento JTable
 *
 * @author Daniel Zúñiga Correa, 2017-12-24 (yyyy-mm-dd)
 */
public class Metodos {

    public Metodos() {
    }

    /**
     * Método para consumir la información contenida en la api
     * http://mindicador.cl/api
     *
     * @param indicador corresponde al indicador cuyos datos se quiere consumir
     * @return retorna un objeto clase Indicador que contiene los datos
     * consumidos
     */
    public static Indicador consumirIndicador(String indicador) {
        Indicador indicadorTemp = new Indicador();
        int tamano;
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
                fechaTemp[cont] = String.valueOf(objeto.getJsonArray("serie")
                        .getJsonObject(cont).get("fecha")).substring(1, 11);
                valorTemp[cont] = String.valueOf(objeto.getJsonArray("serie")
                        .getJsonObject(cont).get("valor"));
            }
            indicadorTemp.setCodigo(indicador);
            indicadorTemp.setNombre(String.valueOf(objeto.get("nombre"))
                    .substring(1, objeto.get("nombre").toString().length() - 1));
            indicadorTemp.setUnidadMedida(String.valueOf(objeto.get("unidad_medida"))
                    .substring(1, objeto.get("unidad_medida").toString().length() - 1));
            indicadorTemp.setFecha(fechaTemp);
            indicadorTemp.setValor(valorTemp);
            indicadorTemp.calculaVariaciones();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return indicadorTemp;
    }

    /**
     * Método para crear la matriz de datos corrspondientes al indicador
     * consumido, a ingresar en el JTable de la vista listar
     *
     * @return retorna un objeto DefaultTableModel que contiene una matriz de
     * datos del indicador
     */
    public static DefaultTableModel llenarTabla(Indicador indicador) {
        DefaultTableModel tablemodel = new DefaultTableModel();
        String[] nombreColumnas = {"Codigo", "Nombre", "Unidad de Medida", "Fecha",
            "Valor", "Variación (día anterior)"};
        int tamano = indicador.getFecha().length;
        Object[][] dato = new String[tamano][6];
        String[] fecha = indicador.getFecha();
        String[] valor = indicador.getValor();
        double[] diferencia = indicador.getDiferencia();
        int cont = 0;
        while (cont < tamano) {
            dato[cont][0] = indicador.getCodigo();
            dato[cont][1] = indicador.getNombre();
            dato[cont][2] = indicador.getUnidadMedida();
            dato[cont][3] = fecha[cont];
            dato[cont][4] = valor[cont];
            dato[cont][5] = String.valueOf(diferencia[cont]);
            cont++;
        }
        tablemodel.setDataVector(dato, nombreColumnas);
        return tablemodel;
    }

    /**
     * Método para deteminar si el url está disponible
     *
     * @param url corresponde al url a revisar
     * @return retorna true si el url está disponible y false en caso contrario
     */
    public static boolean urlConecta(String url) {
        boolean exito = false;
        try {
            URL urlTemp = new URL(url);
            InputStream entrada = urlTemp.openStream();
            exito = true;
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return exito;
    }

}
