import Client.Client;
import CLI.CLI;
import GUI.GUI;
import Server.Server;


public class Main {
    public static void main(String[] args) {
        int option = GUI.selectClientOrServer();
        if (option == 0) new Server();
        else if (option == 1) new Client();
        else System.out.println("Good Bye");
    }
}
