import java.util.ArrayList;

)
public class Carrito {
    private ArrayList<Producto> productos;   // Lista de productos en el carrito
    private ArrayList<Integer> cantidades;   // Cantidades correspondientes
    private Cliente cliente;                 // Cliente dueño del carrito

    // Constructor
    public Carrito(Cliente cliente) {
        this.productos = new ArrayList<>();
        this.cantidades = new ArrayList<>();
        this.cliente = cliente;
    }

    // Agregar producto al carrito
    public void agregarProducto(Producto p, int cantidad) {
        if (cantidad <= p.getUnidadesDisponibles()) {
            productos.add(p);
            cantidades.add(cantidad);
        }
    }

    // Eliminar un producto del carrito
    public void eliminarProducto(Producto p) {
        int index = productos.indexOf(p);
        if (index >= 0) {
            productos.remove(index);
            cantidades.remove(index);
        }
    }

    // Calcular el total del carrito
    public int calcularTotal() {
        int total = 0;
        for (int i = 0; i < productos.size(); i++) {
            total += productos.get(i).getPrecio() * cantidades.get(i);
        }
        return total;
    }

    // Vaciar el carrito después de una orden
    public void vaciar() {
        productos.clear();
        cantidades.clear();
    }

    // Getters
    public ArrayList<Producto> getProductos() { return productos; }
    public ArrayList<Integer> getCantidades() { return cantidades; }
    public Cliente getCliente() { return cliente; }
}