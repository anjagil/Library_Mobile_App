package com.example.gilan.libraryapp.database.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "genres")
public class Genre {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int genre_id;

    public String name;

    public Genre () { }

    public Genre(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
