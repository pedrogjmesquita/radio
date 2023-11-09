package radio.client;

import radio.client.pages.LoginScreen;
import radio.client.pages.MainScreen;
import radio.client.utils.Client;

public class App{
    public static void main(String[] args){
        Client client = new Client();        
        LoginScreen login = new LoginScreen(client);
        while (!login.isLoggedIn()) { 
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        MainScreen screen = new MainScreen(client);
    }

}
