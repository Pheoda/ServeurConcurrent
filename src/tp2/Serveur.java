package tp2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Serveur implements Runnable {

    private int port;
    private ArrayList<Connexion> connexions;

    public Serveur() throws IOException {
        boolean foundPort = false;
		  
        connexions = new ArrayList<Connexion>();

        for(port = 1024; port <= 65535 && !foundPort; port++)
        {
            try {
                ServerSocket test = new ServerSocket(port);
                foundPort = true;
                System.out.println("Port trouvé : " + port);
            } catch (IOException e) {
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
                
                //System.out.println("Adresse : " + p.getAddress());
                //System.out.println("Port : " + p.getPort());
                
                Connexion co = new Connexion(p.getAddress(), p.getPort());
                connexions.add(co);
                new Thread(co).start();
            }

        } catch (IOException e) {
            System.err.println("Erreur : " + e);
        }
    }
}
