package workers;

import facade.PedidoFacade;
import models.Pedido;
import observer.EventType;
import observer.Notificador;

import java.util.concurrent.BlockingQueue;

public class InvoiceWorker implements Runnable {
    private final BlockingQueue<Pedido> inQueue;
    private final PedidoFacade facade;
    private final Notificador notificador;

    public InvoiceWorker(BlockingQueue<Pedido> inQueue,
                         PedidoFacade facade,
                         Notificador notificador) {
        this.inQueue = inQueue;
        this.facade = facade;
        this.notificador = notificador;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Pedido pedido = inQueue.take();
                if ("__END__".equals(pedido.getNumeroOrden())) {
                    break;
                }
                String factura = facade.generarFactura(pedido);
                notificador.notificar(EventType.FACTURA_GENERADA, pedido, factura);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception ex) {
            System.out.println("[InvoiceWorker] Error: " + ex.getMessage());
        }
    }
}
