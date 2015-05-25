package test;

import api.modelo.storage.ReferenciaCliente;
import api.modelo.types.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class TarifaTest {
    public static Tarifa tarifa;
    public static Cliente cliente;
    public static FactoriaClientes factoriaClientes;
    public static FactoriaTarifas factoriaTarifas;

    @Before
    public void setUp() throws Exception {
        factoriaClientes = new FactoriaClientes();
        cliente = factoriaClientes.generarCliente(FactoriaClientes.CLIENTE_EMPRESA);
        factoriaTarifas = new FactoriaTarifas();
        tarifa = factoriaTarifas.generarTarifaBase();
        cliente.setTarifa(tarifa);
    }

    @Test
    public void testGetTarifa() throws Exception {
        assertThat(tarifa.getPrecio(), is(0.15f));
    }

    @Test
    public void testToString() throws Exception {
        assertThat(tarifa.toString(), is("Tarifa base - Precio: 0.15 €/min"));
    }

    @Test
    public void testTardesToString() throws Exception {
        tarifa = factoriaTarifas.generarTarifas(FactoriaTarifas.TARIFA_TARDES, tarifa);
        assertThat(tarifa.toString(), is("Tardes de 16:00 a 20:00 - Precio: 0.05 €/min. Tarifa base - Precio: 0.15 €/min"));
    }

    @Test
    public void testDomingosToString() throws Exception {
        tarifa = factoriaTarifas.generarTarifas(FactoriaTarifas.TARIFA_DOMINGOS, tarifa);
        assertThat(tarifa.toString(), is("Domingos gratis - Precio: 0.0 €/min. Tarifa base - Precio: 0.15 €/min"));
    }

    @Test
    public void testDecorador() throws Exception {
        cliente.setNif("E");
        Llamada llamada = (Llamada) new Llamada()
                .setCliente(new ReferenciaCliente(cliente.getNif()))
                .setDuración(new DuracionMinutos(0,1,0))
                .setFecha((Fecha) new FechaGregorianCalendar()
                        .setFecha(5,4,2015)
                        .setHora(17,25));
        assertThat(tarifa.getPrecioLlamada(llamada), is(0.15f));
        tarifa = factoriaTarifas.generarTarifas(FactoriaTarifas.TARIFA_TARDES, tarifa);
        assertThat(tarifa.getPrecioLlamada(llamada), is(0.05f));
        tarifa = factoriaTarifas.generarTarifas(FactoriaTarifas.TARIFA_DOMINGOS, tarifa);
        assertThat(tarifa.getPrecioLlamada(llamada), is(0.0f));
    }
}