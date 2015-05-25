package api.modelo.storage;

import java.io.FileNotFoundException;

/**
 * Created by alberto on 10/04/15.
 */
public interface ConectorAlmacenamiento<T> {
    public T cargarDatos() throws FileNotFoundException;

    public void guardarDatos(T datos);
}
