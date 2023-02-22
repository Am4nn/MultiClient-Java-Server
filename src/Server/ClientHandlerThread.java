package Server;

import GUI.Gui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandlerThread implements Runnable {

    Socket socket;
    DataInputStream input;
    DataOutputStream output;
    Gui gui;

    public ClientHandlerThread (Socket socket, Gui gui) {
        this.socket = socket;
        this.gui = gui;
    }

    @Override
    public void run() {
        try {
            this.input = new DataInputStream(socket.getInputStream());
            this.output = new DataOutputStream(socket.getOutputStream());

            Server.addClientStream(output);
            String hostName = Integer.toString((int)(Math.floor(Math.random()*1000) + 1));
            gui.write("Connected" + " -> " + hostName);

            // Read all messages from client and respond
            try {
                String inStr = "";
                while (true) {
                    inStr = input.readUTF();
                    gui.write(inStr);
                    Server.broadcastMessage(inStr);
                }
            } catch (IOException e) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
            }

            // connection over - cleanup
            Server.removeClientStream(output);
            input.close();
            output.close();
            socket.close();
            gui.write("Disconnected" + " -> " + hostName);
        } catch (IOException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
