package servidorudp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import clienteudp.Objeto;
import java.util.ArrayList;
import java.util.Date;
import java.util.Queue;

public class ServidorUDP {
    
    public static ArrayList<Data> list;
    
    public static ArrayList<String> currentClients;

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
        
        list=new ArrayList<Data>();
        currentClients =new ArrayList<String>();
        
        DatagramSocket socket = null;
         try {
            socket = new DatagramSocket(5000);
            socket.setReceiveBufferSize(20000000);
            //socket = new DatagramSocket(Integer.parseInt(args[0]));
            while (true) {
                byte[] incomingData = new byte[2048];
                DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
                socket.receive(incomingPacket);
                byte[] data = incomingPacket.getData();
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                ObjectInputStream is = new ObjectInputStream(in);
                try {
                    Objeto o = (Objeto) is.readObject();
                    InetAddress IPAddress = incomingPacket.getAddress();
                    int port = incomingPacket.getPort();
                    String dir=""+IPAddress.toString()+";"+port;
                    System.out.println("object received = " + o.getNumeroSecuencia());
                    list.add(new Data(o, dir, new Date()));
                    if(currentClients.isEmpty()){
                        currentClients.add(dir);
                        (new Conexion(dir)).start();
                    }
                    else{
                        if(!currentClients.contains(dir)){
                            currentClients.add(dir);
                            (new Conexion(dir)).start();
                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                
              
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        } 
    }
    
    public static synchronized ArrayList<String> getCurrentClients(){
        return currentClients;
    }
    
    public static synchronized ArrayList<Data> getData(){
        return list;
    }
}
