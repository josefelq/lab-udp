
package servidorudp;

import clienteudp.Objeto;
import java.util.Date;


public class Data {
    
    private Objeto obj;
    
    private String dir;
    
    private Date date;
    
    public Data(Objeto obj, String dir, Date date){
        this.obj=obj;
        this.dir=dir;
        this.date=date;
    }
    
    public Objeto getObjeto(){
        return obj;
    }
    
    public String getDir(){
        return dir;
    }
    
    public Date getDate(){
        return date;
    }
    
}
