package org.example;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.*;
import com.googlecode.lanterna.gui2.table.Table;

public class BookView {
    private final MultiWindowTextGUI gui;
    private final BookController controller;

    public BookView(MultiWindowTextGUI gui) {
        this.gui = gui;
        this.controller = new BookController();
    }

// Menu
    public void showMainMenu() {
        Panel panel = new Panel();
        panel.setLayoutManager(new LinearLayout());

        panel.addComponent(new Button("Dodaj książkę", this::addBook));
        panel.addComponent(new Button("Pokaż listę książek", this::showBookList));
        panel.addComponent(new Button("Usuń książkę", this::removeBook));
        panel.addComponent(new Button("Wyjdź", () -> System.exit(0)));

        BasicWindow window = new BasicWindow("Menu Główne");
        window.setComponent(panel);

        gui.addWindowAndWait(window);
    }

// Dodawanie ksiązki
    private void addBook() {
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));

        TextBox titleBox = new TextBox();
        TextBox authorBox = new TextBox();
        TextBox genreBox = new TextBox();
        TextBox priceBox = new TextBox();
        TextBox pagesBox = new TextBox();

        panel.addComponent(new Label("Tytuł:"));
        panel.addComponent(titleBox);

        panel.addComponent(new Label("Autor:"));
        panel.addComponent(authorBox);

        panel.addComponent(new Label("Gatunek:"));
        panel.addComponent(genreBox);

        panel.addComponent(new Label("Cena:"));
        panel.addComponent(priceBox);

        panel.addComponent(new Label("Liczba stron:"));
        panel.addComponent(pagesBox);

//Wyjątek
        BasicWindow window = new BasicWindow("Dodaj Książkę");
        panel.addComponent(new Button("Zapisz", () -> {
            try {
                String title = titleBox.getText();
                String author = authorBox.getText();
                String genre = genreBox.getText();
                Float price = Float.parseFloat(priceBox.getText());
                int pages = Integer.parseInt(pagesBox.getText());
                controller.addBook(title, author, genre, price, pages);
                MessageDialog.showMessageDialog(gui, "Sukces", "Książka dodana pomyślnie!");
                window.close();
            } catch (NumberFormatException e) {
                MessageDialog.showMessageDialog(gui, "Błąd", "Niepoprawne dane!");
            }
        }));

        panel.addComponent(new Button("Anuluj", window::close));
        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }

    //Lista książek
    private void showBookList() {
        Panel panel = new Panel();
        Table<String> table = new Table<>("Tytuł", "Autor", "Gatunek", "Cena", "Strony");

        for (Book book : controller.getBooks()) {
            table.getTableModel().addRow(book.getTitle(), book.getAuthor(), book.getGenre(),
                    book.getPrice().toString(), String.valueOf(book.getQuantityPages()));
        }

        panel.addComponent(table);
        BasicWindow window = new BasicWindow("Lista Książek");
        panel.addComponent(new Button("Powrót", window::close));
        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }

    //Usuwanie ksiązki
    private void removeBook() {
        if (controller.getBooks().isEmpty()) {
            MessageDialog.showMessageDialog(gui, "Informacja", "Brak książek do usunięcia!");
            return;
        }

        Panel panel = new Panel();
        TextBox indexBox = new TextBox();

        panel.addComponent(new Label("Podaj numer książki do usunięcia (od 1 do " + controller.getBooks().size() + "):"));
        panel.addComponent(indexBox);

        BasicWindow window = new BasicWindow("Usuń Książkę");
        panel.addComponent(new Button("Usuń", () -> {
            try {
                int index = Integer.parseInt(indexBox.getText()) - 1;

                if (index < 0 || index >= controller.getBooks().size()) {
                    MessageDialog.showMessageDialog(gui, "Błąd", "Podany numer jest poza zakresem!");
                    return;
                }

                // Usunięcie książki
                controller.removeBook(index);
                MessageDialog.showMessageDialog(gui, "Sukces", "Książka została usunięta!");
                window.close();
            } catch (NumberFormatException e) {
                MessageDialog.showMessageDialog(gui, "Błąd", "Nieprawidłowy numer! Użyj liczby całkowitej.");
            }
        }));
        panel.addComponent(new Button("Anuluj", window::close));
        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }


}
