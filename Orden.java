import java.util.ArrayList;

public class Orden {
    private String ordenID;         
    private String ordenStatus;     
    private Cliente comprador;      
    private Carrito carritoUsuario; 
    
    // Constructor vacío 
    public Orden() {
        this.ordenStatus = "PENDIENTE"; // Estado inicial
    }
    
    // Método para procesar orden con pago y cliente (según UML)
    public void procesarOrden(Pago pago, Cliente cliente) {
        // Establecer el status del pago
        pago.setStatusPago();
        
        if (pago.getStatusPago()) {
            // Validar que hay stock suficiente para todos los productos
            boolean stockSuficiente = true;
            for (Producto p : carritoUsuario.getItems()) {
                if (p instanceof Kit) {
                    Kit k = (Kit) p;
                    if (k.getUnidadesDisponibles() < 1) {
                        stockSuficiente = false;
                        break;
                    }
                } else {
                    if (!p.isDisponible() || p.getUnidadesDisponibles() < 1) {
                         stockSuficiente = false;
                        break;
                    }
                }
            }
            if (stockSuficiente) {
                for (Producto p : carritoUsuario.getItems()) {
                    if (p instanceof Kit) {
                        Kit k = (Kit) p;
                        k.reducirStockKit(1);
                    } else {
                        p.reducirStock(1);
                    }
                }
                ordenStatus = "PROCESADA";
                // OJO: NO se vacía el carritoUsuario aquí.
                // Sistema se encarga de vaciar el carrito del cliente
                // y la orden conserva una copia del carrito.
            } else {
                 ordenStatus = "RECHAZADA";
            }
        } else {
            ordenStatus = "RECHAZADA";
        }
    }
    // Setters 
    public void setOrdenID(String ordenID) {
        this.ordenID = ordenID;
    }
    
    public void setOrdenStatus(String status) {
        this.ordenStatus = status;
    }
    
    public void setComprador(Cliente cliente) {
        this.comprador = cliente;
    }
    
    public void setCarritoUsuario(Carrito carrito) {
        this.carritoUsuario = carrito;
    }
    
    // Getters
    public String getOrdenID() { 
        return ordenID; 
    }
    
    public String getOrdenStatus() { 
        return ordenStatus; 
    }
    
    public Cliente getComprador() { 
        return comprador; 
    }
    
    public Carrito getCarritoUsuario() { 
        return carritoUsuario; 
    }
}


