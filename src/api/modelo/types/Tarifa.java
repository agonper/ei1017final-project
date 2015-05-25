package api.modelo.types;

import java.io.Serializable;

/**
 * Created by alberto on 3/03/15.
 */
public abstract class Tarifa implements Serializable{
    private float precio;

    public Tarifa(float precio) {
        this.precio = precio;
    }

    public abstract float getPrecioLlamada(Llamada llamada);

    public float getPrecio() {
        return precio;
    }
}
