import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<Producto> items;        
    private double totalAPagar;         

    // Constructor vacío 
    public Carrito() {
        this.items = new ArrayList<>();
        this.totalAPagar = 0.0;
    }

    // Contructor copia
    public Carrito(Carrito otro) {
        this.items = new ArrayList<>(otro.items);
        calcularTotal();
    }

    // Agregar item (Producto o Kit)
    public boolean agregarItem(Producto producto) {
        if (producto != null && producto.isDisponible() && producto.getUnidadesDisponibles() > 0) {
            int vecesEnCarrito = contarProductoEnCarrito(producto);

            if (vecesEnCarrito < producto.getUnidadesDisponibles()) {
                items.add(producto);
                calcularTotal();
                return true; // agregado correctamente
            }
        }
        return false; // no se pudo agregar
    }

    // Método auxiliar para contar cuántas veces está un producto en el carrito
    private int contarProductoEnCarrito(Producto producto) {
        int contador = 0;
        for (Producto p : items) {
            if (p.equals(producto)) {
                contador++;
            }
        }
        return contador;
    }

    // Eliminar item
    public boolean eliminarItem(Producto producto) {
        boolean eliminado = items.remove(producto);
        if (eliminado) {
            calcularTotal();
        }
        return eliminado;
    }

    // Calcular total (double en lugar de int para no perder decimales)
    public double getTotalAPagar() {
        calcularTotal();
        return totalAPagar;
    }

    // Método privado para calcular el total
    private void calcularTotal() {
        double total = 0.0;
        for (Producto p : items) {
            total += p.getPrecio();
        }
        this.totalAPagar = total;
    }

    // Getter para items
    public List<Producto> getItems() {
        return new ArrayList<>(items); // devuelve copia para seguridad
    }

    // Método adicional para verificar si está vacío
    public boolean estaVacio() {
        return items.isEmpty();
    }

    // Método para obtener cantidad de items
    public int getCantidadItems() {
        return items.size();
    }

    // Vaciar carrito
    public void vaciar() {
        items.clear();
        calcularTotal();
    }
}


