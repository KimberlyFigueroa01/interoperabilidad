package edu.uptc.software.empresa;

import edu.uptc.software.proveedor.PaymentProvider;
import edu.uptc.software.proveedor.User;

public class SistemaOrdenes {
    private PaymentProvider paymentProvider;

    public SistemaOrdenes(PaymentProvider paymentProvider) {
        this.paymentProvider = paymentProvider;
    }

    public void procesarOrden(Cliente cliente, long monto, String moneda) {
        User user = new User(cliente.getId(), cliente.getNombre());
        paymentProvider.executeTransaction(user, monto, moneda);
    }


    
}
