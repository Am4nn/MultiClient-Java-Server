package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import CLI.CLI;
import UI.UI;

public class Server {

    Socket socket = null;
    String portNumber = "3000";
    private boolean running = false;
    public static UI ui;

    // for multi-client maintain array for outputStream of each client
    private static final List<ClientDetails> allConnectedClients = new ArrayList<>();
    // these methods make changes in a shared variable, hence they should be synchronized
    synchronized public static void addClient(ClientDetails client) { allConnectedClients.add(client); }
    synchronized public static void removeClient(ClientDetails client) { allConnectedClients.remove(client); }
    synchronized public static void broadcastMessage (String msg) {
        for (ClientDetails client : allConnectedClients) {
            try {
                client.outputStream.writeUTF(msg);
            } catch (IOException e) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    synchronized public static int getTotalActiveClients () { return allConnectedClients.size(); }

    public Server() {
        ui = new CLI("Server");

        String in = ui.read("Enter the Port Number where server should run (default=3000) :");
        if (!in.isEmpty()) portNumber = in;

        this.running = true;
        this.startAcceptingConnections();
    }

    private void startAcceptingConnections () {
        int port = Integer.parseInt(this.portNumber);
        try {
            ServerSocket ss = new ServerSocket(port);
            ui.write("Server listening on port: " + port);

            // to listen for server's stdin input and broadcast msg to all clients
            Thread serverStdinHandlerThread = new Thread(new ServerStdinHandlerThread());
            serverStdinHandlerThread.setName("server-stdin-listener-thread");
            serverStdinHandlerThread.start();

            while (this.running) {

                socket = ss.accept();
                // to listen for clients requests
                Thread clientHandlerThread = new Thread(new ClientHandlerThread(socket));
                clientHandlerThread.setName("client-thread-" + getTotalActiveClients());
                clientHandlerThread.start();

            }
        } catch (IOException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
        } catch ( NoSuchElementException e) {
            Logger.getLogger(Server.class.getName()).log(Level.INFO, "Server Stdin Closed !");
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
