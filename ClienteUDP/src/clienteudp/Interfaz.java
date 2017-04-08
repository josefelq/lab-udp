package clienteudp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Interfaz {
    
        /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
           ClienteUDP cliente=new ClienteUDP();
           cliente.setNumeroEnvios(10);
           cliente.enviarDatos();  
           
           ClienteUDP cliente2=new ClienteUDP();
           cliente2.setNumeroEnvios(20);
           cliente2.enviarDatos();  
           
           ClienteUDP cliente3=new ClienteUDP();
           cliente3.setNumeroEnvios(30);
           cliente3.enviarDatos();  
    }
    
}
