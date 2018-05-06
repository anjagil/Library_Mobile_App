package com.example.gilan.libraryapp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.gilan.libraryapp.database.entities.Genre;

import java.util.List;

@Dao
public interface GenreDao {
    @Insert
    void insert(Genre genre);
    @Delete
    public void deleteGenres(Genre... genres);

    @Query("DELETE FROM genres")
    void deleteAll();

    @Query("SELECT * FROM genres ORDER BY name ASC")
    LiveData<List<Genre>> getAllGenres();
}
