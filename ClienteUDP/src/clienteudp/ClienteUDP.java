package clienteudp;

import static com.oracle.jrockit.jfr.ContentType.Timestamp;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ClienteUDP {

    private int numeroEnvios;

    private String ip;

    private int puerto;

    private DatagramSocket Socket;

    public ClienteUDP() throws SocketException {

        numeroEnvios = 0;

        ip = "127.0.0.1";

        puerto = 1978;
        
        Socket = new DatagramSocket();

    }

    public void setNumeroEnvios(int numeroEnvios) {
        this.numeroEnvios = numeroEnvios;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public void enviarDatos() throws SocketException, UnknownHostException, IOException {
        int cont = 0;
        while (cont < numeroEnvios+1) {
            String timeStamp=""+cont;
            if(cont !=0){
                timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            }
            Objeto obj = new Objeto(cont, timeStamp);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(outputStream);
            os.writeObject(obj);
            byte[] data = outputStream.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(data, data.length, InetAddress.getByName(ip), puerto);
            Socket.send(sendPacket);
            System.out.println("Mensaje enviado");
            cont++;
        }
        System.out.println("Done!");
    }

}
