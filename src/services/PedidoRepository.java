package services;

import models.Pedido;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepository {
    private final List<Pedido> pedidos = new ArrayList<>();
    private int secuencia = 1000;

    public String nextOrden() { return "ORD-" + (secuencia++); }
    public void guardar(Pedido pedido) { pedidos.add(pedido); }
    public List<Pedido> listar() { return new ArrayList<>(pedidos); }
    public Pedido buscarPorOrden(String orden) {
        for (Pedido p : pedidos) if (orden != null && orden.equals(p.getNumeroOrden())) return p;
        return null;
    }
}
