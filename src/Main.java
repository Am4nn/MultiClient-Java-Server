import Client.Client;
import GUI.Gui;
import Server.Server;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Gui gui = new Gui("Main");
        String output = gui.read("Run SERVER or CLIENT ?");
        switch (output.toLowerCase(Locale.ROOT)) {
            case "server", "s", "1" -> new Server();
            case "client", "c", "2" -> new Client();
        }
    }
}
