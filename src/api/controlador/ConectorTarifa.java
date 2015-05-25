package api.controlador;

import api.modelo.storage.Almacen;
import api.modelo.storage.Referencia;
import api.modelo.types.*;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by alberto on 24/03/15.
 */
public class ConectorTarifa extends Conector {

    private List<Referencia> clientes;

    public ConectorTarifa() {

    }

    public ConectorTarifa(Scanner input) {
        super(input);
    }

    @Override
    public String introducirDatos() {
        Map<String, String> datosVista = super.vista.getSalida();
        Almacen datosClientes = (Almacen) datos.get("clientes");

        Tarifa tarifa = generarTarifa(datosVista.get("tarifa"));
        for (Referencia referencia : clientes) {
            Cliente cliente = (Cliente) datosClientes.getElemento(referencia).get(0);
            cliente.setTarifa(tarifa);
        }
        datosClientes.recargar();

        realizarGuardado();
        return "";
    }

    public Conector setClientes(List clientes) {
         this.clientes = clientes;
        return this;
    }

    public static Tarifa generarTarifa(String opciones) {
        String[] tarifas = opciones.split(",");
        FactoriaTarifas factoriaTarifa = new FactoriaTarifas();
        Tarifa tarifa = factoriaTarifa.generarTarifaBase();
        if (tarifas.length == 3) {
            tarifa = factoriaTarifa.generarTarifas(FactoriaTarifas.TARIFA_TARDES, tarifa);
            tarifa = factoriaTarifa.generarTarifas(FactoriaTarifas.TARIFA_DOMINGOS, tarifa);
        } else if (tarifas.length > 1) {
            if ("tarifaTardes".equals(tarifas[1])) {
                tarifa = factoriaTarifa.generarTarifas(FactoriaTarifas.TARIFA_TARDES, tarifa);
            } else {
                tarifa = factoriaTarifa.generarTarifas(FactoriaTarifas.TARIFA_DOMINGOS, tarifa);
            }
        }
        return tarifa;
    }
}
