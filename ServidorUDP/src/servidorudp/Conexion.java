package servidorudp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion extends Thread {

    private String dir;

    private int cont;

    private long average;

    private int total;

    public Conexion(String dir) {
        this.dir = dir;
        cont = 0;
        average = 0;
        total = 0;
    }

    public void run() {
        while (ServidorUDP.getCurrentClients().contains(dir)) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (ServidorUDP.getData().isEmpty()) {
                ServidorUDP.getCurrentClients().remove(dir);
            } else {
                if (ServidorUDP.getData().get(0).getDir().equals(dir)) {
                    if (ServidorUDP.getData().get(0).getObjeto().getNumeroSecuencia() != 0) {
                        write(ServidorUDP.getData().get(0));
                        cont++;
                        Data data = ServidorUDP.getData().get(0);
                        if (data.getObjeto().getNumeroSecuencia() == 1) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
                            try {
                                average = (((data.getDate().getTime() - 5) - (sdf.parse(data.getObjeto().getFecha())).getTime()) + average);
                            } catch (ParseException ex) {
                                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        } else {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
                            try {
                                average = (((data.getDate().getTime() - 5) - (sdf.parse(data.getObjeto().getFecha())).getTime()) + average) / 2;
                            } catch (ParseException ex) {
                                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        System.out.println("CONT="+cont+" Y TOTAL ES="+total);
                        if (cont == total) {
                            appendFinal();
                        }
                    } else {
                        System.out.println("ENTRAMOS");
                        Data data = ServidorUDP.getData().get(0);
                        total = Integer.parseInt(ServidorUDP.getData().get(0).getObjeto().getFecha());
                        System.out.println("EL TOTAL ES " + total);
                    }
                    ServidorUDP.getData().remove(ServidorUDP.getData().get(0));
                }
            }
        }

        return;
    }

    public void write(Data data) {
        try {
            File f = new File("./" + dir + ".txt");
            if (f.exists() && !f.isDirectory()) {
                append(data);
            } else {
                PrintWriter writer = new PrintWriter("./" + dir + ".txt", "UTF-8");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
                try {
                    writer.println(data.getObjeto().getNumeroSecuencia() + ". " + ((data.getDate().getTime() - 5) - (sdf.parse(data.getObjeto().getFecha())).getTime())+" ms");
                } catch (ParseException ex) {
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                }
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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
            String data = "";
            try {
                System.out.println("LO RECIBI EL  : "+f.getDate().toString());
                System.out.println("SE MANDO EL : "+sdf.parse(f.getObjeto().getFecha()).toString());
                data = f.getObjeto().getNumeroSecuencia() + ". " + (((f.getDate().getTime() - 5) - (sdf.parse(f.getObjeto().getFecha())).getTime()))+" ms";
            } catch (ParseException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }

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

            System.out.println("Done writing");

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

    public void appendFinal() {

        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
            String data = "// OBJETOS RECIBIDOS: " + cont + " OBJETOS FALTANTES: " + (total - cont) + " TIEMPO PROMEDIO: " + average+" ms";

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

            System.out.println("Done writing");

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
