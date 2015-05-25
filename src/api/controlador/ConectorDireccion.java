package api.controlador;

import api.modelo.types.Direccion;

import java.util.Map;
import java.util.Scanner;

/**
 * Created by alberto on 24/03/15.
 */
public class ConectorDireccion extends Conector {
    public ConectorDireccion(Scanner input) {
        super(input);
    }

    @Override
    public String introducirDatos() {
        // Controlador para una futura vista que cambie las direcciones
        return "";
    }

    public static Direccion generarDirección(Map datos) {
        Direccion direccion = new Direccion().setDireccion(datos.get("via") + ", " + datos.get("numero"))
                .setPoblacion((String) datos.get("poblacion"))
                .setProvincia((String) datos.get("provincia"))
                .setCodigoPostal((String) datos.get("codigopostal"))
                .setPais((String) datos.get("pais"));
        return direccion;
    }
}
