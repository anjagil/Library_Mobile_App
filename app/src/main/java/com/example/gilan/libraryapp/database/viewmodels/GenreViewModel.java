package com.example.gilan.libraryapp.database.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.gilan.libraryapp.database.entities.Genre;
import com.example.gilan.libraryapp.database.repository.GenreRepository;

import java.util.List;

public class GenreViewModel extends AndroidViewModel{
    private GenreRepository mRepository;
    private LiveData<List<Genre>> mAllGenres;

    public GenreViewModel(Application application) {
        super(application);
        mRepository = new GenreRepository(application);
        mAllGenres = mRepository.getAllGenres();
    }

    public LiveData<List<Genre>> getAllGenres() { return mAllGenres; }

    public void insert(Genre genre) { mRepository.insert(genre);}

    public void delete(Genre genre) { mRepository.deleteGenre(genre);}

}
