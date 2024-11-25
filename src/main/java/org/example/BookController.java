package org.example;

import java.util.ArrayList;
import java.util.List;

public class BookController {
    private final List<Book> books;

    public BookController() {
        this.books = new ArrayList<>();
    }

    public void addBook(String title, String author, String genre, Float price, int quantityPages) {
        books.add(new Book(title, author, genre, price, quantityPages));
    }

    public List<Book> getBooks() {
        return books;
    }

    public String getBookListAsString() {
        if (books.isEmpty()) {
            return "Brak książek w bazie danych.";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            sb.append(i + 1).append(". ")
                    .append(book.getTitle()).append(" - ").append(book.getAuthor())
                    .append(" (").append(book.getGenre()).append(") - ")
                    .append(book.getPrice()).append(" PLN, ")
                    .append(book.getQuantityPages()).append(" stron\n");
        }
        return sb.toString();
    }
}
