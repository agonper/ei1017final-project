package api.modelo.types;

import java.io.Serializable;

/**
 * Created by alberto on 9/04/15.
 */
public class Componente implements Serializable {
    private Fecha fecha;

    public Componente() {

    }

    public Fecha getFecha() {
        return fecha;
    }

    public Componente setFecha(Fecha fecha) {
        this.fecha = fecha;
        return this;
    }

}
