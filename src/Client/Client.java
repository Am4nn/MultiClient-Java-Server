package Client;

import CLI.CLI;
import UI.UI;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public static Socket socket = null;
    private String ipAddress = "localhost";
    private String portNumber = "3000";
    public static DataInputStream inputStream;
    public static DataOutputStream outputStream;
    public static UI ui;
    public static String name = "newUser";

    public Client () {
        ui = new CLI("Client");
        this.init();
        this.startCommunication();
    }

    private void init() {
        String inIP = ui.read("Input IP Address of Server (default=localhost) :");
        String inPort = ui.read("Input Port Number of Server (default=3000) :");
        String inName = ui.read("Enter your name :");

        if (!inIP.isEmpty()) ipAddress = inIP;
        if (!inPort.isEmpty()) portNumber = inPort;
        if (!inName.isEmpty()) name = inName;

        try {
            socket = new Socket(ipAddress, Integer.parseInt(portNumber));
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void startCommunication() {
        Thread readFromServer = new Thread(new ReadFromServer());
        Thread writeToServer = new Thread(new WriteToServer());

        readFromServer.start();
        writeToServer.start();

        try {
            // wait for threads to die
            readFromServer.join();
            writeToServer.join();
        } catch (InterruptedException e) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
        }

        // after all threads dies - cleanup
        try {
            outputStream.close();
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
