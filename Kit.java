import java.util.ArrayList;
import java.util.List;

public class Kit extends Producto {

    private List<Producto> productos; // Productos que conforman el kit

    // Constructor
    public Kit(String nombre, double precioInicial) {
        // Por defecto el kit no tiene stock definido aún
        super(nombre, precioInicial, 0);
        this.productos = new ArrayList<>();
    }

    // Agregar producto al kit
    public void agregarProducto(Producto p) {
        if (p != null && !productos.contains(p)) {
            productos.add(p);
            calcularPrecioTotal();
            actualizarStock();
        }
    }

    // Eliminar producto del kit
    public void eliminarProducto(Producto p) {
        if (p != null && productos.contains(p)) {
            productos.remove(p);
            calcularPrecioTotal();
            actualizarStock();
        }
    }

    // Calcular el precio total del kit (suma de precios de los productos)
    //    Puede incluir descuentos o márgenes en el futuro
    public void calcularPrecioTotal() {
        double total = 0.0;
        for (Producto p : productos) {
            total += p.getPrecio();
        }
        // Por ahora el precio del kit es la suma directa
        super.setPrecio(total);
    }

    // Actualizar el stock del kit según el producto con menor stock
    //    (no se puede vender más kits que el producto más limitado)
    public void actualizarStock() {
        if (productos.isEmpty()) {
            setUnidadesDisponibles(0);
            setDisponible(false);
            return;
        }

        int stockMinimo = Integer.MAX_VALUE;
        for (Producto p : productos) {
            if (p.getUnidadesDisponibles() < stockMinimo) {
                stockMinimo = p.getUnidadesDisponibles();
            }
        }

        setUnidadesDisponibles(stockMinimo);
        setDisponible(stockMinimo > 0);
    }

    // Verifica si un producto pertenece al kit
    public boolean contieneProducto(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    // Reduce el stock de los productos individuales al vender el kit
    public void reducirStockKit(int cantidad) {
        if (cantidad <= getUnidadesDisponibles()) {
            for (Producto p : productos) {
                p.reducirStock(cantidad);
            }
            actualizarStock();
        }
    }

    // Obtener lista de productos del kit
    public List<Producto> getProductos() {
        return productos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("KIT: ").append(getNombre())
          .append(" | Precio total: $").append(String.format("%.2f", getPrecio()))
          .append(" | Stock: ").append(getUnidadesDisponibles()).append("\n")
          .append("  Incluye:\n");

        for (Producto p : productos) {
            sb.append("   - ").append(p.getNombre())
              .append(" ($").append(String.format("%.2f", p.getPrecio())).append(")\n");
        }
        return sb.toString();
    }
}
