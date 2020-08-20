package com.geekbrains.book.store.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "publish_year")
    private int publishYear;

    @Basic
    @Column(name = "genre_value")
    private String genreValue;

    @Transient
    private Genre genre;

    @PostLoad
    public void fillTransient() {
        if (!genreValue.isEmpty()) {
            this.genre = Genre.of(genreValue);
        }
    }

    @PrePersist
    public void fillPersistent() {
        if (genre != null) {
            this.genreValue = genre.getGenre();
        }
    }
}
