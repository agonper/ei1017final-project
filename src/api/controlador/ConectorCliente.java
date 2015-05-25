package api.controlador;

import api.modelo.storage.Almacen;
import api.modelo.storage.ReferenciaCliente;
import api.modelo.types.*;

import java.util.List;
import java.util.Map;

/**
 * Created by alberto on 24/03/15.
 */
public class ConectorCliente extends Conector {

    public ConectorCliente() {

    }

    @Override
    public String introducirDatos() {
        Map<String, String> datosVista = super.vista.getSalida();

        String error;
        if (!(error = comprobarCliente((Almacen) datos.get("clientes"), datosVista)).equals("")) {
            return error;
        }

        Direccion direccion;
        try {
            direccion = ConectorDireccion.generarDirección(datosVista);
        } catch (NumberFormatException e) {
            return "Erro, debes introducir un código postal válido";
        }

        Fecha fecha = ConectorFecha.generarFecha(datosVista.get("fecha"));

        Tarifa tarifa = ConectorTarifa.generarTarifa(datosVista.get("tarifa"));

        // Identificadores únicos
        String nif = datosVista.get("nif").toUpperCase();


        FactoriaClientes factoria = new FactoriaClientes();
        Almacen datosClientes = (Almacen) datos.get("clientes");
        if ("particular".equals(datosVista.get("tipo"))) {
            ClienteParticular cliente = (ClienteParticular) factoria.generarCliente(FactoriaClientes.CLIENTE_PARTICULAR);
            cliente.setApellidos(datosVista.get("apellidos"))
                    .setNif(nif)
                    .setNombre(datosVista.get("nombre"))
                    .setDireccion(direccion)
                    .setTarifa(tarifa)
                    .setFecha(fecha);
            datosClientes.setElemento(new ReferenciaCliente(nif), cliente);
        } else {
            Cliente cliente = factoria.generarCliente(FactoriaClientes.CLIENTE_EMPRESA);
            cliente.setNif(nif)
                    .setNombre(datosVista.get("nombre"))
                    .setDireccion(direccion)
                    .setTarifa(tarifa)
                    .setFecha(fecha);
            datosClientes.setElemento(new ReferenciaCliente(nif), cliente);
        }
        realizarGuardado();
        return "";
    }

    public void borrarElementos(List referencias) {
        Almacen datosClientes = (Almacen) datos.get("clientes");
        datosClientes.borrarElementos(referencias);

        realizarGuardado();
    }

    public static String comprobarCliente(Almacen datosGuardados, Map datosNuevos) {
        if (datosGuardados.getElemento(new ReferenciaCliente(((String) datosNuevos.get("nif")).toUpperCase())).get(0) != null) {
            return "Error, el cliente ya existe";
        }
        return "";
    }
}
