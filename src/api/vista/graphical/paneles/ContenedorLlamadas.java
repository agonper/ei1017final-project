package api.vista.graphical.paneles;

import api.modelo.storage.Almacen;
import api.modelo.storage.Referencia;
import api.modelo.storage.ReferenciaCliente;
import api.modelo.storage.ReferenciaLlamada;
import api.modelo.types.Llamada;
import api.modelo.types.RangoFechaIncorrectoException;
import api.vista.graphical.dialogos.LlamadaNueva;
import api.vista.graphical.tablas.ModeloLlamadas;
import api.vista.graphical.tablas.Tabla;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by alberto on 13/05/15.
 *
 * Panel encargado de realizar la gestión de las llamadas
 */
public class ContenedorLlamadas extends Contenedor {
    private Tabla tabla;

    public ContenedorLlamadas(Map datos) {
        super(datos);
        this.setLayout(new BorderLayout());
        añadirElementos();
    }

    @Override
    public void añadirElementos() {
        setCabecera("Llamadas");

        //Panel de contenido
        JPanel contenido = new JPanel(new BorderLayout());
        contenido.setBackground(Color.white);

        //Añadir cinta de entradas
        contenido.add(crearCintaDeEntradas(
                crearCintaDeEntradasSuperior(
                new EscuchadorBusquedaNif((Almacen) datos.get("llamadas"))),
                crearCintaDeEntradasInferior(
                        new EscuchadorBusquedaFechaNif((Almacen) datos.get("llamadas"))
                ), "Nueva", LlamadaNueva.class), BorderLayout.NORTH);

        //Añadir tabla
        contenido.add(crearTablaContenido(), BorderLayout.CENTER);


        this.add(contenido, BorderLayout.CENTER);
    }

    private JScrollPane crearTablaContenido() {
        Almacen llamadas = (Almacen) datos.get("llamadas");

        tabla = new Tabla(new ModeloLlamadas(llamadas.getListado()));
        llamadas.setVista(tabla);

        JScrollPane scroll = new JScrollPane(tabla,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        return scroll;
    }

    private List llamadasCliente(List llamadas) {
        List<Llamada> llamadasCliente = new ArrayList<Llamada>();

        Iterator<Llamada> it = llamadas.iterator();
        while (it.hasNext()) {
            Llamada llamada = it.next();
            if (llamada.getCliente().toString().equals(textoBuscar.getText().toUpperCase())) {
                llamadasCliente.add(llamada);
            }
        }
        return llamadasCliente;
    }

    private class EscuchadorBusquedaNif implements ActionListener {
        private Almacen datos;

        public EscuchadorBusquedaNif(Almacen datos) {
            this.datos = datos;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (textoBuscar.getText().length() > 0) {
                List<Llamada> llamadas = datos.getListado();
                tabla.actualizarVista(llamadasCliente(llamadas));
            } else {
                tabla.actualizarVista(datos.getListado());
            }
        }
    }

    private class EscuchadorBusquedaFechaNif extends EscuchadorBusquedaFecha {
        public EscuchadorBusquedaFechaNif(Almacen datos) {
            super(datos);
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            Date inicio = parseDate(fechaInicio.getFecha());
            Date fin = parseDate(fechaFin.getFecha());
            Date[] rango = {inicio, fin};
            try {
                mostrarListado(rango);
            } catch (RangoFechaIncorrectoException e) {
                error.setText(e.getMessage());
            }
        }

        private void mostrarListado(Date[] rango) throws RangoFechaIncorrectoException {
            if (rango[1].compareTo(rango[0]) < 0) {
                throw new RangoFechaIncorrectoException();
            }
            else if (textoBuscar.getText().length() > 0) {
                List<Llamada> llamadas = datos.getListadoRango(rango);
                tabla.actualizarVista(llamadasCliente(llamadas));
                error.setText("");
            } else {
                error.setText("Error, debes indicar el NIF del cliente");
            }
        }
    }
}
