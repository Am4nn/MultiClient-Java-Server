package GUI;

import java.util.Scanner;

public class Gui {
    private final Scanner scanner = new Scanner(System.in);
    private String key;

    public Gui (String name) {
        this.key = name;
        System.out.println(this.key + " GUI Initiated");
    }

    public String read (String msg) {
        if (!msg.isEmpty()) {
            System.out.println(msg);
        }
        return scanner.nextLine();
    }

    public void write (String msg) {
        System.out.println(msg);
    }
}
