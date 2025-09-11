package mainuvgtienda;
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
                // Aquí asumo que el carrito maneja las cantidades internamente
                // Si necesitas cantidades específicas, se puede ajustar
                if (!p.isDisponible() || p.getUnidadesDisponibles() < 1) {
                    stockSuficiente = false;
                    break;
                }
            }
            
            if (stockSuficiente) {
                // Reducir el stock de los productos
                for (Producto p : carritoUsuario.getItems()) {
                    int nuevoStock = p.getUnidadesDisponibles() - 1;
                    p.setUnidadesDisponibles(nuevoStock);
                    
                    // Si es un kit, también reducir stock de productos internos
                    if (p instanceof Kit) {
                        Kit kit = (Kit) p;
                        for (Producto subP : kit.getProductos()) {
                            int nuevoSubStock = subP.getUnidadesDisponibles() - 1;
                            subP.setUnidadesDisponibles(nuevoSubStock);
                        }
                    }
                }
                
                ordenStatus = "PROCESADA";
                carritoUsuario = new Carrito(); // Reiniciar carrito (vaciar)
            } else {
                ordenStatus = "RECHAZADA";
                // NOTA: Si se rechaza por falta de stock, 
                // no restauramos nada porque no se había reducido aún
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
