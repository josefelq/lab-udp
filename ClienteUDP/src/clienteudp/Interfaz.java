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
           cliente.setNumeroEnvios(100);
           cliente.enviarDatos();
    }
    
}
