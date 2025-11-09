package strategy;

public interface ImpuestoStrategy {
    double calcular(double subtotal);
    default String nombre() { return getClass().getSimpleName(); }
}
