package edu.uptc.software.adaptador;

import edu.uptc.software.empresa.ServicioPago;
import edu.uptc.software.proveedor.SecondPaymentProvider;
import edu.uptc.software.proveedor.User;

public class SegundoAdaptadorProveedor implements ServicioPago {

    private SecondPaymentProvider secondPaymentProvider;

    // En tu clase SecondPaymentAdapter
private String divisaDelSistema; // Variable para guardar la moneda que viene del Main

    // El constructor recibe la moneda para que no sea siempre "COP"
    public SegundoAdaptadorProveedor(SecondPaymentProvider provider, String moneda) {
        this.secondPaymentProvider = provider;
        this.divisaDelSistema = moneda;
    }

    @Override
    public RespuestaPago procesarPago(String clienteId, long monto) {
        // 1. Preparamos los datos
        double valor = (double) monto;
        String correo = clienteId + "abcd@correo.com";
        long celular = 3101234567L;
        
        // 2. Usamos el usuario y la moneda real del sistema
        User userExterno = new User(clienteId, "Usuario Sistema");

        // 3. Llamamos al método con la moneda que viene del Main
        String resultado = secondPaymentProvider.realizarPago(valor, correo, celular, userExterno, this.divisaDelSistema);

        // 4. VALIDACIÓN DE ERRORES: Capturamos ambos posibles fallos
        if (resultado.equals("Celular inválido") || resultado.equals("Divisa no soportada")) {
            return new RespuestaPago(null, "error");
        }
        
        // Si no es ninguno de esos, entonces es el código de confirmación exitoso
        return new RespuestaPago(resultado, "exitoso");
    }
    


}
