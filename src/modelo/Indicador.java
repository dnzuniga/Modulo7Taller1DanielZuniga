/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author dnzun
 */
public class Indicador {

    private String codigo;
    private String nombre;
    private String unidadMedida;
    private String[] fecha;
    private String[] valor;
    private double[] variacion;

    public Indicador() {
    }

    public Indicador(String codigo, String nombre, String unidadMedida, String[] fecha, String[] valor, double[] variacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.fecha = fecha;
        this.valor = valor;
        this.variacion = variacion;
    }

    public String[] getValor() {
        return valor;
    }

    public void setValor(String[] valor) {
        this.valor = valor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String[] getFecha() {
        return fecha;
    }

    public void setFecha(String[] fecha) {
        this.fecha = fecha;
    }

    public double[] getVariacion() {
        return variacion;
    }

    public void calculaVariaciones() {
        this.variacion = new double[this.valor.length];
        double[] valorNumero = new double[this.valor.length];
        for (int cont = 0; cont < this.valor.length; cont++) {
            valorNumero[cont] = Double.parseDouble(valor[cont]);
        }
        for (int cont = 0; cont < valorNumero.length - 1; cont++) {
            this.variacion[cont] = valorNumero[cont] - valorNumero[cont + 1];
        }
        this.variacion[valorNumero.length - 1] = 0;
    }

}
