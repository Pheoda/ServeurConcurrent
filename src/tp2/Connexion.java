package tp2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connexion implements Runnable {

    DatagramSocket socket;

    public Connexion(InetAddress adresse, int port) throws IOException {
        socket = new DatagramSocket();
        byte[] data = "Connexion client".getBytes();
        socket.send(new DatagramPacket(data, data.length, adresse, port));
    }

    @Override
    public void run() {
        while(true) {
            byte[] data = new byte[1024];
            
            try {
                socket.receive(new DatagramPacket(data, data.length));
            } catch (IOException ex) {
                Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(new String(data));
        }
    }
}
