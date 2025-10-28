import java.util.ArrayList;
import java.util.List;

public class Sistema {
    // Atributos principales del sistema
    private String nombre;
    private String contrasena;
    private List<Cliente> clientes;
    private List<Producto> productos;
    private List<Kit> kits;
    private List<Orden> ordenes;
    private Cliente clienteActual;
    
    // Constructor
    public Sistema(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.clientes = new ArrayList<>();
        this.productos = new ArrayList<>();
        this.kits = new ArrayList<>();
        this.ordenes = new ArrayList<>();
        this.clienteActual = null;
        
        // Inicializar con productos y kits de ejemplo
        this.inicializarInventario();
    }
    
    // ========== MÉTODOS DE AUTENTICACIÓN ==========
    
    public boolean esAdministrador(String nombre, String contrasena) {
        return this.nombre.equals(nombre) && this.contrasena.equals(contrasena);
    }
    
    public Cliente login(String clienteID) {
        for (Cliente c : clientes) {
            if (c.getClienteID().equals(clienteID)) {
                this.clienteActual = c;
                return c;
            }
        }
        return null;
    }
    
    public boolean registrarCliente(String clienteID, String nombre, String tipo, 
                                   String estudianteInfo, String tarjetaCredito) {
        // Verificar si el cliente ya existe
        for (Cliente c : clientes) {
            if (c.getClienteID().equals(clienteID)) {
                return false; // Cliente ya existe
            }
        }
        
        Cliente nuevoCliente = new Cliente(clienteID, nombre, tipo, estudianteInfo, tarjetaCredito);
        clientes.add(nuevoCliente);
        return true;
    }
    
    // ========== MÉTODOS DE ADMINISTRACIÓN DE PRODUCTOS (SOLO ADMIN) ==========
    
    public boolean añadirProducto(Producto producto) {
        if (producto != null) {
            productos.add(producto);
            return true;
        }
        return false;
    }
    
    public boolean quitarProducto(String nombreProducto) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getNombre().equalsIgnoreCase(nombreProducto)) {
                productos.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public boolean añadirKit(Kit kit) {
        if (kit != null) {
            kits.add(kit);
            return true;
        }
        return false;
    }
    
    public boolean quitarKit(String nombreKit) {
        for (int i = 0; i < kits.size(); i++) {
            if (kits.get(i).getNombre().equalsIgnoreCase(nombreKit)) {
                kits.remove(i);
                return true;
            }
        }
        return false;
    }
    
    // ========== MÉTODOS DE CONSULTA DEL CATÁLOGO ==========
    
    public List<Producto> getProductos() {
        return new ArrayList<>(productos); // Retorna una copia porque es mas seguro.
    }
    
    public List<Kit> getKits() {
        return new ArrayList<>(kits); // Retorna una copia porque es mas seguro.
    }
    
    public List<Producto> getProductosDisponibles() {
        List<Producto> disponibles = new ArrayList<>();
        for (Producto p : productos) {
            if (p.isDisponible() && p.getUnidadesDisponibles() > 0) {
                disponibles.add(p);
            }
        }
        return disponibles;
    }
    
    public List<Kit> getKitsDisponibles() {
        List<Kit> disponibles = new ArrayList<>();
        for (Kit k : kits) {
            if (k.isDisponible() && k.getUnidadesDisponibles() > 0) {
                disponibles.add(k);
            }
        }
        return disponibles;
    }
    
    public Producto buscarProductoPorNombre(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }
    
    public Kit buscarKitPorNombre(String nombre) {
        for (Kit k : kits) {
            if (k.getNombre().equalsIgnoreCase(nombre)) {
                return k;
            }
        }
        return null;
    }
    
    // ========== MÉTODOS DE GESTIÓN DEL CARRITO ==========
    
    public boolean agregarAlCarrito(String nombreItem, int cantidad) {
        if (clienteActual == null) return false;
        
        // Buscar primero en productos
        Producto producto = buscarProductoPorNombre(nombreItem);
        if (producto != null) {
            boolean agregado = true;
            for (int i = 0; i < cantidad; i++) {
                if (!clienteActual.getCarrito().agregarItem(producto)) {
                    agregado = false;
                    break;
                }
            }
            return agregado;
        }
        
        // Buscar en kits
        Kit kit = buscarKitPorNombre(nombreItem);
        if (kit != null) {
            boolean agregado = true;
            for (int i = 0; i < cantidad; i++) {
                if (!clienteActual.getCarrito().agregarItem(kit)) {
                    agregado = false;
                    break;
                }
            }
            return agregado;
        }
        
        return false; // No se encontró el item
    }
    
    public boolean eliminarDelCarrito(String nombreItem) {
        if (clienteActual == null) return false;
        
        Producto producto = buscarProductoPorNombre(nombreItem);
        if (producto != null) {
            return clienteActual.getCarrito().eliminarItem(producto);
        }
        
        Kit kit = buscarKitPorNombre(nombreItem);
        if (kit != null) {
            return clienteActual.getCarrito().eliminarItem(kit);
        }
        
        return false;
    }
    
    public Carrito getCarritoActual() {
        if (clienteActual != null) {
            return clienteActual.getCarrito();
        }
        return null;
    }
    
    // ========== MÉTODOS DE PROCESAMIENTO DE ÓRDENES ==========
    
    // PENDIENTE
    
    // ========== MÉTODOS DE VALIDACIÓN ==========
    
    public boolean comprobarContrasena(String contrasena) {
        return this.contrasena.equals(contrasena);
    }
    
    // ========== MÉTODOS AUXILIARES ==========
    
    private void inicializarInventario() {
        // Productos de ejemplo
        productos.add(new Producto("Protoboard", 50.00, 20));
        productos.add(new Producto("Bolsa 100 jumpers", 75.00, 20));
        productos.add(new Producto("Capacitor 10 microF", 1.50, 7));
        productos.add(new Producto("Resistencia 20K Ohm", 1.00, 8));
        productos.add(new Producto("Circuito integrado TTL 4 compuertas AND", 5.00, 12));
        
        // Kit de ejemplo
        Producto[] productosKit = {
            buscarProductoPorNombre("Protoboard"),
            buscarProductoPorNombre("Bolsa 100 jumpers")
        };
        this.kits.add(new Kit("Kit Electrónica básica", 125.00));
    }

    public void cerrarSesion() {
        this.clienteActual = null;
    }
    
    // ========== GETTERS BÁSICOS ==========
    
    public String getNombre() {
        return nombre;
    }
    
    public List<Cliente> getClientes() {
        return new ArrayList<>(clientes);
    }
}
