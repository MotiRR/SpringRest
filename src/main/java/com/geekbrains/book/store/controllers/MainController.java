package com.geekbrains.book.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/store")
public class MainController {
    @GetMapping
    public String showHomePage() {
        return "about-page";
    }
}
