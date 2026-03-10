package edu.uptc.software;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import edu.uptc.software.adaptador.ProveedorPagoAdaptador;
import edu.uptc.software.empresa.Cliente;
import edu.uptc.software.empresa.ServicioPago;
import edu.uptc.software.empresa.SistemaOrdenes;
import edu.uptc.software.proveedor.PaymentProvider;;

public class SistemaOrdenesTest {

    @Test
    void testProcesarPagoConAdaptador() {
        PaymentProvider provider = new PaymentProvider();

        ServicioPago adaptadorPago = new ProveedorPagoAdaptador(provider, "COP");

        SistemaOrdenes sistema = new SistemaOrdenes(adaptadorPago);

        Cliente cliente = new Cliente("7", "Carlos");

        ServicioPago.RespuestaPago respuesta = sistema.procesarOrden(cliente, 500000);

        assertEquals("success", respuesta.getEstado());
          

    }
    
}
