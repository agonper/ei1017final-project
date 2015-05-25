package api.controlador;

import api.modelo.types.Duracion;
import api.modelo.types.DuracionMinutos;

import java.util.Map;
import java.util.Scanner;

/**
 * Created by alberto on 31/03/15.
 */
public class ConectorDuracion extends Conector {
    public ConectorDuracion(Scanner input) {
        super(input);
    }

    @Override
    public String introducirDatos() {
        // Controlador para una futura vista que cambie las duraciones
        return "";
    }

    public static Duracion generarDuración(Map datos) {
        Duracion duracion = new DuracionMinutos(Integer.parseInt(((String) datos.get("duracionSegundos"))),
                Integer.parseInt(((String) datos.get("duracionMinutos"))),
                Integer.parseInt(((String) datos.get("duracionHoras"))));
        return duracion;
    }
}
