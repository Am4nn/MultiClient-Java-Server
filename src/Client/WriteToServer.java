package Client;

import Server.Server;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public record WriteToServer() implements Runnable {

    @Override
    public void run() {

        Client.ui.setSendBtnActionListener(this::sendButtonActionPerformed);

//        String string;
//        try {
//            while (true) {
//                string = Client.ui.read();
//                if (!string.isEmpty()) {
//                    Client.outputStream.writeUTF(Client.name + " -> " + string.trim());
//                }
//            }
//        } catch (IOException e) {
//            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
//        } catch (NoSuchElementException e) {
//            Logger.getLogger(Client.class.getName()).log(Level.INFO, "Server Stdin Closed !");
//        }
    }

    private void sendButtonActionPerformed(ActionEvent evt) {
        try {
            String str = Client.ui.getInputTextAndClear();
            if (!str.trim().isEmpty()) {
                Client.outputStream.writeUTF(Client.name + " -> " + str.trim());
            }
        } catch (IOException e) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
