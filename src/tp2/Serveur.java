package tp2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;

public class Serveur implements Runnable {

    private int port;

    public Serveur() throws IOException {
        boolean foundPort = false;
        port = 1024;

        while (port < 8888 && !foundPort) {
            ServerSocket test = null;
            try {
                test = new ServerSocket(port);
                foundPort = true;
            } catch (IOException e) {
                test.close();
                foundPort = false;
                port++;
            }
        }
        System.out.println("Serveur initialisé au port : " + port);

    }

    public int getPort() {
        return port;
    }

    @Override
    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket(port);
            
            while (true) {
                byte[] data = new byte[1024];
                DatagramPacket p = new DatagramPacket(data, data.length);
                socket.receive(p);
                
                System.out.println("Adresse : " + p.getAddress());
                System.out.println("Port : " + p.getPort());
                
                new Thread(new Connexion(p.getAddress(), p.getPort())).start();
            }

        } catch (IOException e) {
            System.err.println("Erreur : " + e);
        }
    }
}
