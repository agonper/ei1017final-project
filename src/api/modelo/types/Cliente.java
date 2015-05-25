package api.modelo.types;

/**
 * Created by alberto on 17/03/15.
 */
public abstract class Cliente extends Componente {
    protected String NIF;
    protected String nombre;
    protected Direccion direccion;
    protected Tarifa tarifa;

    public Cliente() {

    }

    public String getNif() {
        return NIF;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public Cliente setNif(String NIF) {
        this.NIF = NIF;
        return this;
    }

    public Cliente setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public Cliente setDireccion(Direccion direccion) {
        this.direccion = direccion;
        return this;
    }

    public Cliente setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
        return this;
    }

    @Override
    public String toString() {
        return NIF + ";" + nombre + ";" + direccion.toString() +
                ";" + getFecha().toString() + ";" + tarifa.toString();
    }
}
