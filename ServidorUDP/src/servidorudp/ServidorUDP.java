package servidorudp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import clienteudp.Objeto;

public class ServidorUDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
        DatagramSocket socket = null;
         try {
            socket = new DatagramSocket(1978);
            //socket = new DatagramSocket(Integer.parseInt(args[0]));
            byte[] incomingData = new byte[1024];

            while (true) {
                DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
                socket.receive(incomingPacket);
                byte[] data = incomingPacket.getData();
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                ObjectInputStream is = new ObjectInputStream(in);
                try {
                    Objeto student = (Objeto) is.readObject();
                    System.out.println("Student object received = " + student);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                InetAddress IPAddress = incomingPacket.getAddress();
                int port = incomingPacket.getPort();
              
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        } 
    }
}
