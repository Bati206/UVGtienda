import java.util.ArrayList;
import java.util.List;

public class Kit extends Producto {
    private List<Producto> productos;

    // Constructor
    public Kit(String nombre, double precio, Producto[] productos, boolean disponible, int stock) {
        super(nombre, precio, disponible, stock);
        this.productos = new ArrayList<>();
        for (Producto p : productos) {
            this.productos.add(p);
        }
    }

    // Métodos de gestión de productos en el kit
    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public boolean eliminarProducto(String nombreProducto) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombreProducto)) {
                productos.remove(p);
                return true; // eliminado con éxito
            }
        }
        return false; // no encontrado
    }

    // Getter y Setter
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    // Metodo para descontar stock de productos, segun compra de kits
    public void descontarStockProductos(int cantidadKits) {
        // Descontar del propio kit
        super.descontarUnidades(cantidadKits);

        // Descontar de cada producto del kit
        for (Producto p : this.productos) {
            p.descontarUnidades(cantidadKits);
        }
    }

    // Mostrar detalles del Kit
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getNombre())
          .append(" - Precio Kit: $").append(getPrecio())
          .append(" - Stock: ").append(getUnidadesDisponibles())
          .append("\n   Incluye: ");
        
        for (Producto p : productos) {
            sb.append(p.getNombre()).append(" ($").append(p.getPrecio()).append("), ");
        }
        return sb.toString();
    }

    // Recalcular stock del kit según productos internos
    public void setUnidadesDisponibles(int unidadesDisponibles) {
        super.setUnidadesDisponibles(unidadesDisponibles);

        // El stock del kit no puede superar al mínimo stock de sus productos
        int minStock = unidadesDisponibles;
        for (Producto p : productos) {
            if (p.getUnidadesDisponibles() < minStock) {
                minStock = p.getUnidadesDisponibles();
            }
        }
        super.setUnidadesDisponibles(minStock);
    }
}
