package servidorudp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Conexion extends Thread {

    private String dir;

    public Conexion(String dir) {
        this.dir = dir;
    }

    public void run() {
        while (ServidorUDP.getCurrentClients().contains(dir)) {
            if (ServidorUDP.getData().isEmpty()) {
                ServidorUDP.getCurrentClients().remove(dir);
            } else {
                if (ServidorUDP.getData().get(0).getDir().equals(dir)) {
                    if (ServidorUDP.getData().get(0).getObjeto().getNumeroSecuencia() != 0) {
                        System.out.println("ENTRAMOS");
                        System.out.println("HOLIS " + ServidorUDP.getData().get(0).getObjeto().getNumeroSecuencia());
                        write(ServidorUDP.getData().get(0));
                    }
                    ServidorUDP.getData().remove(ServidorUDP.getData().get(0));
                }
            }
        }
        ServidorUDP.getCurrentClients().remove(dir);
        return;
    }

    public void write(Data data) {
        try {
            File f = new File("./" + dir + ".txt");
            if (f.exists() && !f.isDirectory()) {
                append(data);
            }
            else{
            PrintWriter writer = new PrintWriter("./" + dir + ".txt", "UTF-8");
            writer.println(data.getObjeto().getNumeroSecuencia() + ". ");
            writer.close();
            }
        } catch (IOException e) {
            // do something
        }
    }

    public void append(Data f) {

        BufferedWriter bw = null;
        FileWriter fw = null;

        try {

            String data = f.getObjeto().getNumeroSecuencia() + ". ";

            File file = new File("./" + dir + ".txt");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // true = append file
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);

            bw.write(data);
            bw.newLine();

            System.out.println("Done");

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bw != null) {
                    bw.close();
                }

                if (fw != null) {
                    fw.close();
                }

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }

    }

}
