package org.example;

public class Book {
    private String title;
    private String author;
    private String genre;
    private Float price;
    private int quantityPages;

    public Book(String title, String author, String genre, Float price, int quantityPages){
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.quantityPages = quantityPages;
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public String getGenre(){
        return genre;
    }

    public Float getPrice(){
        return price;
    }

    public int getQuantityPages(){
        return quantityPages;
    }

}

