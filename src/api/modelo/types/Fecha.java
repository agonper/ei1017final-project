package api.modelo.types;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by alberto on 17/03/15.
 */
public interface Fecha extends Serializable{
    public Calendar getFecha();

    public String getHora();

    public String getDiaSemana();

    public Fecha setFecha(int dia, int mes, int año);

    public Fecha setHora(int hora, int minuto);

    public String toString();
}
