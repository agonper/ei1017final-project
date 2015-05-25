package api.vista.graphical.widgets;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by alberto on 13/05/15.
 */
public class FormatoFecha extends JFormattedTextField.AbstractFormatter {

    private String patron = "dd/MM/yyyy";
    private SimpleDateFormat dateFormater = new SimpleDateFormat(patron);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormater.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormater.format(cal.getTime());
        }

        return "";
    }
}
