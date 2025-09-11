public class Cliente {
    private String clienteID;
    private String nombre;
    private String tipo; // "Estudiante", "General", etc.
    private String estudianteInfo; // info adicional si es estudiante
    private String tarjetaCredito;
    private Carrito carrito; // cada cliente tiene su propio carrito

    // Constructor
    public Cliente(String clienteID, String nombre, String tipo, String estudianteInfo, String tarjetaCredito) {
        this.clienteID = clienteID;
        this.nombre = nombre;
        this.tipo = tipo;
        this.estudianteInfo = estudianteInfo;
        this.tarjetaCredito = tarjetaCredito;
        this.carrito = new Carrito();
    }

    // --- Getters y Setters ---
    public String getClienteID() {
        return clienteID;
    }

    public void setClienteID(String clienteID) {
        this.clienteID = clienteID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstudianteInfo() {
        return estudianteInfo;
    }

    public void setEstudianteInfo(String estudianteInfo) {
        this.estudianteInfo = estudianteInfo;
    }

    public String getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(String tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    // Métodos

    // Devuelve un resumen de la información del cliente
    public String getResumenCliente() {
        return "ID: " + clienteID +
               ", Nombre: " + nombre +
               ", Tipo: " + tipo +
               (tipo.equalsIgnoreCase("Estudiante") ? ", Info: " + estudianteInfo : "");
    }

}
