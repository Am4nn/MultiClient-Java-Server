package Server;

import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public record ServerStdinHandlerThread() implements Runnable {

    @Override
    public void run() {
        try {
            String string;
            while (true) {
                string = Server.ui.read("");
                if (!string.isEmpty()) {
                    Server.broadcastMessage("Server -> " + string.trim());
                }
            }
        } catch (NoSuchElementException e) {
            Logger.getLogger(Server.class.getName()).log(Level.INFO, "Server Stdin Closed !");
        }
    }
}
