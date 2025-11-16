package main;

import adapter.FacturaAdapter;
import facade.PedidoFacade;
import models.OrderRequest;
import models.Pedido;
import observer.*;
import services.PedidoRepository;
import services.StockService;
import strategy.ExoneradoStrategy;
import strategy.IGV18Strategy;
import strategy.ImpuestoStrategy;
import workers.InvoiceWorker;
import workers.OrderWorker;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Registro de Pedido ===");
        System.out.println("Seleccione estrategia de impuesto:");
        System.out.println("1) IGV 18%");
        System.out.println("2) Exonerado (0%)");
        System.out.print("Opcion: ");
        String op = sc.nextLine();

        ImpuestoStrategy strategy;
        switch (op) {
            case "2": strategy = new ExoneradoStrategy(); break;
            default:  strategy = new IGV18Strategy();     break;
        }

        PedidoFacade facade = new PedidoFacade(
                new StockService(),
                new PedidoRepository(),
                new FacturaAdapter(),
                strategy
        );

        Notificador notificador = new Notificador();
        // Mostrar stock restante al crear pedido
        notificador.suscribir(new InventarioObserver());
        // Mostrar comprobante limpio al final
        notificador.suscribir(new ReceiptObserver());

        BlockingQueue<OrderRequest> orderQueue = new ArrayBlockingQueue<>(100);
        BlockingQueue<Pedido> invoiceQueue = new ArrayBlockingQueue<>(100);

        Thread orderThread = new Thread(new OrderWorker(orderQueue, invoiceQueue, facade, notificador), "OrderWorker-Thread");
        Thread invoiceThread = new Thread(new InvoiceWorker(invoiceQueue, facade, notificador), "InvoiceWorker-Thread");

        orderThread.start();
        invoiceThread.start();

        boolean seguir = true;
        while (seguir) {
            System.out.print("Cliente: ");
            String cliente = sc.nextLine();
            System.out.print("Producto (laptop/mouse/teclado): ");
            String producto = sc.nextLine();
            System.out.print("Cantidad: ");
            int cantidad = Integer.parseInt(sc.nextLine());

            orderQueue.offer(new OrderRequest(cliente, producto, cantidad, strategy));

            System.out.print("Ingresar otro pedido? (s/n): ");
            seguir = sc.nextLine().trim().equalsIgnoreCase("s");
        }

        orderQueue.offer(OrderRequest.endSignal());

        try {
            orderThread.join();
            invoiceThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("=== Fin del procesamiento concurrente ===");
    }
}
