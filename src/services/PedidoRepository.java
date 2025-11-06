package services;

import models.Pedido;

public class PedidoRepository {
    private int secuencia = 1000;
    public String registrar(Pedido p) { return "ORD-" + (secuencia++); }
}

