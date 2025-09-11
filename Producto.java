package mainuvgtienda;

public class Producto {
    
    private String nombre;
    private double precio;
    private boolean disponible;
    private int unidadesDisponibles;

    
    //Constructor
    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.unidadesDisponibles = stock;
        this.disponible = stock > 0;
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
    
    public void setUnidadesDisponibles(int unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
        this.disponible = unidadesDisponibles > 0;
    }
    
}
