package api.modelo.types;

import api.modelo.storage.Referencia;

import java.util.List;

/**
 * Created by alberto on 17/02/15.
 */
public class Factura extends Componente {
    private static int autoId = 0;
    private String codigo;
    private Referencia cliente;
    private Tarifa tarifa;
    private List<Llamada> listadoLlamadas;

    public Factura() {
        autoId++;
        codigo = Integer.toString(autoId);
    }

    public String getCodigo() {
        return codigo;
    }

    public Referencia getCliente() {
        return cliente;
    }

    public float getImporte() {
        float precio = 0;
        for (Llamada llamada : listadoLlamadas) {
            precio += tarifa.getPrecioLlamada(llamada);
        }
        return precio;
    }

    public Factura setCliente(Referencia cliente) {
        this.cliente = cliente;
        return this;
    }

    public Factura setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
        return this;
    }

    public Factura setListadoLlamadas(List llamadas) {
        this.listadoLlamadas = llamadas;
        return this;
    }

    @Override
    public String toString() {
        return codigo + ";" + cliente + ";" + getFecha() + ";" + getImporte() + " €";
    }

    public static int getAutoId() {
        return autoId;
    }

    public static void setAutoId(int id) {
        autoId = id;
    }
}
