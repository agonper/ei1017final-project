package api.modelo.storage;

import api.modelo.types.Componente;
import api.vista.graphical.tablas.Tabla;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by alberto on 24/03/15.
 */
public interface Almacen<K extends Referencia, T extends Componente> extends Serializable{
    public List getElemento(K referencia);

    public void setElemento(K referencia, T elemento);

    public void borrarElementos(List<K> referencias);

    public List getListado();

    public List getListadoRango(Date[] rango);

    public Almacen setVista(Tabla tabla);

    public void recargar();
}
