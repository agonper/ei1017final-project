package api.modelo.storage;

/**
 * Created by alberto on 24/03/15.
 */
public class ReferenciaFactura implements Referencia{
    String codfac;

    public ReferenciaFactura(String codfac) {
        this.codfac = codfac;

    }

    public String toString() {
        return codfac;
    }
}
