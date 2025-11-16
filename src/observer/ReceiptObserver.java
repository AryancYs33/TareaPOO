package observer;

import models.Pedido;

public class ReceiptObserver implements Observer {
    @Override
    public void onNotify(EventType type, Pedido pedido, String extraInfo) {
        if (type == EventType.FACTURA_GENERADA) {
            String out = String.format(
                "\n----- FACTURA EMITIDA -----" +
                "\nOrden: %s" +
                "\nCliente: %s" +
                "\nProducto: %s" +
                "\nCantidad: %d" +
                "\nSubtotal: %.2f" +
                "\nImpuesto: %.2f" +
                "\nTotal: %.2f" +
                "\nFactura: %s" +
                "\n---------------------------\n",
                pedido.getNumeroOrden(),
                pedido.getCliente(),
                pedido.getProducto().getNombre(),
                pedido.getCantidad(),
                pedido.getSubtotal(),
                pedido.getIgv(),
                pedido.getTotal(),
                extraInfo
            );
            System.out.print(out);
        }
    }
}
