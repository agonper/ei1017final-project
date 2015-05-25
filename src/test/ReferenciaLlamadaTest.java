package test;

import api.modelo.storage.Referencia;
import api.modelo.storage.ReferenciaCliente;
import api.modelo.storage.ReferenciaLlamada;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class ReferenciaLlamadaTest {
    public static Referencia referencia;

    @Before
    public void setUp() throws Exception {
        referencia = new ReferenciaLlamada(new ReferenciaCliente("2041"), 4);
    }

    @Test
    public void testToString() throws Exception {
        assertThat(referencia.toString(), is("2041 4"));
    }
}