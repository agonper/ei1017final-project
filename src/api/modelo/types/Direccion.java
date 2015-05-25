package api.modelo.types;

import java.io.Serializable;

/**
 * Created by alberto on 17/02/15.
 */
public class Direccion implements Serializable {
    private String direccion;
    private String poblacion;
    private String provincia;
    private int codigoPostal;
    private String pais;

    public Direccion() {

    }

    public Direccion setDireccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public Direccion setPoblacion(String poblacion) {
        this.poblacion = poblacion;
        return this;
    }

    public Direccion setProvincia(String provincia) {
        this.provincia = provincia;
        return this;
    }

    public Direccion setCodigoPostal(String codigoPostal) {
        this.codigoPostal = Integer.parseInt(codigoPostal);
        return this;
    }

    public Direccion setPais(String pais) {
        this.pais = pais;
        return this;
    }

    @Override
    public String toString() {
        return direccion + ". " + poblacion + ", " + provincia + ". " + codigoPostal + ", " + pais;
    }
}
