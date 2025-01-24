package com.sergiocarvajal.prueba_back_iberpixel.Controller;

import com.sergiocarvajal.prueba_back_iberpixel.Model.Book;
import com.sergiocarvajal.prueba_back_iberpixel.Service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Operation(summary = "Get all the books")
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }


    /**
     *
     * @param id received from client's url
     * @return ResponseEntity with: Book if the request is successful (status 200), otherwise, {message: error description} (status 404)
     */
    @Operation(summary = "Get a book from the given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books received successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if(book != null) {
            return ResponseEntity.status(200).body(book);
        }
        else{
            Map<String, String> error = new HashMap<>();
            error.put("message", "Libro no encontrado");
            return ResponseEntity.status(404).body(error);
        }
    }



    @Operation(summary = "Gets books where the given String is exactly the author's name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books received successfully"),
    })
    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String author) {
        return ResponseEntity.status(200).body(bookService.getBooksByAuthor(author));
    }


    @Operation(summary = "Gets books where the given String is contained in the author's name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books received successfully"),
    })
    @GetMapping("/author/contains/{author}")
    public ResponseEntity<List<Book>> getBooksByAuthorContaining(@PathVariable String author) {
        return ResponseEntity.status(200).body(bookService.getBooksByAuthorContaining(author));
    }



    @Operation(summary = "Gets books where the given String is exactly the book's title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books received successfully"),
    })
    @GetMapping("/title/{title}")
    public ResponseEntity<List<Book>> getBooksByTitle(@PathVariable String title) {
        return ResponseEntity.status(200).body(bookService.getBooksByTitle(title));
    }

    @Operation(summary = "Gets books where the given String is contained in the book's titles ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books received successfully"),
    })
    @GetMapping("/title/contains/{title}")
    public ResponseEntity<List<Book>> getBooksByTitleContaining(@PathVariable String title) {
        return ResponseEntity.status(200).body(bookService.getBooksByTitleContaining(title));
    }


    /**
     *
     * @param bookDetails received from client's JSON
     * @return ResponseEntity with: Book object if the request is successful (status 201), otherwise, {message: error description} (status 409)
     */
    @Operation(summary = "Creates a book")
    @Parameters({
            @Parameter(name = "title", required = true),
            @Parameter(name ="author", required = true),
            @Parameter(name="isRead", description = "Boolean. If its empty, false"),
            @Parameter(name="createdAt", description = "Date YYYY-mm-dd. If its empty, today")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book created"),
            @ApiResponse(responseCode = "409", description = "ID already in use")
    })
    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Book bookDetails) {
        if(bookService.createBook(bookDetails) != null) {
            return ResponseEntity.status(201).body(bookService.getBookById(bookDetails.getId()));
        }
        else{
            Map<String, String> error = new HashMap<>();
            error.put("message", "Asegurate que estan todos los campos menos el id: title, author, isRead, (createdAt)");
            return ResponseEntity.status(409).body(error);
        }
    }


    /**
     *
     * @param book received from client's json
     * @return ResponseEntity with: Book object if the request is successful (status 200), otherwise, {message: error description} (status 404)
     */
    @Operation(summary = "Updates the book with the given id and sets the attributes from the request body")
    @Parameters({
            @Parameter(name = "id", required = true),
            @Parameter(name = "title"),
            @Parameter(name ="author"),
            @Parameter(name="isRead"),
            @Parameter(name="createdAt", description = "YYYY-mm-dd")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book updated"),
            @ApiResponse(responseCode = "404", description = "Book not found")

    })
    @PutMapping
    public ResponseEntity<?> updateBook(@RequestBody Book book) {
        if(bookService.updateBook(book) != null) {
            return ResponseEntity.status(200).body(bookService.updateBook(book));
        }
        else{
            Map<String, String> error = new HashMap<>();
            error.put("message", "Libro no encontrado");
            return ResponseEntity.status(404).body(error);
        }

    }


    /**
     *
     * @param id of the book to update
     * @return Book object if the request is successful (status 200) with isRead field as !isRead, otherwise, {message: error description} (status 404)
     */
    @Operation(summary = "Updates the book from the given id and set the isRead field as !isRead")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description = "Book updated"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @PatchMapping("/{id}/read")
    public ResponseEntity<?> updateBookIsRead(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if(book != null) { //Book exist in the database
            book.setIsRead(!book.getIsRead());
            return ResponseEntity.status(200).body(bookService.updateBook(book));
        }
        else{
            Map<String, String> error = new HashMap<>();
            error.put("message", "Libro no encontrado");
            return ResponseEntity.status(404).body(error);
        }

    }


    /**
     *
     * @param id id of the book to delete
     * @return ResponseEntity with: Map &lt;String String&gt;&gt; with the following structure: {message: description}
     */
    @Operation(summary = "Deletes the book from the given id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book deleted"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    //Añadido extra para tener el CRUD completo
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteBook(@PathVariable Long id) {
        Map<String, String> mensaje = new HashMap<>();
        if(bookService.getBookById(id) != null) {
            bookService.deleteBook(id);
            mensaje.put("message", "Libro eliminado con exito");
            return ResponseEntity.status(200).body(mensaje);
        }
        else{
            mensaje.put("message", "No existe libro con ese id");
            return ResponseEntity.status(404).body(mensaje);
        }
    }


}
