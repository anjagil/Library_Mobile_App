package com.example.gilan.libraryapp;

public class Books {

    private int edition;
    private String author;
    private String genre;
    private String isbn_number;
    private String title;
    private String state;
    private String publisher;
    private int year;

    public String getIsbn_number() {
        return isbn_number;
    }

    public void setIsbn_number(String isbn_number) {
        this.isbn_number = isbn_number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    //constructor
    public Books(String isbn, String tytul_, String state_, String publisher_, int year_, String author_, String genre_, int edition_){

        this.isbn_number = isbn;
        this.title = tytul_;
        this.state = state_;
        this.publisher = publisher_;
        this.year = year_;
        this.author = author_;
        this.genre = genre_;
        this.edition = edition_;
    }

    public Books( String tytul_, String autor_){

        this.title = tytul_;
        this.publisher = autor_;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }
}
