package com.sergiocarvajal.prueba_back_iberpixel.Service;

import com.sergiocarvajal.prueba_back_iberpixel.Model.Book;
import com.sergiocarvajal.prueba_back_iberpixel.Repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class BookService {

    @Autowired
    private IBookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(long id){
        return  bookRepository.findById(id).orElse(null); //.orElse(null) to avoid using Optional objects
    }

    public List<Book> getBooksByAuthor(String author){
        return bookRepository.findBooksByAuthor(author);
    }

    public List<Book> getBooksByAuthorContaining(String title){
        return bookRepository.findBooksByAuthorContaining(title);
    }


    public List<Book> getBooksByTitle(String title){
        return bookRepository.findBooksByTitle(title);
    }

    public List<Book> getBooksByTitleContaining(String title){
        return bookRepository.findBooksByTitleContaining(title);
    }

    public Book createBook(Book book){
        if(book.getId() == null){ //id is not on the body from the client
            if(book.getAuthor() == null || book.getTitle() == null){
                return null;
            }
            if(book.getCreatedAt() == null || book.getCreatedAt().isAfter(LocalDate.now())){ //date is not specified or is after today. Sets creation date as today
                book.setCreatedAt(LocalDate.now());
            }
            if(book.getIsRead() == null){
                book.setIsRead(false);
            }
            return bookRepository.save(book);
        }
        return null;
    }

    public Book updateBook(Book book){
        if(book.getId() == null){
            return null;
        }
        Book dbBook = this.getBookById(book.getId()); //Book to modify
        if(dbBook != null){ //Checks if the book exists in the database
            if(book.getTitle() != null){
                dbBook.setTitle(book.getTitle());
            }
            if(book.getAuthor() != null){
                dbBook.setAuthor(book.getAuthor());
            }
            if(book.getIsRead() != null){
                dbBook.setIsRead(book.getIsRead());
            }
            if(book.getCreatedAt() != null){
                dbBook.setCreatedAt(book.getCreatedAt());
            }
            return bookRepository.save(dbBook);
        }
        else{
            return null;
        }
    }

    public void deleteBook(long id){
        bookRepository.deleteById(id);
    }

}
