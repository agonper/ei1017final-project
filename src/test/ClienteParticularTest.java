package test;

import api.modelo.types.*;
import es.uji.www.GeneradorDatosINE;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ClienteParticularTest {
    public static GeneradorDatosINE gdine = new GeneradorDatosINE();
    public static ClienteParticular cliente;
    public static String nif;
    public static String nombre;
    public static String apellido;
    public static Direccion direccion;
    public static Fecha fecha;
    public static Tarifa tarifa;
    public static FactoriaClientes factoriaClientes;
    public static FactoriaTarifas factoriaTarifas;

    @Before
    public void setUp() throws Exception {
        factoriaClientes = new FactoriaClientes();
        nif = gdine.getNIF();
        nombre = gdine.getNombre();
        apellido = gdine.getApellido();
        String provincia = gdine.getProvincia();
        direccion = new Direccion()
                .setDireccion("Calle Ejemplo")
                .setPoblacion(gdine.getPoblacion(provincia))
                .setProvincia(provincia)
                .setCodigoPostal("12345")
                .setPais("España");
        fecha = new FechaGregorianCalendar().setFecha(1, 1, 2014);
        cliente = (ClienteParticular) factoriaClientes.generarCliente(FactoriaClientes.CLIENTE_PARTICULAR);
        factoriaTarifas = new FactoriaTarifas();
        tarifa = factoriaTarifas.generarTarifaBase();
        cliente.setApellidos(apellido)
                .setNif(nif)
                .setDireccion(direccion)
                .setNombre(nombre)
                .setTarifa(tarifa)
                .setFecha(fecha);
    }

    @Test
    public void testToString() throws Exception {
        assertThat(cliente.toString(), is(nif + ";" + nombre +
                ";" + direccion + ";" + fecha + ";" + tarifa + ";" + apellido ));
    }
}