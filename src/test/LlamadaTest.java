package test;

import api.modelo.storage.Referencia;
import api.modelo.storage.ReferenciaCliente;
import api.modelo.types.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class LlamadaTest {
    public static Llamada llamada;
    public static Referencia cliente;
    public static Duracion duracion;
    public static Fecha fecha;

    @Before
    public void setUp() throws Exception {
        cliente = new ReferenciaCliente("45546");
        duracion = new DuracionMinutos(10, 45, 2);
        fecha = new FechaGregorianCalendar().setFecha(10, 12, 2014).setHora(12, 12);
        llamada = (Llamada) new Llamada().setCliente(cliente)
                .setDuración(duracion)
                .setFecha(fecha);
    }

    @Test
    public void testGetDuracion() throws Exception {
        assertThat(llamada.getDuracion(), is(duracion));
    }

    @Test
    public void testGetCliente() throws Exception {
        assertThat(llamada.getCliente(), is(cliente));
    }

    @Test
    public void testToString() throws Exception {
        assertThat(llamada.toString(), is(llamada.getCodigoLlamada()+ ";" + cliente + ";" +
                fecha + ";" + fecha.getHora() + ";" + duracion));
    }
}