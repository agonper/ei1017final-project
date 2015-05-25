package api.modelo.types;

import api.modelo.storage.Referencia;

/**
 * Created by alberto on 17/02/15.
 */
public class Llamada extends Componente {
    private Duracion duraccion;
    private Referencia cliente;
    private static long autoId = 0;
    private long codigoLlamada;

    public Llamada() {
        autoId++;
        codigoLlamada = autoId;
    }

    public long getCodigoLlamada() {
        return codigoLlamada;
    }

    public Duracion getDuracion() {
        return duraccion;
    }

    public Referencia getCliente() {
        return cliente;
    }

    public Llamada setCliente(Referencia cliente) {
        this.cliente = cliente;
        return this;
    }

    public Llamada setDuración(Duracion duracion) {
        this.duraccion = duracion;
        return this;
    }

    @Override
    public String toString() {
        return codigoLlamada + ";" + cliente + ";" + getFecha() + ";" + getFecha().getHora() + ";" + duraccion;
    }

    public static long getAutoId() {
        return autoId;
    }

    public static void setAutoId(long id) {
        autoId = id;
    }
}
