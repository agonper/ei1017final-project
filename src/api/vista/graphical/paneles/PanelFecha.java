package api.vista.graphical.paneles;

import api.vista.graphical.widgets.FormatoFecha;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

/**
 * Created by alberto on 19/05/15.
 */
public class PanelFecha extends JPanel {
    private JDatePickerImpl fecha;

    public PanelFecha() {
        super();
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        añadirElementos();
    }

    private void añadirElementos() {
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Hoy");
        p.put("text.month", "Mes");
        p.put("text.year", "Año");
        JDatePanelImpl dateContainer = new JDatePanelImpl(model, p);
        this.add(fecha = new JDatePickerImpl(dateContainer, new FormatoFecha()));
        this.add(new JLabel(" (Vacío fecha de hoy)"));
    }

    public String getFecha() {
        return fecha.getModel().getDay() + "/" + (fecha.getModel().getMonth()+1) + "/" + fecha.getModel().getYear();
    }


}
