package api.controlador;

import api.modelo.storage.Almacen;
import api.modelo.storage.Referencia;
import api.modelo.storage.ReferenciaCliente;
import api.modelo.storage.ReferenciaLlamada;
import api.modelo.types.Duracion;
import api.modelo.types.Fecha;
import api.modelo.types.Llamada;

import java.util.Map;
import java.util.Scanner;

/**
 * Created by alberto on 25/03/15.
 */
public class ConectorLlamada extends Conector {

    public ConectorLlamada() {

    }

    public ConectorLlamada(Scanner input) {
        super(input);
    }

    @Override
    public String introducirDatos() {
        Map<String, String> datosVista = vista.getSalida();

        if ((ConectorCliente.comprobarCliente((Almacen) datos.get("clientes"), datosVista)).equals("")) {
            return "Error, el cliente no existe";
        }

        Fecha fecha = ConectorFecha.generarFecha(datosVista.get("fecha"));

        Duracion duracion;
        try {
            fecha.setHora(Integer.parseInt(((String) datosVista.get("hora"))), Integer.parseInt(((String) datosVista.get("minuto"))));
            duracion = ConectorDuracion.generarDuración(datosVista);
        } catch (NumberFormatException e) {
            return "Error, los campos de tiempo deben ser numéricos";
        }

        Llamada llamada = (Llamada) new Llamada().setCliente(new ReferenciaCliente(((String) datosVista.get("nif")).toUpperCase()))
                .setDuración(duracion)
                .setFecha(fecha);

        Almacen<Referencia, Llamada> llamadas = (Almacen<Referencia, Llamada>) datos.get("llamadas");
        llamadas.setElemento(new ReferenciaLlamada(llamada.getCliente(), llamada.getCodigoLlamada()), llamada);

        realizarGuardado();

        return "";
    }
}
