package facade;

import adapter.FacturaService;
import models.Pedido;
import models.Producto;
import services.PedidoRepository;
import services.StockService;
import strategy.ImpuestoStrategy;

public class PedidoFacade {
    private final StockService stockService;
    private final PedidoRepository pedidoRepo;
    private final FacturaService facturaService;
    private ImpuestoStrategy impuestoStrategy;

    public PedidoFacade(StockService stockService,
                        PedidoRepository pedidoRepo,
                        FacturaService facturaService,
                        ImpuestoStrategy impuestoStrategy) {
        this.stockService = stockService;
        this.pedidoRepo = pedidoRepo;
        this.facturaService = facturaService;
        this.impuestoStrategy = impuestoStrategy;
    }

    public void setImpuestoStrategy(ImpuestoStrategy impuestoStrategy) {
        this.impuestoStrategy = impuestoStrategy;
    }

    public Pedido crearPedido(String cliente, String producto, int cantidad) {
        Producto p = stockService.buscar(producto);
        stockService.validarStock(p, cantidad);
        stockService.reservar(p, cantidad);

        double subtotal = Math.round(p.getPrecio() * cantidad * 100.0) / 100.0;
        double igv = impuestoStrategy.calcular(subtotal);
        double total = Math.round((subtotal + igv) * 100.0) / 100.0;

        String orden = pedidoRepo.nextOrden();
        Pedido pedido = new Pedido(cliente, p, cantidad, subtotal, igv, total, orden);
        pedidoRepo.guardar(pedido);
        return pedido;
    }

    public String generarFactura(Pedido pedido) {
        return facturaService.generarFactura(pedido.getCliente(),
                pedido.getProducto().getNombre(),
                pedido.getTotal());
    }
}
