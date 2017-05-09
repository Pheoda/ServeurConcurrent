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
        
        Client client3 = new Client();
        client3.connecter(InetAddress.getByName("127.0.0.1"), s.getPort());
        client3.envoyer("Message test client 3 au serveur");
        
        client2.envoyer("Autre test client 2");
        client1.envoyer("Autre test client 1");
        client3.envoyer("Autre test client 3");
        
        client1.fermer();
        client2.fermer();
        client3.fermer();
    }
    
}
