package api.vista.graphical.tablas;

import api.modelo.types.Llamada;

import java.util.List;

/**
 * Created by alberto on 20/05/15.
 */
public class ModeloLlamadas extends ModeloTabla {
    private static final String COLUMNAS[] = {"Nª llamada", "Cliente", "Fecha", "Hora", "Duracion"};

    public ModeloLlamadas(List llamadas) {
        super(COLUMNAS, llamadas);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Llamada llamada = (Llamada) getElementos().get(rowIndex);
        String[] columns = llamada.toString().split(";");
        return columns[columnIndex];
    }
}
