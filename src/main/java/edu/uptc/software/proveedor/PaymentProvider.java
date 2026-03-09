package edu.uptc.software.proveedor;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PaymentProvider {
    
    public Map<String, Object> executeTransaction(User user, BigDecimal amount, String currency){

        Random random = new Random();
        int authId = random.nextInt(90000) + 10000; // Genera un número aleatorio de 5 dígitos

        Map<String, Object> response = new HashMap<>(); // Crea un mapa para la respuesta

        if (!currency.equalsIgnoreCase("USD") && !currency.equalsIgnoreCase("EUR")&& !currency.equalsIgnoreCase("COP")) {
            response.put("status", "error");
            response.put("message", "Divisa no soportada. Solo se aceptan USD, EUR y COP.");
            return response;
        } else{
            response.put("status", "success"); // Agrega el estado de la transacción
            response.put("authId", authId); // Agrega el ID de autorización generado
            response.put("timestamp", LocalDateTime.now().toString()); // Agrega la marca de tiempo de la transacción


            System.out.println("Proveedor externo procesando pago..."); 
            System.out.println("Transacción exitosa. ID de autorización: " + authId);
            
          
          try {
                saveTransaction(response);
                saveTransactionData(user, authId, amount, currency);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return response;
    }

    public void saveTransaction(Map<String, Object> transactionData) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src/main/java/edu/uptc/software/data/pagos.json");

        List<Map<String, Object>> transactions = new ArrayList<>();

        // Si el archivo ya tiene datos, leerlos
        if (file.exists() && file.length() > 0) {
            transactions = mapper.readValue(
                    file,
                    new TypeReference<List<Map<String, Object>>>() {}
            );
        }

        // Agregar nueva transacción
        transactions.add(transactionData);

        // Guardar todo nuevamente
        mapper.writeValue(file, transactions);
    }

    public void saveTransactionData(User user, int authId, BigDecimal amount, String currency) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src\\main\\java\\edu\\uptc\\software\\data\\registros.json");

        Map<String, Object> transactionData = new HashMap<>();

        transactionData.put("userId", user.getId());
        transactionData.put("authId", authId);
        transactionData.put("amount", amount);
        transactionData.put("currency", currency);

        List<Map<String, Object>> transactions = new ArrayList<>();
        
        // Si el archivo ya tiene datos, leerlos
        if (file.exists() && file.length() > 0) {
            transactions = mapper.readValue(
                    file,
                    new TypeReference<List<Map<String, Object>>>() {}
            );
        }

        transactions.add(transactionData);

        mapper.writeValue(file, transactions);
    }
    
}
