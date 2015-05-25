package api.modelo.types;

/**
 * Created by alberto on 21/05/15.
 */
public abstract class TarifaDecoradora extends Tarifa{
    private Tarifa tarifa;

    public TarifaDecoradora(float precio, Tarifa tarifa) {
        super(precio);
        this.tarifa = tarifa;
    }

    public Tarifa getTarifaDecorada() {
        return tarifa;
    }


}
