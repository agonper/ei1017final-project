package api.modelo.types;

/**
 * Created by alberto on 17/03/15.
 */
public class ClienteParticular extends Cliente {

    private String apellidos;

    public ClienteParticular() {
        super();
    }

    public Cliente setApellidos(String apellidos) {
        this.apellidos = apellidos;
        return this;
    }

    public String toString() {
        return super.toString() + ";" + apellidos;
    }
}
