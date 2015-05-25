package api.modelo.types;

/**
 * Created by alberto on 28/04/15.
 */
public class TarifaDomingos extends TarifaDecoradora {
    private static final float PRECIO = 0.0f;

    public TarifaDomingos(Tarifa tarifa) {
        super(PRECIO, tarifa);
    }

    @Override
    public float getPrecioLlamada(Llamada llamada) {
        Fecha fecha = llamada.getFecha();
        int dia = Integer.parseInt(fecha.getDiaSemana());
        float[] precios = new float[2];

        precios[0] = getTarifaDecorada().getPrecioLlamada(llamada);

        if (dia == 7) {
            precios[1] = llamada.getDuracion().getDuracion() * super.getPrecio();
            return Math.min(precios[0], precios[1]);
        } else {
            return precios[0];
        }
    }

    @Override
    public String toString() {
        return "Domingos gratis - Precio: " + super.getPrecio() + " €/min. " + getTarifaDecorada();
    }
}
