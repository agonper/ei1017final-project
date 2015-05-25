package api.modelo.types;

/**
 * Created by alberto on 28/04/15.
 */
public class FactoriaClientes{
    public static final int CLIENTE_PARTICULAR = 0;
    public static final int CLIENTE_EMPRESA = 1;

    public Cliente generarCliente(int posicion) {
        switch (posicion) {
            case CLIENTE_PARTICULAR:
                return new ClienteParticular();
            case CLIENTE_EMPRESA:
                return new ClienteEmpresa();
        }
        return null;
    }
}
