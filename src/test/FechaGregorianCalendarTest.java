package test;

import api.modelo.types.Fecha;
import api.modelo.types.FechaGregorianCalendar;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class FechaGregorianCalendarTest {
    public static Fecha fecha;
    public static Calendar calendar;

    @Before
    public void setUp() {
        calendar = new GregorianCalendar(2014, 11, 24);
        fecha = new FechaGregorianCalendar().setFecha(24, 12, 2014);
    }

    @Test
    public void testGetFecha() throws Exception {
        assertThat(fecha.getFecha(), is(calendar));
    }

    @Test
    public void testToString() throws Exception {
        assertThat(fecha.toString(), is("24/12/2014"));
    }
}