package tp2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    private InetAddress ip;
    private int port;
    private DatagramSocket socket;
    
    public Client() {

    }
    
    public void connecter(InetAddress adresse, int port) throws IOException {
        socket = new DatagramSocket();
        byte[] data = new byte[512];
        socket.send(new DatagramPacket(data, data.length, adresse, port));
        
        //System.out.println(socket.getLocalAddress() + " : " + socket.getLocalPort());
        //System.out.println("Socket client connexion envoyée");
        DatagramPacket p = new DatagramPacket(data, data.length);
        socket.receive(p);
        this.ip = p.getAddress();
        this.port = p.getPort();
        System.out.println(new String(data));
    }

    public void envoyer(String message) throws IOException {
        byte[] data = message.getBytes();
        socket.send(new DatagramPacket(data, data.length, this.ip, this.port));
        //System.out.println("Socket client envoyée");
    }
    
    public void fermer() throws IOException {
        socket.close();
        System.out.println("Fin client !");
    }
    
    public void main(String[] args) throws IOException {
        // Demande message à envoyer
        String message = "";
        do
        {
            
            System.out.println("Envoi message (STOP pour arreter): ");
        }while(message != "STOP");
        fermer();
    }

}
