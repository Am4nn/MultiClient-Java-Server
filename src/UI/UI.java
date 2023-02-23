package UI;

import java.util.NoSuchElementException;

public abstract class UI {
    public abstract String read (String msg) throws NoSuchElementException;
    public abstract void write (String msg);
}
