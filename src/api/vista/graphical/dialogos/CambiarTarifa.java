package api.vista.graphical.dialogos;

import api.controlador.Conector;
import api.controlador.ConectorTarifa;
import api.modelo.storage.Referencia;
import api.vista.graphical.paneles.PanelBotonesDialogo;
import api.vista.graphical.paneles.PanelTarifa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by alberto on 19/05/15.
 */
public class CambiarTarifa extends JDialog implements DialogoDatos {

    private static ConectorTarifa controlador;
    private static List<Referencia> referencias;
    private final Map<String, String> salida;
    private JLabel error;
    private PanelTarifa panelTarifa;

    public CambiarTarifa(Window ventana) {
        super(ventana, "Cambiar Tarifa");
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

    public void añadirContenido() {
        //Tarifas
        this.add(panelTarifa = new PanelTarifa());

        //Zona errores
        JPanel pnlError = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlError.add(error = new JLabel(" "));
        error.setForeground(Color.red);
        this.add(pnlError);

        //Botonera inferior
        this.add(new PanelBotonesDialogo(new AccionAcceptar()));
    }

    @Override
    public Map getSalida() {
        return salida;
    }

    public static void setControlador(Conector conector) {
        controlador = (ConectorTarifa) conector;
    }

    private class AccionAcceptar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!panelTarifa.checkTarifaBase()) {
                error.setText("Error, debes seleccionar mínimo la tarifa base");
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
                if (referencias.isEmpty()) {
                    error.setText("Error, debe seleccionar algún cliente");
                } else {
                    controlador.setClientes(referencias);
                    String respuesta = controlador.introducirDatos();
                    if ("".equals(respuesta)) {
                        JButton btn = (JButton) e.getSource();
                        JDialog dialogo = (JDialog) SwingUtilities.getWindowAncestor(btn);
                        dialogo.dispose();
                    }
                }
            }
        }
    }

    public static void setElementos(List referencias) {
        CambiarTarifa.referencias = referencias;
    }
}
