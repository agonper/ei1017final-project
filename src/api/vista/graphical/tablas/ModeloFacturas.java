package api.vista.graphical.tablas;

import api.modelo.types.Factura;
import api.modelo.types.Llamada;

import java.util.List;

/**
 * Created by alberto on 20/05/15.
 */
public class ModeloFacturas extends ModeloTabla {
    private static final String COLUMNAS[] = {"Nª factura", "Cliente", "Fecha", "Importe"};

    public ModeloFacturas(List facturas) {
        super(COLUMNAS, facturas);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Factura factura = (Factura) getElementos().get(rowIndex);
        String[] columns = factura.toString().split(";");
        return columns[columnIndex];
    }
}
