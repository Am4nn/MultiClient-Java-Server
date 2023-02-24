package Server;

import java.awt.event.ActionEvent;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public record ServerInputHandlerThread() implements Runnable {

    @Override
    public void run() {

        Server.ui.setSendBtnActionListener(this::sendButtonActionPerformed);

//        try {
//            String string;
//            while (true) {
//                string = Server.ui.read();
//                if (!string.isEmpty()) {
//                    Server.broadcastMessage("Server -> " + string.trim());
//                }
//            }
//        } catch (NoSuchElementException e) {
//            Logger.getLogger(Server.class.getName()).log(Level.INFO, "Server Stdin Closed !");
//        }
    }

    private void sendButtonActionPerformed(ActionEvent evt) {
        String str = Server.ui.getInputTextAndClear();
        if (!str.trim().isEmpty()) {
            Server.broadcastMessage("Server -> " + str.trim());
        }
    }
}
