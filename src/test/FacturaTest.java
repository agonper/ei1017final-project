package test;

import api.modelo.storage.Referencia;
import api.modelo.storage.ReferenciaCliente;
import api.modelo.types.*;
import es.uji.www.GeneradorDatosINE;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class FacturaTest {
    public static GeneradorDatosINE gdine = new GeneradorDatosINE();
    public static Factura factura;
    public static Referencia cliente;
    public static Tarifa tarifa;
    public static Fecha fecha;
    public static FactoriaTarifas factoria;

    @Before
    public void setUp() throws Exception {
        cliente = new ReferenciaCliente(gdine.getNIF());
        factoria = new FactoriaTarifas();
        tarifa = factoria.generarTarifaBase();
        fecha = new FechaGregorianCalendar().setFecha(2015, 1, 1);
        factura = (Factura) new Factura()
                .setCliente(cliente)
                .setTarifa(tarifa)
                .setListadoLlamadas(new LinkedList<Llamada>())
                .setFecha(fecha);
    }

    @Test
    public void testGetCliente() throws Exception {
        assertThat(factura.getCliente(), is(cliente));
    }

    @Test
    public void testGetImporte() throws Exception {
        assertThat(factura.getImporte(), is(0.0f));
    }

    @Test
    public void testToString() throws Exception {
        assertThat(factura.toString(), is(factura.getCodigo() + ";" + cliente + ";" +
                fecha + ";0.0 €"));
    }
}