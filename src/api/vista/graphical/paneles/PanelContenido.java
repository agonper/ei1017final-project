package api.vista.graphical.paneles;

import api.vista.graphical.FactoriaContenedores;

import javax.swing.*;
import java.awt.*;

/**
 * Created by alberto on 13/05/15.
 */
public class PanelContenido extends JPanel {
    private FactoriaContenedores contenedores;

    public PanelContenido(FactoriaContenedores contenedores) {
        this.contenedores = contenedores;
        this.setLayout(new CardLayout());
        this.setPreferredSize(new Dimension(550, 390));
        añadirContenido();
    }

    private void añadirContenido() {
        this.add(contenedores.obtenerContenedor(FactoriaContenedores.INDEX), FactoriaContenedores.INDEX);
        this.add(contenedores.obtenerContenedor(FactoriaContenedores.CLIENTES), FactoriaContenedores.CLIENTES);
        this.add(contenedores.obtenerContenedor(FactoriaContenedores.LLAMADAS), FactoriaContenedores.LLAMADAS);
        this.add(contenedores.obtenerContenedor(FactoriaContenedores.FACTURAS), FactoriaContenedores.FACTURAS);
    }
}
