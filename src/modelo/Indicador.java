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
    private double diferenciaMinima;
    private double diferenciaMaxima;
    private double[] diferencia;

    /**
     * Constructor sin parámetros
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

    public double[] getDiferencia() {
        return diferencia;
    }

    public double getDiferenciaMinima() {
        return diferenciaMinima;
    }

    public double getDiferenciaMaxima() {
        return diferenciaMaxima;
    }

    /**
     * Método para poblar el array de diferencias, así como la menor y mayor
     * diferencias
     */
    public void calculaVariaciones() {
        this.diferencia = new double[this.valor.length];
        double[] valorNumero = new double[this.valor.length];
        for (int cont = 0; cont < this.valor.length; cont++) {
            valorNumero[cont] = Double.parseDouble(valor[cont]);
        }
        this.diferencia[0] = valorNumero[0] - valorNumero[1];
        this.diferenciaMinima = this.diferencia[0];
        this.diferenciaMaxima = this.diferencia[0];
        for (int cont = 1; cont < valorNumero.length - 1; cont++) {
            this.diferencia[cont] = valorNumero[cont] - valorNumero[cont + 1];
            if (this.diferenciaMinima > this.diferencia[cont]) {
                this.diferenciaMinima = this.diferencia[cont];
            }
            if (this.diferenciaMaxima < this.diferencia[cont]) {
                this.diferenciaMaxima = this.diferencia[cont];
            }
        }
        this.diferencia[valorNumero.length - 1] = 0;
    }

    /**
     * Método para obtener los días de menor diferencia
     *
     * @return retorna un String con todos los días que cumplen con el criterio
     */
    public String obtieneDiasDiferenciaMinima() {
        String texto = "";
        for (int cont = 0; cont < this.valor.length - 1; cont++) {
            if (this.diferencia[cont] == this.diferenciaMinima) {
                texto += "Día: " + this.fecha[cont].substring(0, 10) + ", Variación: "
                        + String.format("%.5f", this.diferencia[cont]) + "\n";
            }
        }
        return texto;
    }

    /**
     * Método para obtener los días de mayor diferencia
     *
     * @return retorna un String con todos los días que cumplen con el criterio
     */
    public String obtieneDiasDiferenciaMaxima() {
        String texto = "";
        for (int cont = 0; cont < this.valor.length - 1; cont++) {
            if (this.diferencia[cont] == this.diferenciaMaxima) {
                texto += "Día: " + this.fecha[cont].substring(0, 10) + ", Variación: "
                        + String.format("%.5f", this.diferencia[cont]) + "\n";
            }
        }
        return texto;
    }

}
