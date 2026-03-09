package edu.uptc.software.empresa;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uptc.software.proveedor.PaymentProvider; //Cambio con respecto al punto 1
import edu.uptc.software.proveedor.User;
import com.fasterxml.jackson.core.type.TypeReference;


public class SistemaOrdenes {
    private ServicioPago servicioPago;

    public SistemaOrdenes(ServicioPago servicioPago) {
        this.servicioPago = servicioPago;
    }

    public ServicioPago.RespuestaPago procesarOrden(Cliente cliente, long monto) {
        ServicioPago.RespuestaPago respuesta = servicioPago.procesarPago(cliente.getId(), monto);
        
        try {
            saveTransaction(cliente);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return respuesta;
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
