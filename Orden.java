
public class Orden {
    private String ordenID;         // Identificador único de la orden
    private EstadoOrden estado;     // Estado de la orden
    private Cliente cliente;        // Cliente que genera la orden
    private Carrito carrito;        // Carrito asociado
    private Pago pago;              // Pago asociado

    // Constructor
    public Orden(String ordenID, Cliente cliente, Carrito carrito, Pago pago) {
        this.ordenID = ordenID;
        this.cliente = cliente;
        this.carrito = carrito;
        this.pago = pago;
        this.estado = EstadoOrden.PENDIENTE;
    }

    // Procesar la orden
    public boolean procesarOrden() {
        if (pago.validarPago()) {
            // Validar que hay stock suficiente para todos los productos
            for (int i = 0; i < carrito.getProductos().size(); i++) {
                Producto p = carrito.getProductos().get(i);
                int cantidad = carrito.getCantidades().get(i);
                if (cantidad > p.getUnidadesDisponibles()) {
                    estado = EstadoOrden.RECHAZADA;
                    return false;
                }
            }

            // Reducir el stock de los productos
            for (int i = 0; i < carrito.getProductos().size(); i++) {
                Producto p = carrito.getProductos().get(i);
                int cantidad = carrito.getCantidades().get(i);
                p.reducirStock(cantidad);

                // Si es un kit, también reducir stock de productos internos
                if (p instanceof Kit) {
                    Kit kit = (Kit) p;
                    for (Producto subP : kit.getProductos()) {
                        subP.reducirStock(1 * cantidad);
                    }
                }
            }

            estado = EstadoOrden.PROCESADA;
            carrito.vaciar(); // Vaciar el carrito tras el éxito
            return true;
        } else {
            estado = EstadoOrden.RECHAZADA;
            return false;
        }
    }

    // Getters
    public String getOrdenID() { return ordenID; }
    public EstadoOrden getEstado() { return estado; }
    public Cliente getCliente() { return cliente; }
    public Carrito getCarrito() { return carrito; }
    public Pago getPago() { return pago; }
}