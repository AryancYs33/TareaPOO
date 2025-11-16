package workers;

import facade.PedidoFacade;
import models.OrderRequest;
import models.Pedido;
import observer.EventType;
import observer.Notificador;

import java.util.concurrent.BlockingQueue;

public class OrderWorker implements Runnable {
    private final BlockingQueue<OrderRequest> inQueue;
    private final BlockingQueue<Pedido> outQueue;
    private final PedidoFacade facade;
    private final Notificador notificador;

    public OrderWorker(BlockingQueue<OrderRequest> inQueue,
                       BlockingQueue<Pedido> outQueue,
                       PedidoFacade facade,
                       Notificador notificador) {
        this.inQueue = inQueue;
        this.outQueue = outQueue;
        this.facade = facade;
        this.notificador = notificador;
    }

    @Override
    public void run() {
        try {
            while (true) {
                OrderRequest req = inQueue.take();
                if (req.isEndSignal()) {
                    outQueue.put(new Pedido("__END__", null, 0, 0, 0, 0, "__END__"));
                    break;
                }
                facade.setImpuestoStrategy(req.getStrategy());
                Pedido pedido = facade.crearPedido(req.getCliente(), req.getProducto(), req.getCantidad());
                notificador.notificar(EventType.PEDIDO_CREADO, pedido, null);
                outQueue.put(pedido);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception ex) {
            System.out.println("[OrderWorker] Error: " + ex.getMessage());
        }
    }
}