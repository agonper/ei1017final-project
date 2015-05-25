package test;

import api.modelo.types.*;
import es.uji.www.GeneradorDatosINE;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class ClienteEmpresaTest {
    public static GeneradorDatosINE gdine = new GeneradorDatosINE();
    public static Cliente cliente;
    public static String nif;
    public static String nombre;
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
        String provincia = gdine.getProvincia();
        direccion = new Direccion()
                .setDireccion("Calle Ejemplo")
                .setPoblacion(gdine.getPoblacion(provincia))
                .setProvincia(provincia)
                .setCodigoPostal("12345")
                .setPais("España");
        fecha = new FechaGregorianCalendar().setFecha(1,1,2014);
        cliente = factoriaClientes.generarCliente(FactoriaClientes.CLIENTE_EMPRESA);
        factoriaTarifas = new FactoriaTarifas();
        tarifa = factoriaTarifas.generarTarifaBase();
        cliente.setNif(nif)
                .setDireccion(direccion)
                .setNombre(nombre)
                .setTarifa(tarifa)
                .setFecha(fecha);

    }

    @Test
    public void testGetNif() throws Exception {
        assertThat(cliente.getNif(), is(nif));
    }

    @Test
    public void testGetDireccion() throws Exception {
        assertThat(cliente.getDireccion(), is(direccion));
    }

    @Test
    public void testGetTarifa() throws Exception {
        assertThat(cliente.getTarifa(), is(tarifa));
    }

    @Test
    public void testToString() throws Exception {
        assertThat(cliente.toString(), is(nif + ";" + nombre +
        ";" + direccion + ";" + fecha + ";" + tarifa));
    }
}