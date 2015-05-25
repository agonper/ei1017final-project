package api.modelo.storage;

/**
 * Created by alberto on 24/03/15.
 */
public class ReferenciaLlamada implements Referencia{
    private Referencia codcli;
    private long codLlamada;

    public ReferenciaLlamada(Referencia codcli, long codigoLlamada) {
        this.codcli = codcli;
        this.codLlamada = codigoLlamada;
    }

    public String toString() {
        return codcli + " " + codLlamada;
    }
}
