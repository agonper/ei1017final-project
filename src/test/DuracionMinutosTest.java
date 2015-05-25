package test;

import api.modelo.types.Duracion;
import api.modelo.types.DuracionMinutos;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class DuracionMinutosTest {
    public static Duracion duracion;

    @Before
    public void setUp() throws Exception {
        duracion = new DuracionMinutos(30, 45, 2);
    }

    @Test
    public void testGetDuracion() throws Exception {
        assertThat(duracion.getDuracion(), is(165.5f));
    }

    @Test
    public void testToString() throws Exception {
        assertThat(duracion.toString(), is("2h 45min 30s"));
    }
}