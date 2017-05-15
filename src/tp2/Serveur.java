package tp2;

public class Serveur extends UDP implements Runnable{


    public Serveur() {
        super(PORT);
        
        System.out.println("Serveur initialisé !");

    }
    

    @Override
    public void run() {

            while (true) {
                this.receive();
                System.out.println("Nouveau Client sur " + dp.getAddress() + ":" + dp.getPort());
                System.out.println(new String(dp.getData()));
                
                new Thread(new Connexion(dp.getAddress(), dp.getPort())).start();
                
            }

        
    }
    
   public static void main(String[] args) {
       Serveur s = new Serveur();
        new Thread(s).start();
   }
}
