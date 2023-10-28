package com.pacifique.book;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("books")
@AllArgsConstructor
@Tag(name = "Books Controller")
public class BookController {
    private final IBookRepository bookRepository;

    @PostMapping
    public Book saveBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping
    public List<Book> findBooks() {
        return bookRepository.findAll();
    }


    @SneakyThrows
    @GetMapping("/{id}")
    public Book findBook(@PathVariable long id) {
        return bookRepository.findById(id).orElseThrow(() -> new Exception("Book not available"));
    }

}
