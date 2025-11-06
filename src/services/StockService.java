package services;

import models.Producto;
import java.util.HashMap;
import java.util.Map;

public class StockService {
    private final Map<String, Producto> inventario = new HashMap<>();

    public StockService() {
        inventario.put("laptop",   new Producto("laptop",   5,  2500.0));
        inventario.put("mouse",    new Producto("mouse",    20,   45.0));
        inventario.put("teclado",  new Producto("teclado",  15,   80.0));
    }
    

    public Producto buscar(String nombreProducto) {
        return inventario.get(nombreProducto.toLowerCase());
    }

    public void validarStock(Producto p, int cantidad) {
        if (p == null) throw new IllegalArgumentException("Producto no existe");
        if (cantidad <= 0) throw new IllegalArgumentException("La cantidad debe ser positiva");
        if (p.getStock() < cantidad) throw new IllegalArgumentException("Stock insuficiente para " + p.getNombre());
    }

    public void reservar(Producto p, int cantidad) { p.disminuirStock(cantidad); }
}
