import java.util.List;
import java.util.Scanner;

public class Main {
    private static Sistema sistema;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        // Inicializar sistema
        sistema = new Sistema("admin", "admin123");
        scanner = new Scanner(System.in);
        
        mostrarBienvenida();
        menuPrincipal();
        
        scanner.close();
    }
    
    // ========== MENÚS PRINCIPALES ==========
    
    private static void mostrarBienvenida() {
        System.out.println(" ------ BIENVENIDO AL E-COMMERCE -------");
        System.out.println(" ---------- Sistema de Compras --------");
        System.out.println();
    }
    
    private static void menuPrincipal() {
        int opcion;
        do {
            System.out.println("\n═══════════ MENÚ PRINCIPAL ═══════════");
            System.out.println("1. Iniciar sesión como Cliente");
            System.out.println("2. Registrarse como nuevo Cliente");
            System.out.println("3. Acceder como Administrador");
            System.out.println("4. Salir del sistema");
            System.out.print("Seleccione una opción: ");
            
            opcion = leerEntero();
            
            switch (opcion) {
                case 1:
                    loginCliente();
                    break;
                case 2:
                    registrarNuevoCliente();
                    break;
                case 3:
                    loginAdministrador();
                    break;
                case 4:
                    System.out.println("\n¡Gracias por usar nuestro sistema!");
                    System.out.println("¡Vuelve pronto!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4);
    }
    
    // ========== FUNCIONES DE LOGIN Y REGISTRO ==========
    
    private static void loginCliente() {
        System.out.println("\n══════════ LOGIN CLIENTE ══════════");
        System.out.print("Ingrese su ID de cliente: ");
        String clienteID = scanner.nextLine();
        
        Cliente cliente = sistema.login(clienteID);
        if (cliente != null) {
            System.out.println("\n¡Bienvenido, " + cliente.getNombre() + "!");
            System.out.println("Tipo de cliente: " + cliente.getTipo());
            menuCliente();
        } else {
            System.out.println("Cliente no encontrado. Verifique su ID o regístrese.");
        }
    }
    
    private static void registrarNuevoCliente() {
        System.out.println("\n═════════ REGISTRO DE CLIENTE ═════════");
        
        System.out.print("Ingrese ID de cliente (único): ");
        String clienteID = scanner.nextLine();
        
        System.out.print("Ingrese su nombre completo: ");
        String nombre = scanner.nextLine();
        
        System.out.println("Tipo de cliente:");
        System.out.println("1. General");
        System.out.println("2. Estudiante");
        System.out.print("Seleccione (1-2): ");
        int tipoOpcion = leerEntero();
        
        String tipo = (tipoOpcion == 2) ? "Estudiante" : "General";
        String estudianteInfo = "";
        
        if (tipoOpcion == 2) {
            System.out.print("Ingrese información de estudiante (universidad/carnet): ");
            estudianteInfo = scanner.nextLine();
        }
        
        System.out.print("Ingrese número de tarjeta de crédito (opcional): ");
        String tarjetaCredito = scanner.nextLine();
        if (tarjetaCredito.trim().isEmpty()) {
            tarjetaCredito = null;
        }
        
        if (sistema.registrarCliente(clienteID, nombre, tipo, estudianteInfo, tarjetaCredito)) {
            System.out.println("\n¡Cliente registrado exitosamente!");
            System.out.println("Ya puede iniciar sesión con su ID: " + clienteID);
        } else {
            System.out.println("Error: Ya existe un cliente con ese ID.");
        }
    }
    
    private static void loginAdministrador() {
        System.out.println("\n══════════ LOGIN ADMINISTRADOR ══════════");
        System.out.print("Ingrese nombre de usuario: ");
        String usuario = scanner.nextLine();
        
        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine();
        
        if (sistema.esAdministrador(usuario, contrasena)) {
            System.out.println("\n¡Bienvenido Administrador!");
            menuAdministrador();
        } else {
            System.out.println("Credenciales incorrectas.");
        }
    }
    
    // ========== MENÚ DEL CLIENTE ==========
    
    private static void menuCliente() {
        int opcion;
        do {
            System.out.println("\n═══════════ MENÚ CLIENTE ═══════════");
            System.out.println("1. Ver catálogo de productos");
            System.out.println("2. Ver catálogo de kits");
            System.out.println("3. Agregar producto/kit al carrito");
            System.out.println("4. Ver mi carrito");
            System.out.println("5. Eliminar item del carrito");
            System.out.println("6. Procesar orden (realizar compra)");
            System.out.println("7. Ver mis órdenes anteriores");
            System.out.println("8. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            
            opcion = leerEntero();
            
            switch (opcion) {
                case 1:
                    mostrarCatalogoProductos();
                    break;
                case 2:
                    mostrarCatalogoKits();
                    break;
                case 3:
                    agregarItemAlCarrito();
                    break;
                case 4:
                    mostrarCarrito();
                    break;
                case 5:
                    eliminarItemDelCarrito();
                    break;
                case 6:
                    //procesarOrdenCliente() --- PENDIENTE
                    break;
                case 7:
                    // mostrarOrdenesCliente() --- PENDIENTE
                    break;
                case 8:
                    sistema.cerrarSesion();
                    System.out.println("Sesión cerrada correctamente.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 8);
    }
    
    // ========== FUNCIONES DEL CLIENTE ==========
    
    private static void mostrarCatalogoProductos() {
        System.out.println("\n═══════════ CATÁLOGO DE PRODUCTOS ═══════════");
        List<Producto> productos = sistema.getProductosDisponibles();
        
        if (productos.isEmpty()) {
            System.out.println("No hay productos disponibles en este momento.");
            return;
        }
        
        System.out.println("Productos disponibles:");
        System.out.println("─────────────────────────────────────────────");
        for (int i = 0; i < productos.size(); i++) {
            System.out.println((i + 1) + ". " + productos.get(i).toString());
        }
    }
    
    private static void mostrarCatalogoKits() {
        System.out.println("\n═══════════ CATÁLOGO DE KITS ═══════════");
        List<Kit> kits = sistema.getKitsDisponibles();
        
        if (kits.isEmpty()) {
            System.out.println("No hay kits disponibles en este momento.");
            return;
        }
        
        System.out.println("Kits disponibles:");
        System.out.println("─────────────────────────────────────────────");
        for (int i = 0; i < kits.size(); i++) {
            System.out.println((i + 1) + ". " + kits.get(i).toString());
        }
    }
    
    private static void agregarItemAlCarrito() {
        System.out.println("\n═══════════ AGREGAR AL CARRITO ═══════════");
        System.out.print("Ingrese el nombre exacto del producto o kit: ");
        String nombreItem = scanner.nextLine();
        
        System.out.print("Ingrese la cantidad: ");
        int cantidad = leerEntero();
        
        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor a 0.");
            return;
        }
        
        if (sistema.agregarAlCarrito(nombreItem, cantidad)) {
            System.out.println("Item(s) agregado(s) al carrito exitosamente.");
        } else {
            System.out.println("No se pudo agregar al carrito. Verifique:");
            System.out.println("   - Que el nombre sea exacto");
            System.out.println("   - Que haya stock suficiente");
            System.out.println("   - Que el producto esté disponible");
        }
    }
    
    private static void mostrarCarrito() {
        System.out.println("\n═══════════ MI CARRITO ═══════════");
        Carrito carrito = sistema.getCarritoActual();
        
        if (carrito == null || carrito.estaVacio()) {
            System.out.println("Su carrito está vacío.");
            return;
        }
        
        System.out.println("Items en su carrito:");
        System.out.println("─────────────────────────────────────────────");
        
        List<Producto> items = carrito.getItems();
        for (int i = 0; i < items.size(); i++) {
            Producto item = items.get(i);
            System.out.println((i + 1) + ". " + item.getNombre() + " - $" + item.getPrecio());
        }
        
        System.out.println("─────────────────────────────────────────────");
        System.out.println("Total de items: " + carrito.getCantidadItems());
        System.out.println("Total a pagar: $" + String.format("%.2f", carrito.getTotalAPagar()));
    }
    
    private static void eliminarItemDelCarrito() {
        mostrarCarrito();
        if (sistema.getCarritoActual() == null || sistema.getCarritoActual().estaVacio()) {
            return;
        }
        
        System.out.println("\n═══════════ ELIMINAR DEL CARRITO ═══════════");
        System.out.print("Ingrese el nombre exacto del item a eliminar: ");
        String nombreItem = scanner.nextLine();
        
        if (sistema.eliminarDelCarrito(nombreItem)) {
            System.out.println("Item eliminado del carrito.");
        } else {
            System.out.println("No se encontró el item en el carrito.");
        }
    }
    
    // PENDIENTE: procesarOrdenCliente()
    // PENDIENTE: mostrarOrdenesCliente()
    
    // ========== MENÚ DEL ADMINISTRADOR ==========
    
    private static void menuAdministrador() {
        int opcion;
        do {
            System.out.println("\n═══════════ MENÚ ADMINISTRADOR ═══════════");
            System.out.println("1. Ver todos los productos");
            System.out.println("2. Agregar nuevo producto");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Ver todos los kits");
            System.out.println("5. Ver todas las órdenes");
            System.out.println("6. Ver todos los clientes");
            System.out.println("7. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            
            opcion = leerEntero();
            
            switch (opcion) {
                case 1:
                    mostrarTodosLosProductos();
                    break;
                case 2:
                    agregarNuevoProducto();
                    break;
                case 3:
                    eliminarProducto();
                    break;
                case 4:
                    mostrarTodosLosKits();
                    break;
                case 5:
                    // mostrarTodasLasOrdenes() -- PENDIENTE
                    break;
                case 6:
                    mostrarTodosLosClientes();
                    break;
                case 7:
                    System.out.println("Sesión de administrador cerrada.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 7);
    }
    
    // ========== FUNCIONES DEL ADMINISTRADOR ==========
    
    private static void mostrarTodosLosProductos() {
        System.out.println("\n═══════════ TODOS LOS PRODUCTOS ═══════════");
        List<Producto> productos = sistema.getProductos();
        
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        
        System.out.println("Lista completa de productos:");
        System.out.println("─────────────────────────────────────────────");
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            String estado = p.isDisponible() ? "Disponible" : "No disponible";
            System.out.println((i + 1) + ". " + p.toString() + " | " + estado);
        }
    }
    
    private static void agregarNuevoProducto() {
        System.out.println("\n═══════════ AGREGAR PRODUCTO ═══════════");
        
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Precio: $");
        double precio = leerDouble();
        
        System.out.print("Cantidad inicial en stock: ");
        int stock = leerEntero();
        
        boolean disponible = stock > 0;
        Producto nuevoProducto = new Producto(nombre, precio, stock);
        
        if (sistema.añadirProducto(nuevoProducto)) {
            System.out.println("Producto agregado exitosamente.");
        } else {
            System.out.println("Error al agregar el producto.");
        }
    }
    
    private static void eliminarProducto() {
        mostrarTodosLosProductos();
        
        System.out.println("\n═══════════ ELIMINAR PRODUCTO ═══════════");
        System.out.print("Ingrese el nombre exacto del producto a eliminar: ");
        String nombre = scanner.nextLine();
        
        if (sistema.quitarProducto(nombre)) {
            System.out.println("Producto eliminado exitosamente.");
        } else {
            System.out.println("No se encontró el producto.");
        }
    }
    
    private static void mostrarTodosLosKits() {
        System.out.println("\n═══════════ TODOS LOS KITS ═══════════");
        List<Kit> kits = sistema.getKits();
        
        if (kits.isEmpty()) {
            System.out.println("No hay kits registrados.");
            return;
        }
        
        System.out.println("Lista completa de kits:");
        System.out.println("─────────────────────────────────────────────");
        for (int i = 0; i < kits.size(); i++) {
            Kit k = kits.get(i);
            String estado = k.isDisponible() ? "Disponible" : "No disponible";
            System.out.println((i + 1) + ". " + k.toString() + " | " + estado);
        }
    }
    
    
    private static void mostrarTodosLosClientes() {
        System.out.println("\n═══════════ TODOS LOS CLIENTES ═══════════");
        List<Cliente> clientes = sistema.getClientes();
        
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        
        System.out.println("Lista de clientes registrados:");
        System.out.println("─────────────────────────────────────────────");
        for (Cliente cliente : clientes) {
            System.out.println("- " + cliente.getResumenCliente());
        }
    }
    
    // ========== FUNCIONES AUXILIARES ==========
    
    private static int leerEntero() {
        try {
            int numero = Integer.parseInt(scanner.nextLine());
            return numero;
        } catch (NumberFormatException e) {
            System.out.println("Por favor ingrese un número válido.");
            return -1;
        }
    }
    
    private static double leerDouble() {
        try {
            double numero = Double.parseDouble(scanner.nextLine());
            return numero;
        } catch (NumberFormatException e) {
            System.out.println("Por favor ingrese un número válido.");
            return -1.0;
        }
    }
}
