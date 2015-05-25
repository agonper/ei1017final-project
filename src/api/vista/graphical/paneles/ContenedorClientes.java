package api.vista.graphical.paneles;


import api.modelo.storage.Almacen;
import api.modelo.storage.Referencia;
import api.modelo.storage.ReferenciaCliente;
import api.modelo.types.Fecha;
import api.modelo.types.FechaGregorianCalendar;
import api.modelo.types.RangoFechaIncorrectoException;
import api.vista.graphical.dialogos.CambiarTarifa;
import api.vista.graphical.dialogos.ClienteNuevo;
import api.vista.graphical.dialogos.LlamadaNueva;
import api.vista.graphical.escuchadores.AbrirDialogo;
import api.vista.graphical.escuchadores.BorrarElementos;
import api.vista.graphical.tablas.ModeloClientes;
import api.vista.graphical.tablas.Tabla;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;


/**
 * Created by alberto on 13/05/15.
 */
public class ContenedorClientes extends Contenedor {

    private List<Referencia> elementosSeleccionado;

    public ContenedorClientes(Map datos) {
        super(datos);
        elementosSeleccionado = new LinkedList<Referencia>();
        this.setLayout(new BorderLayout());
        añadirElementos();
    }

    @Override
    public void añadirElementos() {
        //Interfaz para mostrar un listado de clientes
        setCabecera("Clientes");

        //Panel de contenido
        JPanel contenido = new JPanel(new BorderLayout());
        contenido.setBackground(Color.white);

        //Añadir cinta de entradas
        contenido.add(crearCintaDeEntradas(
                crearCintaDeEntradasSuperior(
                        new EscuchadorBusquedaNif((Almacen) datos.get("clientes"))),
                crearCintaDeEntradasInferior(
                        new EscuchadorBusquedaFecha((Almacen) datos.get("clientes"))
                ), "Nuevo", ClienteNuevo.class), BorderLayout.NORTH);

        //Añadir tabla
        contenido.add(crearTablaContenido(), BorderLayout.CENTER);

        //Añadir cinta de opciones
        contenido.add(crearCintaDeOpciones(), BorderLayout.SOUTH);


        this.add(contenido, BorderLayout.CENTER);
    }

    private JScrollPane crearTablaContenido() {
        Almacen clientes = (Almacen) datos.get("clientes");


        tabla = new Tabla(new ModeloClientes(clientes.getListado()));
        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                elementosSeleccionado.clear();
                int[] filas = tabla.getSelectedRows();
                for (int fila : filas) {
                    elementosSeleccionado.add(new ReferenciaCliente((String) tabla.getValueAt(fila, 0)));
                }
            }
        });
        clientes.setVista(tabla);

        JScrollPane scroll = new JScrollPane(tabla,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        return scroll;
    }

    private JPanel crearCintaDeOpciones() {
        JPanel panelOpciones = new JPanel(new FlowLayout());
        panelOpciones.setBackground(Color.white);
        JButton borrarElemento;
        panelOpciones.add(borrarElemento = new JButton("Borrar elementos seleccionados"));
        borrarElemento.addActionListener(new BorrarElementos(elementosSeleccionado));
        JButton cambiarTarifa;
        panelOpciones.add(cambiarTarifa = new JButton("Cambiar tarifa"));
        CambiarTarifa.setElementos(elementosSeleccionado);
        cambiarTarifa.addActionListener(new AbrirDialogo(CambiarTarifa.class, SwingUtilities.getWindowAncestor(this)));
        return panelOpciones;
    }

    private class EscuchadorBusquedaNif implements ActionListener {
        private Almacen datos;

        public EscuchadorBusquedaNif(Almacen datos) {
            this.datos = datos;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (textoBuscar.getText().length() > 0) {
                List listadoClientes = datos.getElemento(new ReferenciaCliente(textoBuscar.getText().toUpperCase()));
                if (listadoClientes.get(0) != null) {
                    tabla.actualizarVista(listadoClientes);
                } else {
                    tabla.actualizarVista(new ArrayList());
                }
            } else {
                tabla.actualizarVista(datos.getListado());
            }
        }
    }
}
