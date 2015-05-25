package api.controlador;

import api.modelo.storage.ConectorAlmacenamiento;
import api.modelo.storage.ConectorAlmacenamientoSF;
import api.vista.graphical.dialogos.DialogoDatos;

import java.awt.*;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by alberto on 24/03/15.
 */
public abstract class Conector<T> {
    protected Scanner input;
    protected DialogoDatos vista;
    protected Map datos;

    public Conector() {}

    public Conector(Scanner input) {
        this.input = input;
    }

    public abstract T introducirDatos();

    public void setVista(DialogoDatos vista) {
        this.vista = vista;
    }

    public void setModelo(Map datos) {
        this.datos = datos;
    }

    protected void realizarGuardado() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ConectorAlmacenamiento sistemaDeFicheros = new ConectorAlmacenamientoSF();
                sistemaDeFicheros.guardarDatos(datos);
            }
        }).start();
    }

    public String obtenerDato(String texto) {
        System.out.print(texto + ": ");
        while (!input.hasNextLine());
        return input.nextLine();
    }
}
