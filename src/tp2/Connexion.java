package tp2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connexion extends UDP implements Runnable {

    public Connexion(InetAddress address, int port) {
        super(findPortOpen(1024, 65535));
        this.send("OLLEH", address, port);
    }

    @Override
    public void run() {
        while(loop) {
            byte[] data = new byte[1024];
            
            try {
                socket.receive(new DatagramPacket(data, data.length));
            } catch (IOException ex) {
                Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(new String(dp.getData()) == "FERMETURE")
                loop = false;
            else
                System.out.println(new String(data));
        }
        this.fermer();
    }
}
