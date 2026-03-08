package edu.uptc.software;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {

    Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        Main main = new Main();

        Map<String, Object> response = new HashMap<>();

        response = main.realizarPago();
            System.out.println("Respuesta del pago: " + response);
    }

    public Map<String, Object> realizarPago(){
        System.out.println("Ingrese el id del cliente: ");
        String id = sc.nextLine();
        System.out.println("Ingrese la divisa: ");
        String divisa = sc.nextLine();
        System.out.println("Ingrese el monto a pagar: ");
        Long monto = sc.nextLong();
        Map<String, Object> response = new HashMap<>();
        response.put("id", id);
        response.put("monto", monto);
        response.put("divisa", divisa);
        return response;
    }




}
