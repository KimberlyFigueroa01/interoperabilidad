package edu.uptc.software.adaptador;

import java.util.Map;

import edu.uptc.software.empresa.ServicioPago;
import edu.uptc.software.proveedor.PaymentProvider;
import edu.uptc.software.proveedor.User;

public class ProveedorPagoAdaptador implements ServicioPago {

    private PaymentProvider externalProvider;
    private String moneda;

    public ProveedorPagoAdaptador(PaymentProvider externalProvider, String moneda) {
        this.externalProvider = externalProvider;
        this.moneda = moneda;
    }

    @Override
    public RespuestaPago procesarPago(String clienteId, long monto) {
        User userExterno = new User(clienteId, "Usuario del sistema");
        
        Map<String, Object> respuesta = externalProvider.executeTransaction(userExterno, monto, moneda);

        String estado = (String) respuesta.get("status");
        String codigoAutorizacion = String.valueOf(respuesta.get("authId"));


        return new RespuestaPago(codigoAutorizacion, estado);
    }



    
    
}
