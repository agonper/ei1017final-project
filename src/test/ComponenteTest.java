package test;

import api.modelo.types.Componente;
import api.modelo.types.Fecha;
import api.modelo.types.FechaGregorianCalendar;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class ComponenteTest {
    Fecha fecha;
    Componente componente;

    @Before
    public void setUp() throws Exception {
        fecha = new FechaGregorianCalendar()
                .setFecha(1, 1, 2014);
        componente = new Componente().setFecha(fecha);
    }

    @Test
    public void testGetFecha() throws Exception {
        assertThat(componente.getFecha(), is(fecha));
    }
}