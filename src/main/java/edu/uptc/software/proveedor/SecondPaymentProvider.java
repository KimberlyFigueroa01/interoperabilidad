package edu.uptc.software.proveedor;

public class SecondPaymentProvider {

    public String realizarPago(double valor, String correoElectronico, long celular){
        
        if (celular < 1000000000L) {
            return "Celular inválido";
        }
        
        String nConfirmacion = "Nuevo-" + System.currentTimeMillis() + "-" + (int)(Math.random() * 100);

        System.out.println("Comprobante enviado a: " + correoElectronico);


        return nConfirmacion;


    }
    
}
