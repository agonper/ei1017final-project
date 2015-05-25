package api.modelo.storage;

import api.modelo.types.Componente;
import api.vista.graphical.tablas.Tabla;

import java.util.*;

/**
 * Created by alberto on 24/03/15.
 */
public class AlmacenMemoria<K extends Referencia, T extends Componente> implements Almacen<K, T> {
    private Map<String, T> datos;
    private transient Tabla vista;

    public AlmacenMemoria() {
        datos = new HashMap<String, T>();
    }

    public AlmacenMemoria(Map<String, T> datos) {
        this.datos = datos;
    }

    @Override
    public List getElemento(K referencia) {
        List<T> listado = new ArrayList<T>();
        listado.add(datos.get(referencia.toString()));
        return listado;
    }

    @Override
    public void setElemento(K referencia, T elemento){
        datos.put(referencia.toString(), elemento);
        if (vista != null) {
            recargar();
        }
    }

    @Override
    public void borrarElementos(List<K> referencias) {
        for (K referencia : referencias) {
            datos.remove(referencia.toString());
        }
        recargar();
    }

    @Override
    public List getListado() {
        List<T> listado = new ArrayList<T>();
        Set<String> referencias = datos.keySet();
        Iterator<String> referenciaIterator = referencias.iterator();
        while (referenciaIterator.hasNext()){
            listado.add(datos.get(referenciaIterator.next()));
        }
        return listado;
    }

    @Override
    public List getListadoRango(Date[] rango) {
        List<T> listadoRango = new ArrayList<T>();
        List<T> componentes = getListado();
        Iterator<T> it = componentes.iterator();
        while (it.hasNext()) {
            T componente = it.next();
            Date fechaLlamada = componente.getFecha().getFecha().getTime();
            if (fechaLlamada.compareTo(rango[0]) >= 0 &&
                    fechaLlamada.compareTo(rango[1]) <= 0) {
                listadoRango.add(componente);
            }
        }
        return listadoRango;
    }

    @Override
    public Almacen setVista(Tabla tabla) {
        this.vista = tabla;
        return this;
    }

    @Override
    public void recargar() {
        if (vista != null) {
            vista.actualizarVista(getListado());
        }
    }
}
