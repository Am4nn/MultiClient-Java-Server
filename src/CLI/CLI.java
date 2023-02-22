package CLI;

import UI.UI;
import java.util.Scanner;


public class CLI extends UI {
    private final Scanner scanner = new Scanner(System.in);
    private String key;

    public CLI(String name) {
        super();
        this.key = name;
        System.out.println(this.key + " GUI Initiated");
    }

    @Override
    public String read (String msg) {
        if (!msg.isEmpty()) {
            System.out.println(msg);
        }
        return scanner.nextLine();
    }

    @Override
    public void write (String msg) {
        System.out.println(msg);
    }
}
