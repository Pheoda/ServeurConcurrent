package tp2;

import java.io.IOException;
import java.net.InetAddress;

public class TP2 {

    public static void main(String[] args) throws IOException {
        Serveur s = new Serveur();
        new Thread(s).start();
        Client client1 = new Client();
        client1.connecter(InetAddress.getByName("127.0.0.1"), s.getPort());
        client1.envoyer("Message test client 1");
        Client client2 = new Client();
        client2.connecter(InetAddress.getByName("127.0.0.1"), s.getPort());
        client2.envoyer("Message test client 2");
        
        Serveur s2 = new Serveur();
        new Thread(s2).start();
        Client client3 = new Client();
        client3.connecter(InetAddress.getByName("127.0.0.1"), s2.getPort());
        client3.envoyer("Message test client 3 au serveur 2");
        
        client2.envoyer("Autre test client 2");
        client1.envoyer("Autre test client 1");
        client3.envoyer("Autre test client 3");
    }
    
}
