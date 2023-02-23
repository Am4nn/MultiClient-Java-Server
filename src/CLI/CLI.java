package CLI;

import UI.UI;

import java.util.NoSuchElementException;
import java.util.Scanner;


public class CLI extends UI {
    private final Scanner scanner = new Scanner(System.in);

    public CLI(String name) {
        super();
        System.out.println(name + " GUI Initiated");
    }

    @Override
    public String read (String msg) throws NoSuchElementException {
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
