package Client;

import GUI.Gui;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public record WriteToServer() implements Runnable {

    @Override
    public void run() {
        String string;
        try {
            while (true) {
                string = Client.gui.read("");
                Client.outputStream.writeUTF(Client.name + " -> " + string.trim());
            }
        } catch (IOException e) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
