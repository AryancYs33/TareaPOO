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