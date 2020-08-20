package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.Genre;
import com.geekbrains.book.store.repositories.specifications.BookSpecifications;
import com.geekbrains.book.store.services.BookService;
import com.geekbrains.book.store.utils.BookFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {
    private BookService bookService;
    private final int pageCount = 5;


    @GetMapping
    public String showAllBooks(Model model,
                               @RequestParam(name = "p", defaultValue = "1") Integer pageIndex,
                               @RequestParam Map<String, String> params,
                               @RequestParam(name = "gen", required = false) String genre
    ) {

        BookFilter bookFilter = new BookFilter(params, genre);
        Page<Book> page = bookService.findAll(bookFilter.getSpec(), pageIndex - 1, pageCount);
        model.addAttribute("books", page.getContent());
        model.addAttribute("page", pageIndex);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("filterParams", bookFilter.getFilterParams());
        model.addAttribute("allGenres", Genre.values());
        return "store-page";
    }

    // Эта часть кода будет сильно скорректирована после темы Spring REST
    @GetMapping("/rest")
    @ResponseBody
    @CrossOrigin("*")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }
}
