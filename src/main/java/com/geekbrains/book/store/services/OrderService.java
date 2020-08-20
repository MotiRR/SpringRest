package com.geekbrains.book.store.services;

import com.geekbrains.book.store.entities.Order;
import com.geekbrains.book.store.entities.OrderItem;
import com.geekbrains.book.store.entities.User;
import com.geekbrains.book.store.exceptions.ResourceNotFoundException;
import com.geekbrains.book.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

    public Order saveOrUpdate(Order order) {
        return orderRepository.save(order);
    }

    public void createOrder(User user, List<OrderItem> orderItems) {
        Order newOrder = new Order(user);
        for (OrderItem orderItem: orderItems){
            orderItem.setOrder(newOrder);
        }
        newOrder.setOrderItems(orderItems);
        saveOrUpdate(newOrder);
        orderItems.clear();
    }
}
