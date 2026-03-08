package edu.uptc.software.empresa;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uptc.software.proveedor.PaymentProvider;
import edu.uptc.software.proveedor.User;
import com.fasterxml.jackson.core.type.TypeReference;


public class SistemaOrdenes {
    private PaymentProvider paymentProvider;

    public SistemaOrdenes() {
    }

    public SistemaOrdenes(PaymentProvider paymentProvider) {
        this.paymentProvider = paymentProvider;
    }

    public void procesarOrden(Cliente cliente, long monto, String moneda) {
        User user = new User(cliente.getId(), cliente.getNombre());
        paymentProvider.executeTransaction(user, monto, moneda);
        try{
            saveTransaction(cliente);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void saveTransaction(Cliente cliente) throws Exception {

    ObjectMapper mapper = new ObjectMapper();
    File file = new File("src/main/java/edu/uptc/software/data/clientes.json");

    List<Cliente> clientes = new ArrayList<>();

    if (file.exists() && file.length() > 0) {
        clientes = mapper.readValue(
                file,
                new TypeReference<List<Cliente>>() {}
        );
    }

    clientes.add(cliente);

    mapper.writeValue(file, clientes);
}

    

    
}
