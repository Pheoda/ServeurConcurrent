package tp2;

import java.io.IOException;
import java.net.InetAddress;

public class TP2 {

    public static void main(String[] args) throws IOException {
        new Thread(new Serveur()).start();
        Client client1 = new Client();
        client1.connecter(InetAddress.getByName("127.0.0.1"), 1024);
        client1.envoyer("Message test bite");
        Client client2 = new Client();
        client2.connecter(InetAddress.getByName("127.0.0.1"), 1024);
        client2.envoyer("Message test client 2 mdr");
    }
    
}
