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