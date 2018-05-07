package com.example.gilan.libraryapp.database.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "authors")
public class Author {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int author_id;

    public String name;
    public String surname;

    public Author() {

    }

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return this.name + " " + this.surname;
    }
}
