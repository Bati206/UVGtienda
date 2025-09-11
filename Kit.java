package mainuvtienda;

import java.util.ArrayList;

public class Kit extends Producto {
    private ArrayList<Producto> productos; // Productos dentro del kit

    // Constructor del kit
    public Kit(String nombreKit, double precioKit, int unidadesDisponibles) {
        super(nombreKit, precioKit, unidadesDisponibles); // Llama al constructor de Producto
        this.productos = new ArrayList<>();
    }

    // Agregar un producto al kit
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    // Eliminar un producto del kit
    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
    }

    // Getters y setters para la lista de productos
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }
    
    // Método para buscar un producto por su nombre dentro del kit
    public Producto getProductoPorNombre(String nombre) {
        for (Producto p : productos) { // Recorre la lista de productos del kit
            if (p.getNombre().equalsIgnoreCase(nombre)) { // Compara nombres ignorando mayúsculas/minúsculas
                return p; // Devuelve el producto encontrado
            }
        }
        return null; // Retorna null si no se encuentra ningún producto con ese nombre
    }


    // Método para mostrar información del kit
    public void mostrarInfo() {
        System.out.println("Nombre del Kit: " + getNombre());
        System.out.println("Precio del Kit: " + getPrecio());
        System.out.println("Disponible: " + isDisponible());
        System.out.println("Unidades disponibles: " + getUnidadesDisponibles());
        System.out.println("Productos dentro del kit:");
        for (Producto p : productos) {
            System.out.println(" - " + p.getNombre() + " ($" + p.getPrecio() + ")");
        }
    }
}
