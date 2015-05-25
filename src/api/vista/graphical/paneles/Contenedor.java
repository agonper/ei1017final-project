package api.vista.graphical.paneles;

import api.modelo.storage.Almacen;
import api.modelo.types.Fecha;
import api.modelo.types.FechaGregorianCalendar;
import api.modelo.types.RangoFechaIncorrectoException;
import api.vista.graphical.escuchadores.AbrirDialogo;
import api.vista.graphical.tablas.Tabla;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


/**
 * Created by alberto on 13/05/15.
 */
public abstract class Contenedor extends JPanel {
    protected final Map datos;
    protected Tabla tabla;
    protected JTextField textoBuscar;
    protected PanelFecha fechaInicio;
    protected PanelFecha fechaFin;
    protected JLabel error;

    public Contenedor(Map datos) {
        this.datos = datos;
        this.setBackground(Color.white);
    }

    public abstract void añadirElementos();

    public void setCabecera(String titulo) {
        //Contenedor cabecera
        JPanel cabecera = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Etiqueta cabecera
        JLabel lblCabecera = new JLabel(titulo);
        lblCabecera.setFont(new Font(lblCabecera.getFont().getName(), Font.PLAIN, lblCabecera.getFont().getSize()*3));
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.CENTER;
        cabecera.add(lblCabecera, c);

        this.add(cabecera, BorderLayout.NORTH);
    }

    protected JPanel crearCintaDeEntradas(JPanel superior, JPanel inferior,
                                          String textoBotonNuevo, Class dialogoMostrarNuevo) {
        JPanel entradas = new JPanel(new BorderLayout());
        entradas.setBackground(Color.white);

        JPanel campos = new JPanel();
        campos.setLayout(new BoxLayout(campos, BoxLayout.PAGE_AXIS));

        //Superior
        campos.add(superior);

        //Inferior
        campos.add(inferior);

        //Nuevo
        JButton btnNuevo = new JButton(textoBotonNuevo);
        btnNuevo.addActionListener(new AbrirDialogo(dialogoMostrarNuevo, SwingUtilities.getWindowAncestor(this)));

        entradas.add(campos, BorderLayout.WEST);
        entradas.add(btnNuevo, BorderLayout.EAST);

        return entradas;
    }

    protected JPanel crearCintaDeEntradasSuperior(ActionListener actionBuscarPor) {

        JPanel campos = new JPanel();
        campos.setLayout(new BoxLayout(campos, BoxLayout.PAGE_AXIS));

        //Búsquedas
        campos.add(Contenedor.crearDivisor("Buscar por NIF"));
        JPanel pnlBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlBusqueda.setBackground(Color.white);
        pnlBusqueda.add(new JLabel("Buscar por NIF:"));
        pnlBusqueda.add(textoBuscar = new JTextField(12));
        JButton buscarNif;
        pnlBusqueda.add(buscarNif = new JButton("Buscar"));
        buscarNif.addActionListener(actionBuscarPor);
        campos.add(pnlBusqueda);

        return campos;
    }

    protected JPanel crearCintaDeEntradasInferior(ActionListener actionBuscarPorFecha) {
        JPanel campos = new JPanel();
        campos.setLayout(new BoxLayout(campos, BoxLayout.PAGE_AXIS));

        //Acotar por fecha
        campos.add(Contenedor.crearDivisor("Buscar por fecha"));
        JPanel pnlFechaInicio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlFechaInicio.setBackground(Color.white);
        pnlFechaInicio.add(new JLabel("Fecha inicio:"));
        pnlFechaInicio.add(fechaInicio = new PanelFecha());
        fechaInicio.setBackground(Color.white);
        campos.add(pnlFechaInicio);
        JPanel pnlFechaFin = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlFechaFin.setBackground(Color.white);
        pnlFechaFin.add(new JLabel("Fecha fin:    "));
        pnlFechaFin.add(fechaFin = new PanelFecha());
        fechaFin.setBackground(Color.white);
        campos.add(pnlFechaFin);
        JPanel pnlBtnFecha = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlBtnFecha.add(error = new JLabel());
        error.setForeground(Color.red);
        pnlBtnFecha.setBackground(Color.white);
        JButton buscarFecha;
        pnlBtnFecha.add(buscarFecha = new JButton("Buscar"));
        buscarFecha.addActionListener(actionBuscarPorFecha);
        campos.add(pnlBtnFecha);

        return campos;
    }


    public static JPanel crearDivisor(String nombre) {
        JPanel divisor = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel etiqueta = new JLabel(nombre);
        etiqueta.setFont(new Font(etiqueta.getFont().getName(), Font.BOLD, etiqueta.getFont().getSize()));
        divisor.add(etiqueta);
        return divisor;
    }

    protected class EscuchadorBusquedaFecha implements ActionListener {
        protected Almacen datos;

        public EscuchadorBusquedaFecha(Almacen datos) {
            this.datos = datos;
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

        protected Date parseDate(String fecha) {
            String[] e = fecha.split("/");
            Fecha fechaObjeto = new FechaGregorianCalendar().setFecha(Integer.parseInt(e[0]),
                    Integer.parseInt(e[1]),
                    Integer.parseInt(e[2]));
            return fechaObjeto.getFecha().getTime();
        }

        private void mostrarListado(Date[] rango) throws RangoFechaIncorrectoException {
            if (rango[1].compareTo(rango[0]) < 0) {
                throw new RangoFechaIncorrectoException();
            }
            tabla.actualizarVista(datos.getListadoRango(rango));
            error.setText("");
        }
    }
}
