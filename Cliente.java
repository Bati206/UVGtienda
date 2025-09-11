package mainuvtienda;

public class Cliente {
    private String clienteID;
    private String nombre;
    private boolean tipo; // true = estudiante, false = otro tipo
    private String estudiante; // si es estudiante, puede guardar info adicional
    private String tarjetaCredito;

    // Constructor
    public Cliente(String clienteID, String nombre, boolean tipo, String estudiante, String tarjetaCredito) {
        this.clienteID = clienteID;
        this.nombre = nombre;
        this.tipo = tipo;
        this.estudiante = estudiante;
        this.tarjetaCredito = tarjetaCredito;
    }

    // Getters y Setters
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

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public String getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(String tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    // Método opcional para mostrar información del cliente
    public void mostrarInfo() {
        System.out.println("ID Cliente: " + clienteID);
        System.out.println("Nombre: " + nombre);
        System.out.println("Tipo estudiante: " + tipo);
        System.out.println("Información estudiante: " + estudiante);
        System.out.println("Tarjeta de crédito: " + tarjetaCredito);
    }
}
