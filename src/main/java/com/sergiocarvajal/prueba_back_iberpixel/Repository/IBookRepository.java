package com.sergiocarvajal.prueba_back_iberpixel.Repository;

import com.sergiocarvajal.prueba_back_iberpixel.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {

    List<Book> findBooksByAuthor(String author);
    List<Book> findBooksByAuthorContaining(String author);

    List<Book> findBooksByTitle(String title);
    List<Book> findBooksByTitleContaining(String title);
}
