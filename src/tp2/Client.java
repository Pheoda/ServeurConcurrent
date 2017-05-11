package tp2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client extends UDP implements Runnable{
    
    private InetAddress addCo;
    private int portCo;
    //classic connexion
    public Client() {
        super();
    }
    
 
    @Override
    public void run() {
        int answer;
        this.connecter();
        this.joinAGroup("224.0.0.3", 8888);
        while(true) {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.println("Que voulez-vous faire ?");
                System.out.println("1 - envoyer un message au groupe");
                System.out.println("2 - quitter le groupe");
                answer = scan.nextInt();
                switch(answer) {
                    case 1 :
                        Scanner scan2 = new Scanner(System.in);
                        String msg;
                        System.out.println("Taper votre message\n");
                        msg = scan2.nextLine();
                        this.sendGroup(msg);
                        this.receiveGroup();
                        break;
                    case 2 :
                        this.ms.leaveGroup(this.group);
                        break;
          
                }

            } catch (IOException ex) {
                System.err.println("Impossible de quitter le groupe");
               // System.exit(1);
            }
        }
    }
    
    public void connecter() {
        this.send("HELLO", ia, PORT);
        this.receive();
        System.out.println(new String(dp.getData()));
    }
    
    
    public void fermer() throws IOException {
        socket.close();
        System.out.println("Fin client !");
    }
    
   
    
    public static void main(String[] args) {
      Client c1 = new Client();
      
      new Thread(c1).start();
      
    }

}
