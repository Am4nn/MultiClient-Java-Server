package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientDetails {
    public String name;
    public Socket socket;
    public DataInputStream inputStream;
    public DataOutputStream outputStream;

    public ClientDetails (DataInputStream inputStream, DataOutputStream outputStream, Socket socket, String name) {
        this.name=name;
        this.socket=socket;
        this.inputStream=inputStream;
        this.outputStream=outputStream;
    }
}
