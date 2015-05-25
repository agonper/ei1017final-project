package api.vista.graphical.dialogos;

import api.controlador.Conector;
import api.vista.graphical.paneles.Contenedor;
import api.vista.graphical.paneles.PanelBotonesDialogo;
import api.vista.graphical.paneles.PanelFecha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alberto on 20/05/15.
 */
public class EmitirFactura extends JDialog implements DialogoDatos {
    private static Conector controlador;

    private JTextField nif;
    private PanelFecha fecha;
    private PanelFecha fechaInicio;
    private PanelFecha fechaFin;
    private JLabel error;

    private Map<String, String> salida;

    public EmitirFactura(Window ventana) {
        super(ventana, "Emitir Factura");
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
        //Nif
        this.add(Contenedor.crearDivisor("Cliente"));
        JPanel datosPersonales = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datosPersonales.add(new JLabel("NIF:"));
        datosPersonales.add(nif = new JTextField(8));
        this.add(datosPersonales);

        //Fecha y hora
        this.add(Contenedor.crearDivisor("Fecha de emisión"));
        this.add(fecha = new PanelFecha());

        //Periodo de facturación
        this.add(Contenedor.crearDivisor("Periodo de facturación"));
        JPanel panelInicio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInicio.add(new JLabel("Fecha inicio:"));
        panelInicio.add(fechaInicio = new PanelFecha());
        this.add(panelInicio);
        JPanel panelFin = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFin.add(new JLabel("Fecha fin:"));
        panelFin.add(fechaFin = new PanelFecha());
        this.add(panelFin);

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
            try {
                if (checkText(nif)) salida.put("nif", nif.getText());
                salida.put("fecha", fecha.getFecha());
                salida.put("fechaInicio", fechaInicio.getFecha());
                salida.put("fechaFin", fechaFin.getFecha());

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

        private boolean checkText(JTextField textField) throws IllegalArgumentException {
            if (textField.getText().length() == 0) {
                throw new IllegalArgumentException();
            }
            return true;
        }
    }
}
