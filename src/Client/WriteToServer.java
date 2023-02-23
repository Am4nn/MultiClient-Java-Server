package Client;

import Server.Server;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public record WriteToServer() implements Runnable {

    @Override
    public void run() {
        String string;
        try {
            while (true) {
                string = Client.ui.read("");
                if (!string.isEmpty()) {
                    Client.outputStream.writeUTF(Client.name + " -> " + string.trim());
                }
            }
        } catch (IOException e) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
        } catch (NoSuchElementException e) {
            Logger.getLogger(Client.class.getName()).log(Level.INFO, "Server Stdin Closed !");
        }
    }
}
