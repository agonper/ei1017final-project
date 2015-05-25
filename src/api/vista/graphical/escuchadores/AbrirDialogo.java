package api.vista.graphical.escuchadores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by alberto on 13/05/15.
 */
public class AbrirDialogo implements ActionListener {

    private Class cla;
    private Window ventana;
    private JDialog dialogo;

    public AbrirDialogo(Class c, Window ventana) {
        this.cla = c;
        this.ventana = ventana;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Constructor cns = cla.getConstructor(Window.class);
            dialogo = (JDialog) cns.newInstance(new Object[]{ventana});
            dialogo.setVisible(true);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException ex) {
            ex.printStackTrace();
            dialogo = null;
        }
    }
}
