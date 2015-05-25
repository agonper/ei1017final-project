package api.modelo.storage;

/**
 * Created by alberto on 24/03/15.
 */
public class ReferenciaCliente implements Referencia{
    private String codcli;

    public ReferenciaCliente(String codcli) {
        this.codcli = codcli;
    }

    public String toString() {
        return codcli;
    }
}
