package api.modelo.types;

/**
 * Created by alberto on 17/03/15.
 */
public class DuracionMinutos implements Duracion{
    private int segundos;
    private int minutos;
    private int horas;

    public DuracionMinutos(int segundos, int minutos, int horas) {
        this.segundos = segundos;
        this.minutos = minutos;
        this.horas = horas;
    }

    @Override
    public float getDuracion() {
        return minutos + horas * 60.0f + segundos / 60.0f;
    }

    @Override
    public String toString() {
        return horas + "h " + minutos + "min " + segundos + "s";
    }
}
