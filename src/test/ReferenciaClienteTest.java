package test;

import api.modelo.storage.Referencia;
import api.modelo.storage.ReferenciaCliente;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class ReferenciaClienteTest {
    public static Referencia referencia;

    @Before
    public void setUp() throws Exception {
        referencia = new ReferenciaCliente("20154");
    }

    @Test
    public void testToString() throws Exception {
        assertThat(referencia.toString(), is("20154"));
    }
}