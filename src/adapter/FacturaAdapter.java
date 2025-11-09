package adapter;

import legacy.LegacyBillingSystem;

public class FacturaAdapter implements FacturaService {
    private final LegacyBillingSystem legacy = new LegacyBillingSystem();

    @Override
    public String generarFactura(String cliente, String producto, double total) {
        String razonSocial = cliente;
        String ruc = "00000000000";
        return legacy.emitirFactura(razonSocial, ruc, total);
    }
}