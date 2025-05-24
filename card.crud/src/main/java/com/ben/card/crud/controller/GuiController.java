package com.ben.card.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuiController {
    @GetMapping("/card-ui")
    public String showCardRequestUI() {
        return "card_requests"; // renders card_requests.html
    }
}
