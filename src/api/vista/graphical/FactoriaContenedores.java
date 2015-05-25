package api.vista.graphical;

import api.vista.graphical.paneles.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alberto on 7/05/15.
 *
 * Factoría de contenedores pricipales
 *
 */
public class FactoriaContenedores {
    private Map<String, Contenedor> contenedores;
    public static final String INDEX = "index";
    public static final String CLIENTES = "clientes";
    public static final String LLAMADAS = "llamadas";
    public static final String FACTURAS = "facturas";


    public FactoriaContenedores(Map datos, String usuario) {
        contenedores = new HashMap<String, Contenedor>();
        contenedores.put(INDEX, new ContenedorInicial(datos, usuario));
        contenedores.put(CLIENTES, new ContenedorClientes(datos));
        contenedores.put(LLAMADAS, new ContenedorLlamadas(datos));
        contenedores.put(FACTURAS, new ContenedorFacturas(datos));
    }

    //Método user-friendly para obtener los paneles
    public Container obtenerContenedor(String id) {
        return contenedores.get(id);
    }
}
