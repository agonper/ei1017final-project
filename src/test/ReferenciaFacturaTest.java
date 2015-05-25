package test;

import api.modelo.storage.Referencia;
import api.modelo.storage.ReferenciaFactura;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class ReferenciaFacturaTest {
    public static Referencia referencia;

    @Before
    public void setUp() {
        referencia = new ReferenciaFactura("2");
    }

    @Test
    public void testToString() throws Exception {
        assertThat(referencia.toString(), is("2"));
    }
}