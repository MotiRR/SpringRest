package com.geekbrains.book.store.entities;

import java.util.stream.Stream;

public enum Genre {
    FANTASY("Фэнтези"), FANTASTIC("Фантастика"), DETECTIVE("Детектив");
    private String genre;
    Genre(String genre){
        this.genre = genre;
    }
    public String getGenre(){ return genre;}

    public static Genre of(String genre) {
        return Stream.of(Genre.values())
                .filter(g -> g.getGenre().equals(genre))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
