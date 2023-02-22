package Client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public record ReadFromServer() implements Runnable {

    @Override
    public void run() {
        String string;
        try {
            while (true) {
                string = Client.inputStream.readUTF();
                Client.ui.write(string);
            }
        } catch (IOException e) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
