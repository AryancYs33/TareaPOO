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