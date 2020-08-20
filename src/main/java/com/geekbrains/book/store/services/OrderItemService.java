package com.geekbrains.book.store.services;

import com.geekbrains.book.store.repositories.OrderItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class OrderItemService {
    private OrderItemRepository orderItemRepo;
}
