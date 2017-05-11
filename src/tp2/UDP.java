/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 *
 * @author alexa
 */
public class UDP {
    protected DatagramSocket socket;
    protected InetAddress ia;
    protected DatagramPacket dp;
    //protected static ArrayList<InetAddress> groups = new ArrayList<>();
    protected MulticastSocket ms;
    protected InetAddress group;

    
    final public static String IP = "127.0.0.1";
    final public static int PORT = 8008;
    final public static int TAILLE_DP = 1024;
    
    public UDP() {
        try {
            socket = new DatagramSocket();
            ia = InetAddress.getByName(IP);
        }catch(SocketException ex){
            System.err.println("Erreur ouverture (socket)");
            System.exit(1);
        }catch(UnknownHostException ex) {
            System.err.println("Erreur ouverture (IP)");
            System.exit(1);
        }
    }
    
    public UDP(int port) {
        try {
            socket = new DatagramSocket(port);
        }catch(SocketException ex){
            System.err.println("Erreur ouverture 5 (socket)");
            System.exit(1);
        }
    }

    
    protected void send(String message, InetAddress adress, int port) {
        byte[] data = new byte[TAILLE_DP];
        data = message.getBytes();
        dp = new DatagramPacket(data,data.length, adress, port);
        try {
            socket.send(dp);
        }catch(IOException ex) {
            System.err.println("Erreur envoi DP");
            System.exit(1);
        }
    }
    
    protected void receive() {
        byte[] data = new byte[TAILLE_DP];
        dp = new DatagramPacket(data, data.length);
        try {
            socket.receive(dp);
        }catch(IOException ex) {
            System.err.println("Erreur reception");
            System.exit(1);
        }
    }
    
    public void joinAGroup(String adress, int port) {
        try {
            MulticastSocket ms = new MulticastSocket(port);
            InetAddress group = InetAddress.getByName(adress);
            ms.joinGroup(group);
            this.group = group;
            this.ms = ms;
            this.sendGroup("Hello group");
            this.receiveGroup();
            System.out.println(new String(dp.getData()));
            
            
        } catch (IOException ex) {
            System.err.print("Erreur creation multicast socket");
            System.exit(1);
        }
 
    }
    
    protected void sendGroup(String message) {
        byte[] data = new byte[TAILLE_DP];
        data = message.getBytes();
        dp = new DatagramPacket(data,data.length, group, ms.getLocalPort());
        try {
            socket.send(dp);
        }catch(IOException ex) {
            System.err.println("Erreur envoi DP ms");
            System.exit(1);
        }
    }
    
    protected void receiveGroup() {
        byte[] data = new byte[TAILLE_DP];
        dp = new DatagramPacket(data, data.length);
        try {
            ms.receive(dp);
        }catch(IOException ex) {
            System.err.println("Erreur reception");
            System.exit(1);
        }
    }
    
    protected static int findPortOpen(int start, int end) {
        boolean used;
        DatagramSocket test = null;
	for(int port = start; port <= end ; port++)
        {
            used = false;
            try {
                test = new DatagramSocket(port);
                
            } catch (SocketException e) {
                used = true;
            }
            if (!used) {
                test.close();
                return port;
            }

        }
        return 0;
    }
}
