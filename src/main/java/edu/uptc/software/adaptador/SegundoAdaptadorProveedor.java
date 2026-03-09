package edu.uptc.software.adaptador;

import edu.uptc.software.empresa.ServicioPago;
import edu.uptc.software.proveedor.SecondPaymentProvider;

public class SegundoAdaptadorProveedor implements ServicioPago {

    private SecondPaymentProvider secondPaymentProvider;

    public SegundoAdaptadorProveedor(SecondPaymentProvider secondPaymentProvider) {
        this.secondPaymentProvider = secondPaymentProvider;
    }

    @Override
    public RespuestaPago procesarPago(String clienteId, long monto) {
        
        double nuevoMonto = (double) monto;

        String emailEjemplo = clienteId + "abcd@gmail.com";
        long telefono = 3118672345L;

        String resultado = secondPaymentProvider.realizarPago(nuevoMonto, emailEjemplo, telefono);


        if (resultado.startsWith("ERROR")) {
            return new RespuestaPago(null, "error");
        } else {
            return new RespuestaPago(resultado, "success");
        }
    }
    


}
