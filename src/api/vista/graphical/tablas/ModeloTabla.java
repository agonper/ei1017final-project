package api.vista.graphical.tablas;

import api.modelo.types.Cliente;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by alberto on 20/05/15.
 */
public abstract class ModeloTabla extends AbstractTableModel {
    private final String columnas[];
    private List elementos;

    public ModeloTabla(String[] columnas, List elementos) {
        this.columnas = columnas;
        this.elementos = elementos;
    }

    @Override
    public int getRowCount() {
        return elementos.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    public void setNewData(List elementos) {
        this.elementos = elementos;
        fireTableDataChanged();
    }

    protected List getElementos() {
        return elementos;
    }
}
