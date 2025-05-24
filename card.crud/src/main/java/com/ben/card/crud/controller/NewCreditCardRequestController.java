package com.ben.card.crud.controller;

import com.ben.card.crud.dto.NewCardRequest;
import com.ben.card.crud.model.NewCreditCardRequest;
import com.ben.card.crud.service.NewCreditCardRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class NewCreditCardRequestController {

    @Autowired
    NewCreditCardRequestService cardRequestService;

    @RequestMapping(value = "/client-cards/{oib}", method = RequestMethod.GET)
    public List<NewCardRequest> clientCardInformation(@PathVariable String oib) {

        List<NewCreditCardRequest> creditCardRequests = cardRequestService.findByOib(oib);
        List<NewCardRequest> cardRequests = creditCardRequests.stream().map(
                x -> new NewCardRequest(x.getFirstName(), x.getLastName(), x.getStatus(), x.getOib())).
                collect(Collectors.toList());
        return cardRequests;
    }
}
