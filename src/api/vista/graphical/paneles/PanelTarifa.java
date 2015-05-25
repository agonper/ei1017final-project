package api.vista.graphical.paneles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by alberto on 19/05/15.
 */
public class PanelTarifa extends JPanel {

    private JCheckBox tarifaBase;
    private JCheckBox tarifaTardes;
    private JCheckBox tarifaDomingos;

    public PanelTarifa() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        añadirElementos();
    }

    public void añadirElementos() {
        this.add(Contenedor.crearDivisor("Tarifa"));
        JPanel tarifas = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tarifas.add(tarifaBase = new JCheckBox("Tarifa Base"));
        tarifas.add(tarifaTardes = new JCheckBox("Tarifa Tardes"));
        tarifaTardes.setEnabled(false);
        tarifas.add(tarifaDomingos = new JCheckBox("Tarifa Domingos"));
        tarifaDomingos.setEnabled(false);
        tarifaBase.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case ItemEvent.SELECTED:
                        tarifaTardes.setEnabled(true);
                        tarifaDomingos.setEnabled(true);
                        break;
                    case ItemEvent.DESELECTED:
                        tarifaTardes.setEnabled(false);
                        tarifaDomingos.setEnabled(false);
                        break;
                }
            }
        });
        this.add(tarifas);
    }

    public boolean checkTarifaBase() {
        return tarifaBase.isSelected();
    }

    public boolean checkTarifaTardes() {
        return tarifaTardes.isSelected();
    }

    public boolean checkTarifaDomingos() {
        return tarifaDomingos.isSelected();
    }
}
