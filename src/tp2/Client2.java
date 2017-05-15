package tp2;

import java.util.logging.Level;
import java.util.logging.Logger;
import static tp2.UDP.PORT;


public class Client2 extends UDP implements Runnable{
    
    //classic connexion
    public Client2() {
        super();
    }
    
 
   @Override
    public void run() {
        this.connecter();
        this.joinAGroup("224.0.0.3", 8888);
        System.out.println("Client is running...");
        while(true) {
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
        
    public static void main(String[] args) {
      Client2 c1 = new Client2();
      
      new Thread(c1).start();
      
    }

}
