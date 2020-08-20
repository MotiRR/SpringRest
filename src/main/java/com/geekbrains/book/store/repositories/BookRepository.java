package com.geekbrains.book.store.repositories;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.dto.BookDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    Book findOneByTitle(String title); // ... where b.title = ?1
    Book findOneByTitleAndPublishYear(String title, int year); // ... where b.title = ?1 and b.publish_year = ?2
    List<Book> findAllByTitleStartingWith(String titleStartingWith);

    @Query("delete from Book b where b.publishYear < 2000")
    void deleteAllBooksOlder2000();

    void deleteAllByPublishYearLessThan(int year);

    List<BookDto> findAllBy();
}
