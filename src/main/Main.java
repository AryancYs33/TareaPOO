package main;

import adapter.FacturaAdapter;
import facade.PedidoFacade;
import services.PedidoRepository;
import services.StockService;
import services.TaxService;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        PedidoFacade facade = new PedidoFacade(
                new StockService(),
                new TaxService(),
                new PedidoRepository(),
                new FacturaAdapter()
        ); 

Scanner sc = new Scanner(System.in);
        System.out.println("=== Registro de Pedido (Facade + Adapter) ===");
        System.out.print("Cliente: ");
        String cliente = sc.nextLine();
        System.out.print("Producto (laptop/mouse/teclado): ");
        String producto = sc.nextLine();
        System.out.print("Cantidad: ");
        int cantidad = Integer.parseInt(sc.nextLine());
        
try {
            System.out.println(facade.procesarPedido(cliente, producto, cantidad));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
