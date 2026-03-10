package edu.uptc.software.adaptador;

import edu.uptc.software.empresa.ServicioPago;
import edu.uptc.software.proveedor.SecondPaymentProvider;
import edu.uptc.software.proveedor.User;

public class SegundoAdaptadorProveedor implements ServicioPago {

    private SecondPaymentProvider secondPaymentProvider;

  
private String divisaDelSistema; 

    
    public SegundoAdaptadorProveedor(SecondPaymentProvider provider, String moneda) {
        this.secondPaymentProvider = provider;
        this.divisaDelSistema = moneda;
    }

    @Override
    public RespuestaPago procesarPago(String clienteId, long monto) {
        
        double valor = (double) monto;
        String correo = clienteId + "abcd@correo.com";
        long celular = 3101234567L;
        
        
        User userExterno = new User(clienteId, "Usuario Sistema");

        
        String resultado = secondPaymentProvider.realizarPago(valor, correo, celular, userExterno, this.divisaDelSistema);

        
        if (resultado.equals("Celular inválido") || resultado.equals("Divisa no soportada")) {
            return new RespuestaPago(null, "error");
        }
        
       
        return new RespuestaPago(resultado, "exitoso");
    }
    


}
