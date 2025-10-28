public class Producto {
    
    private String nombre;
    private double precio;
    private boolean disponible;
    private int unidadesDisponibles;

    // Constructor principal
    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.unidadesDisponibles = stock;
        this.disponible = stock > 0;
    }

    // Getters y Setters
    public String getNombre() { 
        return nombre; 
    }
    
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    public double getPrecio() { 
        return precio; 
    }
    
    public void setPrecio(double precio) { 
        this.precio = precio; 
    }

    public boolean isDisponible() { 
        return disponible; 
    }
    
    public void setDisponible(boolean disponible) { 
        this.disponible = disponible; 
    }

    public int getUnidadesDisponibles() { 
        return unidadesDisponibles; 
    }
    
    public void setUnidadesDisponibles(int unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
        this.disponible = this.unidadesDisponibles > 0;
    }

    // Método para reducir stock después de una compra
    public void reducirStock(int cantidad) {
        if (cantidad > 0 && cantidad <= unidadesDisponibles) {
            unidadesDisponibles -= cantidad;
            if (unidadesDisponibles <= 0) {
                unidadesDisponibles = 0;
                disponible = false;
            }
        }
    }

    // Método para aumentar stock
    public void aumentarStock(int cantidad) {
        if (cantidad > 0) {
            unidadesDisponibles += cantidad;
            disponible = true;
        }
    }

    // Método para verificar si hay suficiente stock
    public boolean hayStock(int cantidad) {
        return disponible && cantidad <= unidadesDisponibles;
    }

    // Representación textual del producto (para catálogos o resúmenes)
    @Override
    public String toString() {
        return String.format(
            "%-20s | Precio: $%.2f | Stock: %d | %s",
            nombre, precio, unidadesDisponibles, 
            (disponible ? "Disponible" : "Agotado")
        );
    }


}
