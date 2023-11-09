package radio.client.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;

public class ClientSocket {
    private final Socket socket;
    private final BufferedReader inputBuffer;
    private final PrintWriter writer;

    public ClientSocket(final Socket socket) throws IOException{
        this.socket = socket;
        System.out.println("Client connected @ "+socket.getRemoteSocketAddress());
        inputBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
    }

    public SocketAddress getRemoteAddress(){
        return socket.getRemoteSocketAddress();
    }

    public void close(){
        try{
            inputBuffer.close();
            writer.close();
            socket.close();
        } catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    public String getMessage(){
        try{
            return inputBuffer.readLine();
        } catch(IOException e){
            System.out.println("Error: "+e.getMessage());
            return null;
        }
    }

    public boolean sendMessage(String msg){
        writer.println(msg);
        return !writer.checkError();
    }
}
