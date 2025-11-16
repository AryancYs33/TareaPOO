package observer;

import models.Pedido;

public class InventarioObserver implements Observer {
    @Override
    public void onNotify(EventType type, Pedido pedido, String extraInfo) {
        if (type == EventType.PEDIDO_CREADO) {
            System.out.printf("Stock actualizado: %s -> quedan %d unidades.%n",
                    pedido.getProducto().getNombre(),
                    pedido.getProducto().getStock());
        }
    }
}