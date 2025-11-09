package main;

import adapter.FacturaAdapter;
import facade.PedidoFacade;
import services.PedidoRepository;
import services.StockService;
import strategy.ExoneradoStrategy;
import strategy.IGV18Strategy;
import strategy.ImpuestoStrategy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Seleccione estrategia de impuesto:");
System.out.println("1) IGV 18%");
System.out.println("2) Exonerado (0%)");
System.out.print("Opcion: ");
String op = sc.nextLine();

ImpuestoStrategy strategy;
switch (op) {
    case "2":
        strategy = new ExoneradoStrategy();
        break;
    default:
        strategy = new IGV18Strategy();
        break;
}

PedidoFacade facade = new PedidoFacade(
        new StockService(),
        new PedidoRepository(),
        new FacturaAdapter(),
        strategy
);


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