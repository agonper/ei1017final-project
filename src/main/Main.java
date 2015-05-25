package main;

import api.controlador.*;
import api.modelo.storage.*;
import api.modelo.types.Cliente;
import api.modelo.types.Factura;
import api.modelo.types.Llamada;
import api.vista.graphical.MainWindow;
import api.vista.graphical.dialogos.CambiarTarifa;
import api.vista.graphical.dialogos.ClienteNuevo;
import api.vista.graphical.dialogos.EmitirFactura;
import api.vista.graphical.dialogos.LlamadaNueva;
import api.vista.graphical.escuchadores.BorrarElementos;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by alberto on 17/03/15.
 */
public class Main {

    public static void main(String[] args){
        Main main = new Main();
        final String usuario = main.faseDeLogin();
        final Map<String, Almacen> datos = main.faseDeCarga();
        main.setModeloControlador(datos);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow(datos, usuario).mostrarVentana();
            }
        });
    }

    private String faseDeLogin() {
        return "Usuario";
    }

    private Map faseDeCarga() {
        Map datos = null;
        ConectorAlmacenamiento sistemaDeFicheros = new ConectorAlmacenamientoSF();
        try {
            datos = (Map) sistemaDeFicheros.cargarDatos();
        } catch (FileNotFoundException e) {
            datos = generarAlmacenes();
        }
        return  datos;
    }

    private Map generarAlmacenes() {
        Map<String, Almacen> datos = new HashMap<String, Almacen>();
        datos.put("clientes", new AlmacenMemoria<Referencia, Cliente>());
        datos.put("llamadas", new AlmacenMemoria<Referencia, Llamada>());
        datos.put("facturas", new AlmacenMemoria<Referencia, Factura>());
        return datos;
    }

    private void setModeloControlador(Map datos) {
        Conector controlador = new ConectorCliente();
        controlador.setModelo(datos);
        ClienteNuevo.setControlador(controlador);
        BorrarElementos.setControlador(controlador);

        controlador = new ConectorTarifa();
        controlador.setModelo(datos);
        CambiarTarifa.setControlador(controlador);

        controlador = new ConectorLlamada();
        controlador.setModelo(datos);
        LlamadaNueva.setControlador(controlador);

        controlador = new ConectorFactura();
        controlador.setModelo(datos);
        EmitirFactura.setControlador(controlador);
    }


}