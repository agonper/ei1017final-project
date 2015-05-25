package api.modelo.storage;

import api.modelo.types.Factura;
import api.modelo.types.Llamada;

import java.io.*;

/**
 * Created by alberto on 10/04/15.
 */
public class ConectorAlmacenamientoSF<Map> implements ConectorAlmacenamiento<Map> {
    @Override
    public Map cargarDatos() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("company.bin");
        ObjectInputStream ois = null;
        Map datos = null;
        try {
            ois = new ObjectInputStream(fis);
            try {
                datos = (Map) ois.readObject();
                Llamada.setAutoId(ois.readLong());
                Factura.setAutoId(ois.readInt());
            } finally {
                ois.close();
            }
        } catch (IOException e) {
            System.out.println("El tipo de fichero no se corresponde");
        } catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada");
        }
        return datos;
    }

    @Override
    public void guardarDatos(Map datos) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("company.bin");
            try {
                oos = new ObjectOutputStream(fos);
                oos.writeObject(datos);
                oos.writeLong(Llamada.getAutoId());
                oos.writeInt(Factura.getAutoId());
            } finally {
                oos.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Imposible guardar los datos: fichero no accesible");
        } catch (IOException e) {
            System.out.println("Imposible guardar los datos: fichero en uso");
        }
    }
}
