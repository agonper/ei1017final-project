package api.modelo.types;

/**
 * Created by alberto on 28/04/15.
 */
public class FactoriaTarifas {
    public static final int TARIFA_TARDES = 0;
    public static final int TARIFA_DOMINGOS = 1;

    public Tarifa generarTarifaBase() {
        return new TarifaBase();
    }

    public Tarifa generarTarifas(int opcion, Tarifa tarifa) {
        switch (opcion) {
            case TARIFA_TARDES:
                return new TarifaTardes(tarifa);
            case TARIFA_DOMINGOS:
                return new TarifaDomingos(tarifa);
        }
        return null;
    }
}
