package api.modelo.types;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by alberto on 3/03/15.
 */
public class FechaGregorianCalendar implements Fecha{
    private GregorianCalendar fecha;

    public FechaGregorianCalendar(){

    }

    public Calendar getFecha() {
        return fecha;
    }

    @Override
    public String getHora() {
        Date fechaHora = fecha.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(fechaHora);
    }

    @Override
    public String getDiaSemana() {
        Date fechaDia = fecha.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("u");
        return dateFormat.format(fechaDia);
    }

    @Override
    public Fecha setFecha(int dia, int mes, int año) {
        this.fecha = new GregorianCalendar(año, mes-1, dia);
        return this;
    }

    @Override
    public Fecha setHora(int hora, int minuto) {
        fecha.set(GregorianCalendar.HOUR_OF_DAY, hora);
        fecha.set(GregorianCalendar.MINUTE, minuto);
        return this;
    }


    @Override
    public String toString() {
        Date fechaAlmacenada = fecha.getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(fechaAlmacenada);
    }
}
