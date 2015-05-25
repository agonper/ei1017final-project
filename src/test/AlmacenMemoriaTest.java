package test;

import api.modelo.storage.Almacen;
import api.modelo.storage.AlmacenMemoria;
import api.modelo.storage.Referencia;
import api.modelo.storage.ReferenciaCliente;
import api.modelo.types.Componente;
import api.modelo.types.Fecha;
import api.modelo.types.FechaGregorianCalendar;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class AlmacenMemoriaTest {
    public static Almacen<Referencia, Componente> almacen;

    @Before
    public void setUp() throws Exception {
        almacen = new AlmacenMemoria<Referencia, Componente>();
    }

    @Test
    public void testGetElemento() throws Exception {
        Componente componente = new Componente();
        almacen.setElemento(new ReferenciaCliente("2041"), componente);
        List list = almacen.getElemento(new ReferenciaCliente("2041"));
        assertThat((Componente) list.get(0), is(componente));
    }

    @Test
    public void testBorrarElemento() throws Exception {
        Componente componente = new Componente();
        almacen.setElemento(new ReferenciaCliente("3154"), componente);
        List list = almacen.getElemento(new ReferenciaCliente("3154"));
        assertThat((Componente) list.get(0), is(componente));
        List<Referencia> refs = new LinkedList<Referencia>();
        refs.add(new ReferenciaCliente("3154"));
        almacen.borrarElementos(refs);
        assertNull(almacen.getElemento(new ReferenciaCliente("3154")).get(0));
    }

    @Test
    public void testGetListado() throws Exception {
        assertThat(almacen.getListado().isEmpty(), is(true));
    }

    @Test
    public void testGetListadoRango() throws Exception {
        Componente componente = new Componente().setFecha(new FechaGregorianCalendar().setFecha(10, 2, 2015));
        almacen.setElemento(new ReferenciaCliente("Hola"), componente);
        Fecha inicio = new FechaGregorianCalendar().setFecha(1, 2, 2015);
        Fecha fin = new FechaGregorianCalendar().setFecha(20, 2, 2015);
        Date[] rango = {inicio.getFecha().getTime(), fin.getFecha().getTime()};
        assertNotNull(almacen.getListadoRango(rango));
        assertThat((Componente) almacen.getListadoRango(rango).get(0), is(componente));
    }
}