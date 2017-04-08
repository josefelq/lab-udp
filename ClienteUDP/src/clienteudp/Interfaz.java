package clienteudp;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Interfaz extends JFrame implements ActionListener{

    private JButton send;

    private JTextField port;

    private JTextField ip;

    private JTextField number;
    
    private ClienteUDP cliente;
    
    private JLabel lport;
    
    private JLabel lip;
    
    private JLabel lnumber;

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
        Interfaz interfaz = new Interfaz();
        interfaz.setVisible(true);
        /*
           ClienteUDP cliente=new ClienteUDP();
           cliente.setNumeroEnvios(1000);
           cliente.enviarDatos();  
           
           ClienteUDP cliente2=new ClienteUDP();
           cliente2.setNumeroEnvios(500);
           cliente2.enviarDatos();  
           /*
           ClienteUDP cliente3=new ClienteUDP();
           cliente3.setNumeroEnvios(30);
           cliente3.enviarDatos();  
         */
    }

    public Interfaz() throws SocketException {
        super();
        setTitle("Cliente UDP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2));
        setSize(500, 500);
        ip=new JTextField();
        port=new JTextField();
        number=new JTextField();
        lip=new JLabel("IP");
        lport=new JLabel("PORT");
        lnumber=new JLabel("#OBJECTS");
        send = new JButton("send");
        
        add(lip);
        add(ip);
        
        add(lport);
        add(port);
        
        add(lnumber);
        add(number);
        
        add(send);
        send.addActionListener(this);
        cliente=new ClienteUDP();
        cliente.start();
    }


    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        cliente.setNumeroEnvios(Integer.parseInt(number.getText()));
        cliente.setPuerto(Integer.parseInt(port.getText()));
        cliente.setIp(ip.getText());
        try {  
            cliente.enviarDatos();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    


}
