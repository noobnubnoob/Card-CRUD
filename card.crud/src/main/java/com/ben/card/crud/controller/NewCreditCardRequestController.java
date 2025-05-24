package com.ben.card.crud.controller;

import com.ben.card.crud.dto.NewCardRequest;
import com.ben.card.crud.model.NewCreditCardRequest;
import com.ben.card.crud.service.NewCreditCardRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1")
public class NewCreditCardRequestController {

    @Autowired
    NewCreditCardRequestService cardRequestService;

    @RequestMapping(value = "/client-cards/{oib}", method = RequestMethod.GET)
    public List<NewCardRequest> getCreditCardRequestByOib(@PathVariable String oib) {

        List<NewCreditCardRequest> creditCardRequests = cardRequestService.findByOib(oib);
        List<NewCardRequest> cardRequests = creditCardRequests.stream().map(
                x -> new NewCardRequest(x.getFirstName(), x.getLastName(), x.getStatus(), x.getOib())).
                collect(Collectors.toList());
        return cardRequests;
    }

    @RequestMapping(value = "/client-cards/{oib}", method = RequestMethod.DELETE)
    public void deleteCardRequestByOib(@PathVariable String oib) {
        cardRequestService.deleteByOib(oib);
    }

    @RequestMapping(value = "/card-request", method = RequestMethod.POST)
    public ResponseEntity<Void> createNewCard(@RequestBody NewCardRequest cardRequest) {
        cardRequestService.createCardRequest(cardRequest.getFirstName(), cardRequest.getLastName(),
                cardRequest.getOib(), cardRequest.getStatus());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
