package Server;


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
    String name;

    public ClientHandlerThread (Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            this.input = new DataInputStream(socket.getInputStream());
            this.output = new DataOutputStream(socket.getOutputStream());

            // Read some initial data like name, etc
            this.name = input.readUTF().trim();

            ClientDetails clientDetails = new ClientDetails(input, output, socket, name);

            Server.addClient(clientDetails);
            Server.ui.write("Connected" + " -> " + name + ", Active Clients : " + Server.getTotalActiveClients());
            Server.broadcastMessage("Connected" + " -> " + name + ", Active Clients -> " + Server.getTotalActiveClients());

            String cause = "";
            // Read all messages from client and respond
            try {
                String inStr = "";
                while (true) {
                    inStr = input.readUTF();
                    Server.ui.write(inStr);
                    Server.broadcastMessage(inStr);
                }
            } catch (IOException e) {
                cause = e.getMessage();
                if (!e.getMessage().equals("Connection reset")) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            // connection over - cleanup
            Server.removeClient(clientDetails);
            input.close();
            output.close();
            socket.close();
            Server.ui.write("Disconnected" + " -> " + name + ", Cause -> " + cause + ", Active Clients -> " + Server.getTotalActiveClients());
            Server.broadcastMessage("Disconnected" + " -> " + name + ", Cause -> " + cause + ", Active Clients -> " + Server.getTotalActiveClients());
        } catch (IOException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
