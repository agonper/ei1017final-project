package api.vista.graphical.escuchadores;

import api.controlador.Conector;
import api.controlador.ConectorCliente;
import api.modelo.storage.Referencia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by alberto on 19/05/15.
 */
public class BorrarElementos implements ActionListener {
    private List<Referencia> referencias;
    private static ConectorCliente conector;

    public BorrarElementos(List referencias) {
        this.referencias = referencias;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!referencias.isEmpty()) {
            conector.borrarElementos(referencias);
        }
    }

    public static void setControlador(Conector conector) {
        BorrarElementos.conector = (ConectorCliente) conector;
    }
}
