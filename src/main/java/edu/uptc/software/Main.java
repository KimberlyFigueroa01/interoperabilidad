package edu.uptc.software;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import edu.uptc.software.adaptador.ProveedorPagoAdaptador;
import edu.uptc.software.adaptador.SegundoAdaptadorProveedor;
import edu.uptc.software.empresa.Cliente;
import edu.uptc.software.empresa.ServicioPago;
import edu.uptc.software.empresa.SistemaOrdenes;
import edu.uptc.software.proveedor.PaymentProvider;
import edu.uptc.software.proveedor.SecondPaymentProvider;


public class Main {

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        

        Main main = new Main();

        Map<String, Object> datos = main.realizarPago();
        Cliente cliente = (Cliente) datos.get("cliente");
        Long monto = (Long) datos.get("monto");
        String divisa = (String) datos.get("divisa");

        //Proveedor Original
        PaymentProvider provider = new PaymentProvider();
        ServicioPago adaptador = new ProveedorPagoAdaptador(provider, divisa);

        // Segundo proveedor
        SecondPaymentProvider segundoProveedor = new SecondPaymentProvider();
        ServicioPago segundoAdaptador = new SegundoAdaptadorProveedor(segundoProveedor, divisa);

        SistemaOrdenes sistemaOrdenes = new SistemaOrdenes(segundoAdaptador);
        ServicioPago.RespuestaPago respuesta = sistemaOrdenes.procesarOrden(cliente, monto);

        System.out.println("\n RECIBO DE PAGO");
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Monto: " + monto + " " + divisa);
        System.out.println("Estado: " + respuesta.getEstado());
        System.out.println("Código Autorización: " + respuesta.getCodigoAutorizacion());
    }

    public Map<String, Object> realizarPago(){
        System.out.println("\n[Bienvenido al Sistema de Órdenes]");
        Cliente cliente = registroCliente();
        System.out.println("Ingrese la divisa (ej: COP, USD, EUR): ");
        String divisa = sc.nextLine();
        System.out.println("Ingrese el monto a pagar: ");
        Long monto = sc.nextLong();
        sc.nextLine();

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
