package mainuvtienda;
 
// Representa una orden generada a partir de un carrito y un cliente
public class Orden {
    
    
    private String ordenID;
    private String ordenStatus;
    private Cliente comprador;
    private Carrito carritoUsuario;

    public Orden(String ordenID, Cliente comprador, Carrito carritoUsuario) {
        this.ordenID = ordenID;
        this.comprador = comprador;
        this.carritoUsuario = carritoUsuario;
        this.ordenStatus = "PENDIENTE";
    }

    // Procesa la orden dependiendo del pago
    public void procesarOrden(Pago pago) {
        if (pago.getStatusPago()) {
            this.ordenStatus = "PROCESADA";
        } else {
            this.ordenStatus = "RECHAZADA";
        }
    }

    public String getOrdenID() { return ordenID; }
    public String getOrdenStatus() { return ordenStatus; }
    public Cliente getComprador() { return comprador; }
    public Carrito getCarritoUsuario() { return carritoUsuario; }
}

