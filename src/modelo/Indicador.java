/**
 * Resuelve el taller número 1 del módulo 7 "Integración Módulos de Software",
 * Programa CORFO "Mil Programadores".
 *
 * @autor Daniel Zúñiga Correa, 2017-12-24 (yyyy-mm-dd)
 */
package modelo;

/**
 * Corresponde a la clase desde la cual se instancian objetos indicador que
 * contienen como atributos los datos del indicador
 *
 * @author Daniel Zúñiga Correa, 2017-12-24 (yyyy-mm-dd)
 */
public class Indicador {

    private String codigo;
    private String nombre;
    private String unidadMedida;
    private String[] fecha;
    private String[] valor;
    private double variacionDiaMinimo;
    private double variacionDiaMaximo;
    private double[] variacion;

    /**
     * Constructor sin paámetros
     */
    public Indicador() {
    }

//    Accesadores y Mutadores
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

    public double getVariacionDiaMinimo() {
        return variacionDiaMinimo;
    }

    public double getVariacionDiaMaximo() {
        return variacionDiaMaximo;
    }

    /**
     * Método para poblar el array de variaciones, así como la menor y mayor
     * variación
     */
    public void calculaVariaciones() {
        this.variacion = new double[this.valor.length];
        double[] valorNumero = new double[this.valor.length];
        for (int cont = 0; cont < this.valor.length; cont++) {
            valorNumero[cont] = Double.parseDouble(valor[cont]);
        }
        this.variacion[0] = valorNumero[0] - valorNumero[1];
        this.variacionDiaMinimo = this.variacion[0];
        this.variacionDiaMaximo = this.variacion[0];
        for (int cont = 1; cont < valorNumero.length - 1; cont++) {
            this.variacion[cont] = valorNumero[cont] - valorNumero[cont + 1];
            if (this.variacionDiaMinimo > this.variacion[cont]) {
                this.variacionDiaMinimo = this.variacion[cont];
            }
            if (this.variacionDiaMaximo < this.variacion[cont]) {
                this.variacionDiaMaximo = this.variacion[cont];
            }
        }
        this.variacion[valorNumero.length - 1] = 0;
    }

    /**
     * Método para obtener los días de menor variación
     *
     * @return retorna un String con todos los días que cumplen con el criterio
     */
    public String obtieneDiasMinimos() {
        String texto = "";
        for (int cont = 0; cont < this.valor.length - 1; cont++) {
            if (this.variacion[cont] == this.variacionDiaMinimo) {
                texto += "Día: " + this.fecha[cont].substring(0, 10) + ", Variación: "
                        + String.format("%.5f", this.variacion[cont]) + "\n";
            }
        }
        return texto;
    }

    /**
     * Método para obtener los días de mayor variación
     *
     * @return retorna un String con todos los días que cumplen con el criterio
     */
    public String obtieneDiasMaximos() {
        String texto = "";
        for (int cont = 0; cont < this.valor.length - 1; cont++) {
            if (this.variacion[cont] == this.variacionDiaMaximo) {
                texto += "Día: " + this.fecha[cont].substring(0, 10) + ", Variación: "
                        + String.format("%.5f", this.variacion[cont]) + "\n";
            }
        }
        return texto;
    }

}
