package com.example.gilan.libraryapp.database.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.gilan.libraryapp.database.entities.Book;
import com.example.gilan.libraryapp.database.repository.BookRepository;

import java.util.List;

public class BookViewModel extends AndroidViewModel{
    private BookRepository mRepository;
    private LiveData<List<Book>> mAllBooks;

    public BookViewModel(Application application) {
        super(application);
        mRepository = new BookRepository(application);
        mAllBooks = mRepository.getAllBooks();
    }

    public LiveData<List<Book>> getAllBooks() { return mAllBooks; }

    public void insert(Book book) { mRepository.insert(book);}


}
