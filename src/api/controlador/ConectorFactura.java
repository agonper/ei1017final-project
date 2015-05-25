package api.controlador;

import api.modelo.storage.Almacen;
import api.modelo.storage.ReferenciaCliente;
import api.modelo.storage.ReferenciaFactura;
import api.modelo.types.*;

import java.util.*;

/**
 * Created by alberto on 20/05/15.
 */
public class ConectorFactura extends Conector {
    @Override
    public Object introducirDatos() {
        Map<String, String> datosVista = vista.getSalida();

        String error;
        if ((error = ConectorCliente.comprobarCliente((Almacen) datos.get("clientes"), datosVista)).equals("")) {
            return "Error, el cliente no existe";
        }
        String nif = datosVista.get("nif").toUpperCase();

        Tarifa tarifaCliente = ((Cliente) ((Almacen) datos.get("clientes"))
                .getElemento(new ReferenciaCliente(nif)).get(0))
                .getTarifa();

        Fecha fechaFactura = ConectorFecha.generarFecha(datosVista.get("fecha"));
        Fecha fechaInicio = ConectorFecha.generarFecha(datosVista.get("fechaInicio"));
        Fecha fechaFin = ConectorFecha.generarFecha(datosVista.get("fechaFin"));

        Date[] periodoFacturacion = {fechaInicio.getFecha().getTime(), fechaFin.getFecha().getTime()};
        try {
            comprobarPeriodoFacturación(periodoFacturacion);
        } catch (RangoFechaIncorrectoException e) {
            return e.getMessage();
        }

        List<Llamada> llamadasPeriodoFacturacion = ((Almacen) datos.get("llamadas")).getListadoRango(periodoFacturacion);
        List<Llamada> llamadasClientePeriodo = new LinkedList<Llamada>();

        Iterator<Llamada> it = llamadasPeriodoFacturacion.iterator();
        while (it.hasNext()) {
            Llamada llamada = it.next();
            String clienteLlamada = llamada.getCliente().toString();
            if (clienteLlamada.equals(nif)) {
                llamadasClientePeriodo.add(llamada);
            }
        }

        Factura factura = (Factura) new Factura()
                .setCliente(new ReferenciaCliente(nif))
                .setTarifa(tarifaCliente)
                .setListadoLlamadas(llamadasClientePeriodo)
                .setFecha(fechaFactura);

        Almacen facturas = (Almacen) datos.get("facturas");
        facturas.setElemento(new ReferenciaFactura(factura.getCodigo()), factura);

        realizarGuardado();
        return "";
    }

    private void comprobarPeriodoFacturación(Date[] rango) throws RangoFechaIncorrectoException {
        if (rango[1].compareTo(rango[0]) < 0) {
            throw new RangoFechaIncorrectoException();
        }
    }
}
