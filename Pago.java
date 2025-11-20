public class Pago {
    
    
    private String metodoPago; //SOLAMENTE PUEDE SER "tarjeta", "transferencia" O "efectivo"
    private boolean statusPago; //True es realizado, false es que no ha sido realizado
    private Cliente tarjetaHabiente; //El tarjeta habiente como clase Cliente

    //Constructor
     public Pago(String metodo, Cliente cliente) {
        this.metodoPago = metodo;
        this.statusPago = false;
        this.tarjetaHabiente = cliente;
    }

     //Por las limitaciones solamente revisará que haya info de la terjeta de credito y cambiará el status del pago a true, es decir, realizado.
     //Si en dado caso el método de pago no es tarjeta de credito entonces automaticamente cambia el valor a true.
     public void setStatusPago() {
        if (this.metodoPago.equalsIgnoreCase("tarjeta")){ 
            String tc = this.tarjetaHabiente.getTarjetaCredito();
            if (tc != null && !tc.trim().isEmpty()) {
                this.statusPago = true;
            } else {
                this.statusPago = false;
            }
        } else {
             // transferencia o efectivo se acepta
            this.statusPago = true;
        }
     }

     //Set del tarjetaHabiente
     public void setTarjetaHabiente(Cliente cliente){
         this.tarjetaHabiente = cliente;
     }
     
     //Get del método de pago
     public String getMetodoPago(){
         return this.metodoPago;
     }
    
    //Get del Status de pago
    public boolean getStatusPago(){
    return this.statusPago;
    }
    
    //Retorna el tarjeta habiente como objeto
    public Cliente getTarjetaHabiente(){
        return this.tarjetaHabiente;
    }
     
     
    
}
