package api.vista.graphical.tablas;

import api.modelo.storage.Almacen;

import javax.swing.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by alberto on 13/05/15.
 */
public class Tabla extends JTable {

    public Tabla(ModeloTabla modeloTabla) {
        this.setModel(modeloTabla);
        this.setShowGrid(false);
        this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    public void actualizarVista(List elementos) {
        ModeloTabla modelo = (ModeloTabla) this.getModel();
        modelo.setNewData(elementos);
    }

}
