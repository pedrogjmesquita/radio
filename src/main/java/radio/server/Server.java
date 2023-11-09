package radio.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;


public class Server{
    public static final String adress = "127.0.0.1";
    public static final int port = 4000;
    private ServerSocket serverSocket;
    private final List<ClientSocket> clients = new LinkedList<>();


    public void start() throws IOException{
        serverSocket = new ServerSocket(port);
        System.out.println("Server started @ "+port);
        clientConectionLoop();
    }

    private void clientConectionLoop() throws IOException{
        System.out.println("Waiting for clients...");
        while(true){
            ClientSocket clientSocket = new ClientSocket(serverSocket.accept());
            clients.add(clientSocket);
            new Thread(() -> clientInteractionLoop(clientSocket)).start();
        }
    }

    public void clientInteractionLoop(ClientSocket clientSocket){
        clientCredentialsLoop(clientSocket);
    }


    public void clientCredentialsLoop(ClientSocket clientSocket){
        String[] msg;
        try{
;
            while((msg = clientSocket.getMessage().split("  ")) != null){
                String res = DatabaseQuery.buscaDB("Select password from credenciais where username = '"+msg[0]+"'", "password");
                if(res.equals(msg[1])){
                    System.out.println("Client "+clientSocket.getRemoteAddress()+" logged in");
                    sendMsg("true",clientSocket);
                    break;
                } else{
                    System.out.println("Client "+clientSocket.getRemoteAddress()+" failed to log in");
                    sendMsg("false",clientSocket);
                }
                
            }
        } finally{
            clientMessageLoop(clientSocket);;
        }
    }

    public void clientMessageLoop(ClientSocket clientSocket){
        String msg;
        try{
            while((msg = clientSocket.getMessage()) != null){
                System.out.println("Frequency desired by client "+clientSocket.getRemoteAddress()+" : "+msg);
                String nome = DatabaseQuery.buscaDB("Select nome from frequencias where freq = '"+msg+"'", "nome");
                String path = DatabaseQuery.buscaDB("Select path from frequencias where freq = '"+msg+"'", "path");
                String output = nome+"  "+path;
                sendMsg(output,clientSocket);
            }
        } finally{
            clientSocket.close();
        }
    }

    private void sendMsg(String string, ClientSocket clientSocket) {
        clientSocket.sendMessage(string);
    }

    public static void main(String args[]){
        System.out.println("VVV Server Console VVV");
        try{
            Server server = new Server();
            server.start();
        } catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
        System.out.println("^^^ Server Closed ^^^");
    }
}