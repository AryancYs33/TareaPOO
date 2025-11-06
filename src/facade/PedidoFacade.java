package facade;

import adapter.FacturaService;
import models.Pedido;
import models.Producto;
import services.PedidoRepository;
import services.StockService;
import services.TaxService;

public class PedidoFacade {
    private final StockService stockService;
    private final TaxService taxService;
    private final PedidoRepository pedidoRepo;
    private final FacturaService facturaService;

    public PedidoFacade(StockService stockService, TaxService taxService,
                        PedidoRepository pedidoRepo, FacturaService facturaService) {
        this.stockService = stockService;
        this.taxService = taxService;
        this.pedidoRepo = pedidoRepo;
        this.facturaService = facturaService;
    }

public String procesarPedido(String cliente, String producto, int cantidad) {
        Producto p = stockService.buscar(producto);
        stockService.validarStock(p, cantidad);
        stockService.reservar(p, cantidad);
        
double subtotal = Math.round(p.getPrecio() * cantidad * 100.0) / 100.0;
        double igv = taxService.calcularIgv(subtotal);
        double total = Math.round((subtotal + igv) * 100.0) / 100.0;
        

        Pedido pedido = new Pedido(cliente, p, cantidad, subtotal, igv, total, null);
        String orden = pedidoRepo.registrar(pedido);
        String factura = facturaService.generarFactura(cliente, producto, total);
        
 return String.format(
            "\n=== COMPROBANTE ===\nCliente: %s\nProducto: %s\nCantidad: %d\nSubtotal: %.2f\nIGV: %.2f\nTotal: %.2f\nOrden: %s\nFactura: %s\n===================",
            cliente, producto, cantidad, subtotal, igv, total, orden, factura
        );
    }
}
        
