import Client.Client;
import CLI.CLI;
import Server.Server;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        CLI CLI = new CLI("Main");
        String output = CLI.read("Run SERVER or CLIENT ?");
        switch (output.toLowerCase(Locale.ROOT)) {
            case "server", "s", "1" -> new Server();
            case "client", "c", "2" -> new Client();
        }
    }
}
