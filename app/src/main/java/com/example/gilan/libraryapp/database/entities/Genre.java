package com.example.gilan.libraryapp.database.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Genre {
    @PrimaryKey
    @NonNull
    public int id;

    public String name;
}
