package api.vista.graphical.dialogos;

import api.controlador.Conector;
import api.controlador.ConectorCliente;
import api.vista.graphical.paneles.Contenedor;
import api.vista.graphical.paneles.PanelBotonesDialogo;
import api.vista.graphical.paneles.PanelFecha;
import api.vista.graphical.paneles.PanelTarifa;
import api.vista.graphical.widgets.FormatoFecha;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by alberto on 13/05/15.
 */
public class ClienteNuevo extends JDialog implements DialogoDatos {
    private static Conector controlador;

    private JRadioButton particular;
    private JRadioButton empresa;
    private JTextField nif;
    private JTextField nombre;
    private JTextField apellidos;
    private JTextField via;
    private JTextField numero;
    private JTextField poblacion;
    private JTextField provincia;
    private JTextField codpost;
    private JTextField pais;
    private PanelFecha fecha;
    private PanelTarifa panelTarifa;
    private JLabel error;

    private Map<String, String> salida;

    public ClienteNuevo(Window ventana) {
        super(ventana, "Cliente nuevo");
        salida = new HashMap<String, String>();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setBackground(Color.white);
        añadirContenido();
        this.pack();
        if (controlador != null) {
            controlador.setVista(this);
        }
    }

    private void añadirContenido() {
        //Tipo
        this.add(Contenedor.crearDivisor("Tipo"));
        JPanel tipo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tipo.add(particular = new JRadioButton("Particular"));
        particular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apellidos.setEnabled(true);
            }
        });
        tipo.add(empresa = new JRadioButton("Empresa"));
        empresa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apellidos.setEnabled(false);
            }
        });
        empresa.setSelected(true);

        ButtonGroup gbTipo = new ButtonGroup();
        gbTipo.add(particular);
        gbTipo.add(empresa);
        this.add(tipo);

        //Datos personales
        this.add(Contenedor.crearDivisor("Datos"));
        JPanel datosPersonales = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datosPersonales.add(new JLabel("NIF:"));
        datosPersonales.add(nif = new JTextField(8));
        datosPersonales.add(new JLabel("Nombre:"));
        datosPersonales.add(nombre = new JTextField(10));
        datosPersonales.add(new JLabel("Apellidos:"));
        datosPersonales.add(apellidos = new JTextField(15));
        apellidos.setEnabled(false);
        this.add(datosPersonales);

        //Dirección
        this.add(Contenedor.crearDivisor("Dirección"));
        JPanel calleNumPoblacion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        calleNumPoblacion.add(new JLabel("Vía:"));
        calleNumPoblacion.add(via = new JTextField(20));
        calleNumPoblacion.add(new JLabel("Numero:"));
        calleNumPoblacion.add(numero = new JTextField(4));
        calleNumPoblacion.add(new JLabel("Población:"));
        calleNumPoblacion.add(poblacion = new JTextField(12));
        this.add(calleNumPoblacion);

        JPanel provinciaCPPais = new JPanel(new FlowLayout(FlowLayout.LEFT));
        provinciaCPPais.add(new JLabel("Provincia:"));
        provinciaCPPais.add(provincia = new JTextField(10));
        provinciaCPPais.add(new JLabel("Codigo Postal:"));
        provinciaCPPais.add(codpost = new JTextField(6));
        provinciaCPPais.add(new JLabel("Pais:"));
        provinciaCPPais.add(pais = new JTextField(8));
        this.add(provinciaCPPais);

        //Fecha
        this.add(Contenedor.crearDivisor("Fecha de alta"));
        this.add(fecha = new PanelFecha());

        //Tarifa
        this.add(panelTarifa = new PanelTarifa());

        //Zona errores
        JPanel pnlError = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlError.add(error = new JLabel(" "));
        error.setForeground(Color.red);
        this.add(pnlError);

        //Botonera inferior
        this.add(new PanelBotonesDialogo(new AccionAceptar()));

    }

    public Map getSalida() {
        return salida;
    }

    public static void setControlador(Conector conector) {
        controlador = conector;
    }

    private class AccionAceptar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isParticular()) {
                salida.put("tipo", "particular");
            } else {
                salida.put("tipo", "empresa");
            }
            try {
                if (checkText(nif)) salida.put("nif", nif.getText());
                if (checkText(nombre)) salida.put("nombre", nombre.getText());
                if (checkText(via)) salida.put("via", via.getText());
                if (checkText(numero)) salida.put("numero", numero.getText());
                if (checkText(poblacion)) salida.put("poblacion", poblacion.getText());
                if (checkText(provincia)) salida.put("provincia", provincia.getText());
                if (checkText(codpost)) salida.put("codigopostal", codpost.getText());
                if (checkText(pais)) salida.put("pais", pais.getText());
                salida.put("fecha", fecha.getFecha());
                if (!panelTarifa.checkTarifaBase()) {
                    throw new IllegalArgumentException();
                } else {
                    StringBuilder tarifa = new StringBuilder();
                    tarifa.append("tarifaBase");
                    if (panelTarifa.checkTarifaTardes()) {
                        tarifa.append(",tarifaTardes");
                    }
                    if (panelTarifa.checkTarifaDomingos()) {
                        tarifa.append(",tarifaDomingos");
                    }
                    salida.put("tarifa", tarifa.toString());
                }
                if (isParticular()) {
                    if (checkText(apellidos)) salida.put("apellidos", apellidos.getText());
                }
                String errorText = (String) controlador.introducirDatos();
                if (errorText != "") {
                    error.setText(errorText);
                } else {
                    JButton btn = (JButton) e.getSource();
                    JDialog dialogo = (JDialog) SwingUtilities.getWindowAncestor(btn);
                    dialogo.dispose();
                }
            } catch (IllegalArgumentException ex) {
                error.setText("Error, falta algún campo por completar");
            }


        }

        private boolean isParticular() {
            if (particular.isSelected()) {
                return true;
            }
            return false;
        }

        private boolean checkText(JTextField textField) throws IllegalArgumentException {
            if (textField.getText().length() == 0) {
                throw new IllegalArgumentException();
            }
            return true;
        }
    }

}
