public class Producto {
    
    private String nombre;
    private double precio;
    private boolean disponible;
    private int unidadesDisponibles;

    
    //Constructor
    public Producto(String nombre, double precio, boolean disponible, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.unidadesDisponibles = stock;
        this.disponible = disponible;
    }

    //Setters y getters
    public String getNombre() { 
        return nombre; 
    }
    
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    
    public double getPrecio() { 
        return precio; 
    }
    
    public void setPrecio(double precio){ 
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

    // Metodos
    
    public void setUnidadesDisponibles(int unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
        this.disponible = this.unidadesDisponibles > 0;
    }

    // Descontar unidades (cuando se procesa una orden)
    public void descontarUnidades(int cantidad) {
        if (cantidad <= unidadesDisponibles) {
            this.unidadesDisponibles -= cantidad;
        }
        if (this.unidadesDisponibles <= 0) {
            this.disponible = false;
        }
    }

    @Override
    public String toString() {
        return nombre + " - Precio: $" + precio + " - Stock: " + unidadesDisponibles;
    }
    
}
