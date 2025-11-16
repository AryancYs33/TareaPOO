package models;

import strategy.ImpuestoStrategy;

public class OrderRequest {
    private final String cliente;
    private final String producto;
    private final int cantidad;
    private final ImpuestoStrategy strategy;

    public OrderRequest(String cliente, String producto, int cantidad, ImpuestoStrategy strategy) {
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.strategy = strategy;
    }

    public String getCliente() { return cliente; }
    public String getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public ImpuestoStrategy getStrategy() { return strategy; }

    public boolean isEndSignal() { return "__END__".equals(cliente); }

    public static OrderRequest endSignal() {
        return new OrderRequest("__END__", "", 0, null);
    }
}
