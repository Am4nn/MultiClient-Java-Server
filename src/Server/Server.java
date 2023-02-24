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
import GUI.GUI;

public class Server {

    Socket socket = null;
    String portNumber;
    private boolean running = false;
    public static GUI ui;

    // for multi-client maintain array for outputStream of each client
    private static final List<ClientHandlerThread> allConnectedClients = new ArrayList<>();
    // these methods make changes in a shared variable, hence they should be synchronized
    synchronized public static void addClient(ClientHandlerThread client) { allConnectedClients.add(client); }
    synchronized public static void removeClient(ClientHandlerThread client) { allConnectedClients.remove(client); }
    synchronized public static void broadcastMessage (String msg) {
        for (ClientHandlerThread client : allConnectedClients) {
            try {
                client.output.writeUTF(msg);
            } catch (IOException e) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    synchronized public static int getTotalActiveClients () { return allConnectedClients.size(); }

    public Server() {
        ui = new GUI("Server");

        portNumber = ui.read("Enter the Port Number where server should run", "3000");

        this.running = true;
        this.startAcceptingConnections();
    }

    private void startAcceptingConnections () {
        int port = Integer.parseInt(this.portNumber);
        try {
            ServerSocket ss = new ServerSocket(port);
            ui.write("Server listening on port: " + port);

            // to listen for server's stdin input and broadcast msg to all clients
            Thread serverInputHandlerThread = new Thread(new ServerInputHandlerThread());
            serverInputHandlerThread.setName("server-stdin-listener-thread");
            serverInputHandlerThread.start();

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
