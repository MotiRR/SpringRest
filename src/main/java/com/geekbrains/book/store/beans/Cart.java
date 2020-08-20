package com.geekbrains.book.store.beans;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.Order;
import com.geekbrains.book.store.entities.OrderItem;
import com.geekbrains.book.store.entities.User;
import com.geekbrains.book.store.exceptions.ResourceNotFoundException;
import com.geekbrains.book.store.services.BookService;
import com.geekbrains.book.store.services.OrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    private BookService bookService;
    private OrderService orderService;

    private List<OrderItem> orderItems;

    public void add(Long bookId) {
        boolean containsBook = false;
        for (OrderItem orderItem : orderItems) {
            Book book = orderItem.getBook();
            if (book.getId().equals(bookId)) {
                containsBook = true;
                orderItem.setCount(orderItem.getCount() + 1);
                orderItem.setAmount(orderItem.getAmount().add(book.getPrice()));
            }
        }

        if (!containsBook) {
            Book book = bookService.findById(bookId);
            orderItems.add(new OrderItem(book, 1, book.getPrice()));
        }
    }

    public void createOrder(User user){
        orderService.createOrder(user, orderItems);
    }

}
