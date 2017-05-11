package tp2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static tp2.UDP.PORT;


public class Client2 extends UDP implements Runnable{
    
    private InetAddress addCo;
    private int portCo;
    //classic connexion
    public Client2() {
        super();
    }
    
 
   @Override
    public void run() {
        this.connecter();
        this.joinAGroup("224.0.0.3", 8888);
        while(true) {
            System.out.println("Client is running...");
            this.receiveGroup();
            System.out.println("message du groupe : " + new String(dp.getData()));
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void connecter() {
        this.send("HELLO", ia, PORT);
        this.receive();
        System.out.println(new String(dp.getData()));
        
      /*  System.out.println("port " + p.getPort());
        System.out.println(new String(data));*/
    }
    
    
    public void fermer() throws IOException {
        socket.close();
        System.out.println("Fin client !");
    }
    
   
    
    public static void main(String[] args) {
      Client2 c1 = new Client2();
      
      new Thread(c1).start();
      
    }

}
