package api.controlador;

import api.modelo.types.Fecha;
import api.modelo.types.FechaGregorianCalendar;
import api.modelo.types.RangoFechaIncorrectoException;

import java.util.Date;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by alberto on 24/03/15.
 */
public class ConectorFecha extends Conector {
    public ConectorFecha(Scanner input) {
        super(input);
    }

    @Override
    public String introducirDatos() {
        // Controlador para una futura vista fechas
        return "";
    }

    public static Fecha generarFecha(String fechaCadena) {
        String[] cadenaFecha = fechaCadena.split("/");
        Fecha fecha = new FechaGregorianCalendar().setFecha(Integer.parseInt(cadenaFecha[0]), Integer.parseInt(cadenaFecha[1]), Integer.parseInt(cadenaFecha[2]));
        return fecha;
    }
}
