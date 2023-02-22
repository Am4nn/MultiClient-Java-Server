package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import GUI.Gui;

public class Server {

    Socket socket = null;
    String portNumber = "3000";
    private boolean running = false;
    private final Gui gui;

    // for multi-client maintain array for outputStream of each client
    private static final List<DataOutputStream> allClientStreams = new ArrayList<>();
    // these methods make changes in a shared variable, hence they should be synchronized
    synchronized public static void addClientStream(DataOutputStream stream) { allClientStreams.add(stream); }
    synchronized public static void removeClientStream(DataOutputStream stream) { allClientStreams.remove(stream); }

    synchronized public static void broadcastMessage (String msg) {
        for (DataOutputStream stream : allClientStreams) {
            try {
                stream.writeUTF(msg);
            } catch (IOException e) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public Server() {
        this.gui = new Gui("Server");

        String in = this.gui.read("Enter the Port Number where server should run (default=3000) :");
        if (!in.isEmpty()) portNumber = in;

        this.running = true;
        this.startAcceptingConnections();
    }

    private void startAcceptingConnections () {
        int port = Integer.parseInt(this.portNumber);
        try {
            ServerSocket ss = new ServerSocket(port);
            this.gui.write("Server listening on port: " + port);
            while (this.running) {

                socket = ss.accept();

                Thread clientHandlerThread = new Thread(new ClientHandlerThread(socket, gui));
                clientHandlerThread.start();

            }
        } catch (IOException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
