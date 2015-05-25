package api.vista.graphical;

import api.vista.graphical.paneles.PanelBotonera;
import api.vista.graphical.paneles.PanelContenido;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Created by alberto on 5/05/15.
 */
public class MainWindow {
    private String usuario;
    private Map datos;

    public MainWindow(Map datos, String usuario) {
        this.usuario = usuario;
        this.datos = datos;
    }

    public void mostrarVentana() {
        setLookAndFeel();
        JFrame ventanaPrincipal = new JFrame("Compañía Telefónica");
        ventanaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        añadirContenido(ventanaPrincipal.getContentPane());
        ventanaPrincipal.pack();
        ventanaPrincipal.setVisible(true);
    }

    private void añadirContenido(Container contendedor) {
        //Gestor de contenido
        FactoriaContenedores containers = new FactoriaContenedores(datos, usuario);
        JPanel gestorPaneles = new PanelContenido(containers);

        contendedor.add(new PanelBotonera(gestorPaneles), BorderLayout.WEST);
        contendedor.add(gestorPaneles, BorderLayout.CENTER);
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
