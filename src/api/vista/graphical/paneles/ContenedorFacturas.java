package api.vista.graphical.paneles;

import api.modelo.storage.*;
import api.modelo.types.Factura;
import api.modelo.types.Llamada;
import api.modelo.types.RangoFechaIncorrectoException;
import api.vista.graphical.dialogos.EmitirFactura;
import api.vista.graphical.dialogos.LlamadaNueva;
import api.vista.graphical.tablas.ModeloFacturas;
import api.vista.graphical.tablas.Tabla;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by alberto on 13/05/15.
 */
public class ContenedorFacturas extends Contenedor {
    private Tabla tabla;
    private JRadioButton selectorNif;
    private JRadioButton selectorCodfac;

    public ContenedorFacturas(Map datos) {
        super(datos);
        this.setLayout(new BorderLayout());
        añadirElementos();
    }

    @Override
    public void añadirElementos() {
        setCabecera("Facturas");

        //Panel de contenido
        JPanel contenido = new JPanel(new BorderLayout());
        contenido.setBackground(Color.white);



        //Añadir cinta de entradas
        contenido.add(crearCintaDeEntradas(
                crearCintaDeEntradasSuperior(
                        new EscuchadorBusqueda((Almacen) datos.get("facturas"))),
                crearCintaDeEntradasInferior(
                        new EscuchadorBusquedaFechaNif((Almacen) datos.get("facturas"))
                ), "Emitir", EmitirFactura.class), BorderLayout.NORTH);

        //Añadir tabla
        contenido.add(crearTablaContenido(), BorderLayout.CENTER);


        this.add(contenido, BorderLayout.CENTER);
    }

    @Override
    protected JPanel crearCintaDeEntradasSuperior(ActionListener actionBuscarPor) {

        JPanel campos = new JPanel();
        campos.setLayout(new BoxLayout(campos, BoxLayout.PAGE_AXIS));

        //Búsquedas
        campos.add(Contenedor.crearDivisor("Buscar por"));

        //Selector
        JPanel tipo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tipo.setBackground(Color.white);
        tipo.add(selectorNif = new JRadioButton("NIF"));
        tipo.add(selectorCodfac = new JRadioButton("Nº factura"));
        selectorNif.setSelected(true);
        ButtonGroup gbTipo = new ButtonGroup();
        gbTipo.add(selectorNif);
        gbTipo.add(selectorCodfac);
        campos.add(tipo);

        //Buscar por Nif
        JPanel pnlBusquedaNif = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlBusquedaNif.setBackground(Color.white);
        final JLabel lblBuscar;
        pnlBusquedaNif.add(lblBuscar = new JLabel("Buscar por NIF:"));
        pnlBusquedaNif.add(textoBuscar = new JTextField(12));
        JButton buscarNif;
        pnlBusquedaNif.add(buscarNif = new JButton("Buscar"));
        buscarNif.addActionListener(actionBuscarPor);
        campos.add(pnlBusquedaNif);

        selectorNif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblBuscar.setText("Buscar por NIF:");
            }
        });

        selectorCodfac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblBuscar.setText("Buscar por Nº factura");
            }
        });



        return campos;
    }

    private JScrollPane crearTablaContenido() {
        Almacen facturas = (Almacen) datos.get("facturas");

        tabla = new Tabla(new ModeloFacturas(facturas.getListado()));
        facturas.setVista(tabla);

        JScrollPane scroll = new JScrollPane(tabla,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        return scroll;
    }

    private List facturasCliente(List facturas) {
        List<Factura> facturasCliente = new ArrayList<Factura>();

        Iterator<Factura> it = facturas.iterator();
        while (it.hasNext()) {
            Factura factura = it.next();
            if (factura.getCliente().toString().equals(textoBuscar.getText().toUpperCase())) {
                facturasCliente.add(factura);
            }
        }
        return facturasCliente;
    }


    private class EscuchadorBusqueda implements ActionListener {
        private Almacen datos;

        public EscuchadorBusqueda(Almacen datos) {
            this.datos = datos;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (textoBuscar.getText().length() > 0) {
                if (selectorNif.isSelected()) {
                    List<Factura> facturas = datos.getListado();
                    tabla.actualizarVista(facturasCliente(facturas));
                } else {
                    List listadoFacturas = datos.getElemento(new ReferenciaFactura(textoBuscar.getText().toUpperCase()));
                    if (listadoFacturas.get(0) != null) {
                        tabla.actualizarVista(listadoFacturas);
                    } else {
                        tabla.actualizarVista(new ArrayList());
                    }
                }
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
            else if (textoBuscar.getText().length() > 0 && selectorNif.isSelected()) {
                java.util.List<Factura> facturas = datos.getListadoRango(rango);
                tabla.actualizarVista(facturasCliente(facturas));
                error.setText("");
            } else {
                error.setText("Error, debes indicar el NIF del cliente");
            }
        }
    }
}

