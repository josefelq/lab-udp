package clienteudp;

import java.io.Serializable;

public class Objeto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private int numeroSecuencia;
    
    private String fecha;
    
    public Objeto(int numeroSecuencia, String fecha){
        this.numeroSecuencia=numeroSecuencia;
        this.fecha=fecha;
    }
    
    public int getNumeroSecuencia(){
        return numeroSecuencia;
    }
    
    public String getFecha(){
        return fecha;
    }
    
    public void setFecha(String fecha){
        this.fecha=fecha;
    }
    
    public void setNumeroSecuencia(int numeroSecuencia){
        this.numeroSecuencia=numeroSecuencia;
    }
    
    public String toString(){
        return fecha;
    }
    
    
    
}
