package edu.uptc.software.proveedor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PaymentProvider {
    
    public Map<String, Object> executeTransaction(User user, long amount, String currency){

        Random random = new Random();
        int authId = random.nextInt(90000) + 10000; // Genera un número aleatorio de 5 dígitos

        Map<String, Object> response = new HashMap<>(); // Crea un mapa para la respuesta

        response.put("status", "success"); // Agrega el estado de la transacción
        response.put("authId", authId); // Agrega el ID de autorización generado
        response.put("timestamp", LocalDateTime.now().toString()); // Agrega la marca de tiempo de la transacción

        System.out.println("Proveedor externo procesando pago...");
        System.out.println("Usuario: " + user.getName());
        System.out.println("Monto: " + amount + " " + currency);
        System.out.println("Transacción exitosa. ID de autorización: " + authId);
        return response; // Devuelve la respuesta
    }
    
}
