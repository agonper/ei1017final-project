package api.modelo.types;

import java.io.Serializable;

/**
 * Created by alberto on 14/04/15.
 */
public class TarifaBase extends Tarifa {
    private static final float PRECIO = 0.15f;

    public TarifaBase() {
        super(PRECIO);
    }

    public TarifaBase(float precio){
        super(precio);
    }

    @Override
    public float getPrecioLlamada(Llamada llamada) {
        return llamada.getDuracion().getDuracion() * getPrecio();
    }

    @Override
    public String toString() {
        return "Tarifa base - Precio: " + getPrecio() + " €/min";
    }
}
