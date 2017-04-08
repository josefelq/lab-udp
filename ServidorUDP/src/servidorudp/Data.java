
package servidorudp;

import clienteudp.Objeto;


public class Data {
    
    private Objeto obj;
    
    private String dir;
    
    public Data(Objeto obj, String dir){
        this.obj=obj;
        this.dir=dir;
    }
    
    public Objeto getObjeto(){
        return obj;
    }
    
    public String getDir(){
        return dir;
    }
    
}
