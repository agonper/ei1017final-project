package api.modelo.types;

/**
 * Created by alberto on 10/04/15.
 */
public class RangoFechaIncorrectoException extends Exception {

    public RangoFechaIncorrectoException() {
        super("Error. La fecha inicial no puede ser mayor que la final");
    }

}
