import java.util.ArrayList;

public class Carrito {
    private Producto[] items;        
    private int totalAPagar;         
    
    // Constructor vacío (según UML)
    public Carrito() {
        this.items = new Producto[0]; // Inicializar array vacío
        this.totalAPagar = 0;
    }
    
    // Agregar item 
    public void agregarItem(Producto producto) {
        // Requisito 2: Asegurarse de que el usuario no escoja más productos de los disponibles
        // Requisito 3: Restricciones de stock, artículos inexistentes
        if (producto != null && producto.isDisponible() && producto.getUnidadesDisponibles() > 0) {
            // Verificar que no se agregue el mismo producto si ya no hay stock para más
            int vecesEnCarrito = contarProductoEnCarrito(producto);
            
            if (vecesEnCarrito < producto.getUnidadesDisponibles()) {
                // Crear nuevo array con un elemento más
                Producto[] nuevosItems = new Producto[items.length + 1];
                
                // Copiar elementos existentes
                for (int i = 0; i < items.length; i++) {
                    nuevosItems[i] = items[i];
                }
                
                // Agregar nuevo producto
                nuevosItems[items.length] = producto;
                items = nuevosItems;
                
                // Actualizar total
                calcularTotal();
            }
            // Si no se puede agregar (sin stock), no hace nada (restricción aplicada)
        }
    }
    
    // Método auxiliar para contar cuántas veces está un producto en el carrito
    private int contarProductoEnCarrito(Producto producto) {
        int contador = 0;
        for (Producto p : items) {
            if (p != null && p.equals(producto)) {
                contador++;
            }
        }
        return contador;
    }
    
    // Eliminar item según UML
    public void eliminarItem(Producto producto) {
        if (producto != null) {
            // Buscar el producto en el array
            int indexAEliminar = -1;
            for (int i = 0; i < items.length; i++) {
                if (items[i] != null && items[i].equals(producto)) {
                    indexAEliminar = i;
                    break;
                }
            }
            
            // Si se encontró el producto, eliminarlo
            if (indexAEliminar >= 0) {
                Producto[] nuevosItems = new Producto[items.length - 1];
                int j = 0;
                
                for (int i = 0; i < items.length; i++) {
                    if (i != indexAEliminar) {
                        nuevosItems[j] = items[i];
                        j++;
                    }
                }
                
                items = nuevosItems;
                calcularTotal();
            }
        }
    }
    
    // Calcular total según UML (retorna int)
    public int getTotalAPagar() {
        calcularTotal();
        return totalAPagar;
    }
    
    // Método privado para calcular el total
    private void calcularTotal() {
        double total = 0.0;
        for (Producto p : items) {
            if (p != null) {
                total += p.getPrecio();
            }
        }
        this.totalAPagar = (int) Math.round(total); // Convertir a int como especifica el UML
    }
    
    // Getter para items
    public Producto[] getItems() {
        return items;
    }
    
    // Método adicional para verificar si está vacío
    public boolean estaVacio() {
        return items.length == 0;
    }
    
    // Método para obtener cantidad de items
    public int getCantidadItems() {
        return items.length;
    }
}
