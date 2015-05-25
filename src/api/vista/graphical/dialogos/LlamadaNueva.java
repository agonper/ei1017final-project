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
public class LlamadaNueva extends JDialog implements DialogoDatos{
    private static Conector controlador;

    private JTextField nif;
    private PanelFecha fecha;
    private JTextField hora;
    private JTextField minuto;
    private JTextField duracionHoras;
    private JTextField duracionMinutos;
    private JTextField duracionSegundos;
    private JLabel error;

    private Map<String, String> salida;

    public LlamadaNueva(Window ventana) {
        super(ventana, "Llamada nueva");
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
        this.add(Contenedor.crearDivisor("Fecha y hora"));
        this.add(fecha = new PanelFecha());

        //Hora y minutos
        JPanel fechaYHora = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fechaYHora.add(new JLabel("Hora (24h):"));
        fechaYHora.add(hora = new JTextField(2));
        fechaYHora.add(new JLabel("Minuto:"));
        fechaYHora.add(minuto = new JTextField(2));
        this.add(fechaYHora);

        //Duración
        this.add(Contenedor.crearDivisor("Duración"));
        JPanel duracion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        duracion.add(new JLabel("Horas:"));
        duracion.add(duracionHoras = new JTextField(2));
        duracion.add(new JLabel("minutos"));
        duracion.add(duracionMinutos = new JTextField(2));
        duracion.add(new JLabel("segundos"));
        duracion.add(duracionSegundos = new JTextField(2));
        this.add(duracion);

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
                if (checkText(hora)) salida.put("hora", hora.getText());
                if (checkText(minuto)) salida.put("minuto", minuto.getText());
                if (checkText(duracionHoras)) salida.put("duracionHoras", duracionHoras.getText());
                if (checkText(duracionMinutos)) salida.put("duracionMinutos", duracionMinutos.getText());
                if (checkText(duracionSegundos)) salida.put("duracionSegundos", duracionSegundos.getText());
                salida.put("fecha", fecha.getFecha());

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
