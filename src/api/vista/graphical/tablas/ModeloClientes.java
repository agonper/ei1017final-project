package api.vista.graphical.tablas;

import api.modelo.types.Cliente;

import java.util.List;

/**
 * Created by alberto on 13/05/15.
 */
public class ModeloClientes extends ModeloTabla {
    private static final String COLUMNAS[] = {"NIF", "Nombre", "Apellidos", "Direccion", "Fecha", "Tarifa/s"};

    public ModeloClientes(List clientes) {
        super(COLUMNAS, clientes);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = (Cliente) getElementos().get(rowIndex);
        String[] columns = cliente.toString().split(";");
        if (columnIndex == 2) {
            if (columns.length == 5) {
                return "";
            } else {
                return columns[columns.length - 1];
            }
        } if (columnIndex > 2) {
            return columns[columnIndex - 1];
        } else {
            return columns[columnIndex];
        }

    }
}
