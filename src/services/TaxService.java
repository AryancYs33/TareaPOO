package services;

public class TaxService {
    private static final double IGV = 0.18;
    public double calcularIgv(double subtotal) { return Math.round(subtotal * IGV * 100.0) / 100.0; }
}

