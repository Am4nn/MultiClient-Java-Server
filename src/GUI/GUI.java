package GUI;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;

public class GUI {

    String name;
    MyFrame frame;

    public String getInputTextAndClear() {
        String str = frame.sendArea.getText();
        frame.sendArea.setText("");
        return str;
    }

    public void setSendBtnActionListener(ActionListener listener) {
        frame.sendButton.addActionListener(listener);
    }

    public GUI (String name) {
        this.name = name;
        frame = new MyFrame(name);
    }

    public static int selectClientOrServer() {
        String[] options = {"Server", "Client"};
        return JOptionPane.showOptionDialog(null, "Run Client or Server ?", "Java Socket Programming", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, 0);
    }

    public String read(String msg) throws NoSuchElementException {
        if (msg.isEmpty()) return this.read();
        String option = "";
        while (option.isEmpty()) {
            option = JOptionPane.showInputDialog(null, msg);
        }
        return option;
    }

    public String read() throws NoSuchElementException {
        return null;
    }

    public String read(String msg, String defaultValue) throws NoSuchElementException {
        String a = JOptionPane.showInputDialog(null, msg, defaultValue);
        if (a == null) System.exit(0);
        return a;
    }

    public void write(String msg) {
        frame.convoArea.append(msg+"\n");
    }
}
