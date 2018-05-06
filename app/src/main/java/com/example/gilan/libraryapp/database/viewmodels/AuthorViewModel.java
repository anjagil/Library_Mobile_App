package com.example.gilan.libraryapp.database.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.gilan.libraryapp.database.entities.Author;
import com.example.gilan.libraryapp.database.repository.AuthorRepository;

import java.util.List;

public class AuthorViewModel extends AndroidViewModel {
    private AuthorRepository mRepository;
    private LiveData<List<Author>> mAllAuthors;

    public AuthorViewModel(Application application) {
        super(application);
        mRepository = new AuthorRepository(application);
        mAllAuthors = mRepository.getAllAuthors();
    }

    public LiveData<List<Author>> getAllAuthors() { return mAllAuthors; }




    public void delete(Author author) { mRepository.deleteAuthor(author);}

}
