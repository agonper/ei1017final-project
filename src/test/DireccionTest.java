package test;

import api.modelo.types.Direccion;
import es.uji.www.GeneradorDatosINE;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DireccionTest {
    public static GeneradorDatosINE gdine = new GeneradorDatosINE();
    public static Direccion direccion;
    public static String poblacion;
    public static String provincia;

    @Before
    public void setUp() throws Exception {
        provincia = gdine.getProvincia();
        poblacion = gdine.getPoblacion(provincia);
        direccion = new Direccion()
                .setDireccion("Calle Ejemplo")
                .setPoblacion(poblacion)
                .setProvincia(provincia)
                .setCodigoPostal("12345")
                .setPais("España");
    }

    @Test
    public void testToString() throws Exception {
        assertThat(direccion.toString(), is("Calle Ejemplo. " + poblacion + ", " + provincia + ". 12345, España"));
    }
}