package strategy;

public class IGV18Strategy implements ImpuestoStrategy {
    @Override
    public double calcular(double subtotal) {
        return Math.round(subtotal * 0.18 * 100.0) / 100.0;
    }
} 