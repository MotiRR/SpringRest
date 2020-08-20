package com.geekbrains.book.store.utils;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.Genre;
import com.geekbrains.book.store.repositories.specifications.BookSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@Getter
public class BookFilter {
    private Specification<Book> spec, specGenre;
    private String filterParams;
    private Map<String, String> params;

    private void addNewParameters(Map<String, String> paramsFilter, String genres) {
        for(String genre : genres.split(",")) {
            paramsFilter.put(genre, genre);
        }

    }

    public BookFilter(Map<String, String> params, String genres) {
        if(genres != null)
            addNewParameters(params, genres);
        spec = Specification.where(null);
        specGenre = Specification.where(null);
        StringBuilder stringBuilder = new StringBuilder();
        if (params.containsKey("maxPrice") && !params.get("maxPrice").isEmpty()) {
            spec = spec.and(BookSpecifications.priceLesserOrEqualsThan(Integer.parseInt(params.get("maxPrice"))));
            stringBuilder.append("&maxPrice=").append(Integer.parseInt(params.get("maxPrice")));
        }
        if (params.containsKey("minPrice") && !params.get("minPrice").isEmpty()) {
            spec = spec.and(BookSpecifications.priceGreaterOrEqualsThan(Integer.parseInt(params.get("minPrice"))));
            stringBuilder.append("&minPrice=").append(Integer.parseInt(params.get("minPrice")));
        }
        if (params.containsKey("titlePart") && !params.get("titlePart").isEmpty()) {
            spec = spec.and(BookSpecifications.titleLike(params.get("titlePart")));
            stringBuilder.append("&titlePart=").append(params.get("titlePart"));
        }

        for(Genre genre : Genre.values()) {
            if (params.containsKey(genre.getGenre()) && !params.get(genre.getGenre()).isEmpty()) {
                specGenre = specGenre.or(BookSpecifications.genreEquals(genre.getGenre()));
                stringBuilder.append("&gen=").append(params.get(params.get(genre.getGenre())));
            }
        }
        spec = spec.and(specGenre);
        filterParams = stringBuilder.toString();
    }
}
