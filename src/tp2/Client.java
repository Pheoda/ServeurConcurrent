package tp2;

import java.io.IOException;
import java.util.Scanner;


public class Client extends UDP implements Runnable{
    
    //classic connexion
    public Client() {
        super();
    }
    
 
    @Override
    public void run() {
        int answer;
        this.connecter();
        this.joinAGroup("224.0.0.3", 8888);
        while(loop) {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.println("Que voulez-vous faire ?");
                System.out.println("1 - envoyer un message au groupe");
                System.out.println("2 - quitter le groupe");
                System.out.println("3 - envoyer un message au serveur");
                System.out.println("4 - quitter connexion");
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
                    case 3 :
                        Scanner scanMsg = new Scanner(System.in);
                        String msgServeur;
                        System.out.println("Taper votre message\n");
                        msgServeur = scanMsg.nextLine();
                        this.send(msgServeur, ia, portConnexion);
                        break;
                    case 4:
                        loop = false;
                        this.send("FERMETURE", ia, PORT);
                        break;
                }

            } catch (IOException ex) {
                System.err.println("Impossible de quitter le groupe");
               // System.exit(1);
            }
        }
        this.fermer();
    }
    
    public void connecter() {
        this.send("HELLO", ia, PORT);
        this.receive();
        portConnexion = dp.getPort();
        System.out.println(new String(dp.getData()));
    }

    public static void main(String[] args) {
      Client c1 = new Client();
      
      new Thread(c1).start();
      
    }

}
