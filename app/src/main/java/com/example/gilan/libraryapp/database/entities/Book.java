package com.example.gilan.libraryapp.database.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "books",
        indices = { @Index(value = { "isbn_number", "title" }) }
//      ,  foreignKeys = @ForeignKey(entity = Genre.class,
//                                    parentColumns = "id",
//                                    childColumns = "genre_id")
        )
public class Book {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    public int genre_id;

    public String author;
    public String isbn_number;

    @NonNull
    public String title;

    public String state;
    public String publisher;
    public int year;
    public int edition;

    public Book() {

    }

    public Book(String isbn, String tytul_, String state_, String publisher_, int year_, String author_, int genre_, int edition_){
        this.isbn_number = isbn;
        this.title = tytul_;
        this.state = state_;
        this.publisher = publisher_;
        this.year = year_;
        this.author = author_;
        this.edition = edition_;
        this.genre_id = genre_;
    }

}
