package api.modelo.types;

/**
 * Created by alberto on 28/04/15.
 */
public class TarifaTardes extends TarifaDecoradora {
    private static final float PRECIO = 0.05f;

    public TarifaTardes(Tarifa tarifa) {
        super(PRECIO, tarifa);
    }

    @Override
    public float getPrecioLlamada(Llamada llamada) {
        Fecha fecha = llamada.getFecha();
        int hora = Integer.parseInt(fecha.getHora().split(":")[0]);
        float[] precios = new float[2];

        precios[0] = getTarifaDecorada().getPrecioLlamada(llamada);

        if (hora >= 16 && hora <=20) {
            precios[1] = llamada.getDuracion().getDuracion() * super.getPrecio();
            return Math.min(precios[0], precios[1]);
        } else {
            return precios[0];
        }
    }

    @Override
    public String toString() {
        return "Tardes de 16:00 a 20:00 - Precio: " + super.getPrecio() + " €/min. " + getTarifaDecorada();
    }
}
