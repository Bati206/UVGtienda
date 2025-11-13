
import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private String nombre;
    private String contrasena;
    private List<Cliente> clientes;
    private List<Producto> productos;
    private List<Kit> kits;
    private List<Orden> ordenes;
    private Cliente clienteActual;
    
    public Sistema(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.clientes = new ArrayList<>();
        this.productos = new ArrayList<>();
        this.kits = new ArrayList<>();
        this.ordenes = new ArrayList<>();
        this.clienteActual = null;
        this.inicializarInventario();
    }
    
    // ========== AUTENTICACIÓN ==========
    
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
        for (Cliente c : clientes) {
            if (c.getClienteID().equals(clienteID)) {
                return false;
            }
        }
        Cliente nuevoCliente = new Cliente(clienteID, nombre, tipo, estudianteInfo, tarjetaCredito);
        clientes.add(nuevoCliente);
        return true;
    }
    
    // ========== ADMINISTRACIÓN DE PRODUCTOS ==========
    
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
    
    // ========== CONSULTA DE CATÁLOGO ==========
    
    public List<Producto> getProductos() {
        return new ArrayList<>(productos);
    }
    
    public List<Kit> getKits() {
        return new ArrayList<>(kits);
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
    
    // ========== GESTIÓN DEL CARRITO ==========
    
    public boolean agregarAlCarrito(String nombreItem, int cantidad) {
        if (clienteActual == null) return false;
        
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
        
        return false;
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
    
    // ========== PROCESAMIENTO DE ÓRDENES ==========
    
    public String procesarOrden(String metodoPago) {
        if (clienteActual == null) {
            return "ERROR: No hay cliente con sesión activa";
        }
        
        Carrito carrito = clienteActual.getCarrito();
        if (carrito == null || carrito.estaVacio()) {
            return "ERROR: El carrito está vacío";
        }
        
        // Crear orden
        Orden nuevaOrden = new Orden();
        String ordenID = "ORD-" + System.currentTimeMillis();
        nuevaOrden.setOrdenID(ordenID);
        nuevaOrden.setComprador(clienteActual);
        nuevaOrden.setCarritoUsuario(carrito);
        
        // Crear pago
        Pago pago = new Pago(metodoPago, clienteActual);
        
        // Procesar orden (esto valida pago y reduce stock)
        nuevaOrden.procesarOrden(pago, clienteActual);
        
        // Agregar orden a la lista
        ordenes.add(nuevaOrden);
        
        // Limpiar carrito del cliente
        clienteActual.getCarrito().vaciar();
        
        return nuevaOrden.getOrdenStatus();
    }
    
    public List<Orden> getOrdenesDelCliente() {
        if (clienteActual == null) return new ArrayList<>();
        
        List<Orden> ordenesCliente = new ArrayList<>();
        for (Orden o : ordenes) {
            if (o.getComprador().getClienteID().equals(clienteActual.getClienteID())) {
                ordenesCliente.add(o);
            }
        }
        return ordenesCliente;
    }
    
    public List<Orden> getOrdenes() {
        return new ArrayList<>(ordenes);
    }
    
    // ========== AUXILIARES ==========
    
    private void inicializarInventario() {
        // Productos de ejemplo
        productos.add(new Producto("Protoboard", 50.00, 20));
        productos.add(new Producto("Bolsa 100 jumpers", 75.00, 20));
        productos.add(new Producto("Capacitor 10 microF", 1.50, 7));
        productos.add(new Producto("Resistencia 20K Ohm", 1.00, 8));
        productos.add(new Producto("Circuito integrado TTL 4 compuertas AND", 5.00, 12));
        
        // Kit de ejemplo CON productos agregados
        Kit kitBasico = new Kit("Electronica Basica", 125.00, 
            "Kit ideal para principiantes en electrónica. Incluye los componentes esenciales para comenzar tus proyectos.");
        kitBasico.agregarProducto(buscarProductoPorNombre("Protoboard"));
        kitBasico.agregarProducto(buscarProductoPorNombre("Bolsa 100 jumpers"));
        kits.add(kitBasico);
    }

    public void cerrarSesion() {
        this.clienteActual = null;
    }
    
    public boolean comprobarContrasena(String contrasena) {
        return this.contrasena.equals(contrasena);
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public List<Cliente> getClientes() {
        return new ArrayList<>(clientes);
    }
    
    public Cliente getClienteActual() {
        return clienteActual;
    }
}

