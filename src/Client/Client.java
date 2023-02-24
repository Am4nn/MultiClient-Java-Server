package Client;

import CLI.CLI;
import GUI.GUI;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public static Socket socket = null;
    String ipAddress;
    String portNumber;
    public static DataInputStream inputStream;
    public static DataOutputStream outputStream;
    public static GUI ui;
    public static String name;

    public Client () {
        ui = new GUI("Client");
        this.init();
        this.startCommunication();
    }

    private void init() {
        ipAddress = ui.read("Input IP Address of Server", "localhost");
        portNumber = ui.read("Input Port Number of Server", "3000");
        name = ui.read("Enter your name :");

        try {
            socket = new Socket(ipAddress, Integer.parseInt(portNumber));
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());

            // send client name and some other imp details if required
            outputStream.writeUTF(name);

            ui.write("Client Connected with Server on "+ipAddress+":"+portNumber);

        } catch (IOException e) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
            ui.write(e.getMessage());
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
