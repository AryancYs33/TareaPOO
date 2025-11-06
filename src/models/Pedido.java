package models;

public class Pedido {
    private final String cliente;
    private final Producto producto;
    private final int cantidad;
    private final double subtotal;
    private final double igv;
    private final double total;
    private final String numeroOrden;

    public Pedido(String cliente, Producto producto, int cantidad,
                  double subtotal, double igv, double total, String numeroOrden) {
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.igv = igv;
        this.total = total;
        this.numeroOrden = numeroOrden;
    }

    public String getCliente() { return cliente; }
    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public double getSubtotal() { return subtotal; }
    public double getIgv() { return igv; }
    public double getTotal() { return total; }
    public String getNumeroOrden() { return numeroOrden; }
}