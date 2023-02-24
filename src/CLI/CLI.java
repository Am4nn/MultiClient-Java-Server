package CLI;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class CLI {
    private final static Scanner scanner = new Scanner(System.in);

    public CLI(String name) {
        super();
        System.out.println(name + " CLI Initiated");
    }

    public static int selectClientOrServer() {
        System.out.println("Run SERVER or CLIENT ?");
        String output = scanner.nextLine();
        int option = -1;
        switch (output.toLowerCase(Locale.ROOT)) {
            case "server", "s", "1" -> option = 0;
            case "client", "c", "2" -> option = 1;
        }
        return option;
    }

    public String read (String msg) throws NoSuchElementException {
        if (!msg.isEmpty()) {
            System.out.println(msg);
        }
        return scanner.nextLine();
    }

    public String read() throws NoSuchElementException {
        return scanner.nextLine();
    }

    public String read(String msg, String defaultValue) throws NoSuchElementException {
        String in =  this.read(msg + " (default="+ defaultValue +") :");
        if (!in.isEmpty()) return in;
        return defaultValue;
    }

    public void write (String msg) {
        System.out.println(msg);
    }
}
