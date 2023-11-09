package radio.client.utils;

import java.io.IOException;
import java.net.Socket;

public class Client {
    
    private ClientSocket clientSocket;
    private final static String SERVERADDRESS = "127.0.0.1";
    private final static int SERVERPORT = 4000;
    
    public Client(){
        try{
            start();
        } catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    public void start() throws IOException{
        clientSocket = new ClientSocket(new Socket(SERVERADDRESS, SERVERPORT));
        System.out.println("Connected to server @ "+SERVERADDRESS+":"+SERVERPORT);
    }

    public void sendMessage(String msg){
        clientSocket.sendMessage(msg);
    }

    public String getMessage(){
        return clientSocket.getMessage();
    }
}
