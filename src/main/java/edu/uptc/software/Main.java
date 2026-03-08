package edu.uptc.software;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import edu.uptc.software.empresa.Cliente;
import edu.uptc.software.empresa.SistemaOrdenes;
import edu.uptc.software.proveedor.PaymentProvider;


public class Main {

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        
        PaymentProvider provider = new PaymentProvider();
        SistemaOrdenes sistemaOrdenes = new SistemaOrdenes(provider);

        Main main = new Main();

        Map<String, Object> response = new HashMap<>();

        response = main.realizarPago();
        
        sistemaOrdenes.procesarOrden((Cliente) response.get("cliente"), (Long) response.get("monto"), (String) response.get("divisa"));
        
    }

    public Map<String, Object> realizarPago(){
        
        Cliente cliente = registroCliente();

        System.out.println("Ingrese la divisa: ");
        String divisa = sc.nextLine();
        System.out.println("Ingrese el monto a pagar: ");
        Long monto = sc.nextLong();
        Map<String, Object> response = new HashMap<>();

        response.put("cliente", cliente);
        response.put("monto", monto);
        response.put("divisa", divisa);
        return response;
    }

    public Cliente registroCliente(){
        System.out.println("Ingrese el id del cliente: ");
        String id = sc.nextLine();
        System.out.println("Ingrese el nombre del cliente: ");
        String nombre = sc.nextLine();

        Cliente cliente = new Cliente(id, nombre);
        return cliente;
    }




}
