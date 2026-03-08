package edu.uptc.software.empresa;

public interface  ServicioPago {

    RespuestaPago procesarPago(String clienteId, long monto);

       public class RespuestaPago{
        private String estado;
        private String codigoAutorizacion;

        public RespuestaPago(String codigoAutorizacion, String estado) {
            this.codigoAutorizacion = codigoAutorizacion;
            this.estado = estado;
        }

        public String getEstado() {
            return estado;
        }

        public String getCodigoAutorizacion() {
            return codigoAutorizacion;
        }


       }
    
}
