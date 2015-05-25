package api.vista.graphical.paneles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by alberto on 19/05/15.
 */
public class PanelBotonesDialogo extends JPanel {
    private ActionListener listener;

    public PanelBotonesDialogo(ActionListener listener) {
        super();
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.listener = listener;
        añadirContenido();
    }

    private void añadirContenido() {
        JButton aceptar;
        this.add(aceptar = new JButton("Crear"));
        aceptar.addActionListener(listener);

        JButton cancelar;
        this.add(cancelar = new JButton("Cancelar"));
        cancelar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton) e.getSource();
                JDialog dialogo = (JDialog) SwingUtilities.getWindowAncestor(btn);
                dialogo.dispose();
            }
        });
    }
}
