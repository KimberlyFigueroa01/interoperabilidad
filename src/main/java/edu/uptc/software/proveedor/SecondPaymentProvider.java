package edu.uptc.software.proveedor;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SecondPaymentProvider {

    
    public String realizarPago(double valor, String correoElectronico, long celular, User user, String currency) {
    
    
    if (celular < 1000000000L) {
        return "Celular inválido";
    }

    String nConfirmacion = "Nuevo-" + System.currentTimeMillis() + "-" + (int)(Math.random() * 100);
    Map<String, Object> response = new HashMap<>();

    
    if (!currency.equalsIgnoreCase("USD") && !currency.equalsIgnoreCase("EUR") && !currency.equalsIgnoreCase("COP")) {
        
        return "Divisa no soportada";
    } else {
        
        response.put("status", "exitoso");
        response.put("authId", nConfirmacion);
        response.put("timestamp", LocalDateTime.now().toString());

        try {
            saveTransaction(response);
            BigDecimal amount = BigDecimal.valueOf(valor);
            saveTransactionData(user, nConfirmacion, amount, currency);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Comprobante enviado a: " + correoElectronico);

        return nConfirmacion; 
    }
}
    public void saveTransaction(Map<String, Object> transactionData) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src/main/java/edu/uptc/software/data/pagos.json");
        List<Map<String, Object>> transactions = new ArrayList<>();

        if (file.exists() && file.length() > 0) {
            transactions = mapper.readValue(file, new TypeReference<List<Map<String, Object>>>() {});
        }
        transactions.add(transactionData);
        mapper.writeValue(file, transactions);
    }

    public void saveTransactionData(User user, String authId, BigDecimal amount, String currency) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src/main/java/edu/uptc/software/data/registros.json");

        Map<String, Object> transactionData = new HashMap<>();
        transactionData.put("userId", user.getId());
        transactionData.put("authId", authId);
        transactionData.put("amount", amount);
        transactionData.put("currency", currency);

        List<Map<String, Object>> transactions = new ArrayList<>();
        if (file.exists() && file.length() > 0) {
            transactions = mapper.readValue(file, new TypeReference<List<Map<String, Object>>>() {});
        }
        transactions.add(transactionData);
        mapper.writeValue(file, transactions);
    }
}