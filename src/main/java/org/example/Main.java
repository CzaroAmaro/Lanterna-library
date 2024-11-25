package org.example;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.terminal.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Ustawienie terminala i ekranu
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();

        // Tworzenie interfejsu
        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen);

        // Tworzenie widoku
        BookView bookView = new BookView(gui);

        // Start aplikacji
        bookView.showMainMenu();
    }
}
