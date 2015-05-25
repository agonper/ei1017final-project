package api.vista.graphical.paneles;

import api.vista.graphical.FactoriaContenedores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by alberto on 13/05/15.
 */
public class PanelBotonera extends JPanel {
    private EscuchadorBotonera escuchador;

    public PanelBotonera(JPanel panelIntercambiable) {
        this.setLayout(new GridLayout(3,1,5,5));
        this.setBackground(Color.white);
        escuchador = new EscuchadorBotonera(panelIntercambiable);
        añadirContenido();
    }

    private void añadirContenido() {
        //Atributos de los botones laterales
        AtributosBoton[] atributosBotones = {
                new AtributosBoton("C", FactoriaContenedores.CLIENTES),
                new AtributosBoton("Ll", FactoriaContenedores.LLAMADAS),
                new AtributosBoton("F", FactoriaContenedores.FACTURAS)};
        for (AtributosBoton atributos : atributosBotones) {
            this.add(crearBoton(atributos));
        }
    }

    private Component crearBoton(AtributosBoton atributos) {
        //Atributos
        JButton boton = new JButton(atributos.etiqueta);
        boton.setActionCommand(atributos.id);
        boton.addActionListener(escuchador);

        //Estilo
        boton.setPreferredSize(new Dimension(130, 130));
        boton.setBackground(Color.BLUE);
        boton.setForeground(Color.white);
        boton.setFont(new Font(boton.getFont().getName(), Font.PLAIN, boton.getFont().getSize() * 4));
        return boton;
    }

    private class AtributosBoton {
        public String etiqueta;
        public String id;

        public AtributosBoton(String etiqueta, String id) {
            this.etiqueta = etiqueta;
            this.id = id;
        }
    }

    private class EscuchadorBotonera implements ActionListener {
        private JPanel panelIntercambiable;
        private JButton ultimoPulsado;

        public EscuchadorBotonera(JPanel panelIntercambiable) {
            this.panelIntercambiable = panelIntercambiable;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            String id = boton.getActionCommand();

            CardLayout cl = (CardLayout) panelIntercambiable.getLayout();

            if (boton.equals(ultimoPulsado)) {
                boton.setBackground(Color.blue);
                cl.show(panelIntercambiable, FactoriaContenedores.INDEX);
                ultimoPulsado = null;
            } else {
                boton.setBackground(Color.gray);
                cl.show(panelIntercambiable, id);
                if (ultimoPulsado != null) {
                    ultimoPulsado.setBackground(Color.BLUE);
                }
                ultimoPulsado = boton;
            }
        }
    }
}
