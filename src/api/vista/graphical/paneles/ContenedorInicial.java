package api.vista.graphical.paneles;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Created by alberto on 13/05/15.
 *
 * Clase que genera el panel inicial
 *
 */

public class ContenedorInicial extends Contenedor{
    private String usuario;

    public ContenedorInicial(Map datos, String usuario) {
        super(datos);
        this.usuario = usuario;
        this.setLayout(new GridBagLayout());
        añadirElementos();
    }

    @Override
    public void añadirElementos() {
        // Contenido inicial a mostrar
        GridBagConstraints c = new GridBagConstraints();

        // Texto inicial panel contenido
        JTextArea bienvenida = new JTextArea("Hola " + usuario);
        bienvenida.setBorder(BorderFactory.createEmptyBorder());
        bienvenida.setEditable(false);
        bienvenida.setFont(new Font(bienvenida.getFont().getName(), Font.PLAIN, bienvenida.getFont().getSize()*4));
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.CENTER;
        this.add(bienvenida, c);

        //Separador
        JTextArea separador = new JTextArea("");
        separador.setEditable(false);
        separador.setBorder(BorderFactory.createEmptyBorder());
        c.gridy = 1;
        this.add(separador, c);

        // Texto ayuda panel contenido
        JTextArea ayuda = new JTextArea("Elige una opción del panel lateral\npara empezar a realizar gestiones");
        ayuda.setBorder(BorderFactory.createEmptyBorder());
        ayuda.setEditable(false);
        ayuda.setFont(new Font(ayuda.getFont().getName(), Font.PLAIN, ayuda.getFont().getSize()*2));
        c.gridy = 2;
        c.fill = GridBagConstraints.PAGE_END;
        this.add(ayuda, c);
    }
}
