package models;

public class Producto {
    private final String nombre;
    private int stock;
    private final double precio;

    public Producto(String nombre, int stock, double precio) {
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }
    
 public String getNombre() { return nombre; }
    public int getStock() { return stock; }
    public double getPrecio() { return precio; }

    public void disminuirStock(int cantidad) { this.stock -= cantidad; }
}
