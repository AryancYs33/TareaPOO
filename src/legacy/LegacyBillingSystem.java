package legacy;

public class LegacyBillingSystem {
     public String emitirFactura(String razonSocial, String ruc, double monto) {
        return "LEGACY-INVOICE{" + razonSocial + "|" + ruc + "|TOTAL=" + monto + "}";
    }
}
