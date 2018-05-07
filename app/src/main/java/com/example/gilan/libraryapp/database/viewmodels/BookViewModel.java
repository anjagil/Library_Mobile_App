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
    }

    public LiveData<List<Book>> getAllBooks(String sortBy, String sortDir) {
        return mAllBooks = mRepository.getAllBooks(sortBy, sortDir);
    }

    public void insert(Book book) { mRepository.insert(book);}

    public void update(Book book) { mRepository.update(book);}

    public LiveData<Book> getBookById(int id) {
        return mRepository.bookById(id);
    }

    public LiveData<List<Book>> searchBooks(String type, String searchValue) {
        return mRepository.searchBooks(type, searchValue);
    }

    public void delete(Book book) { mRepository.deleteBook(book);}

}
