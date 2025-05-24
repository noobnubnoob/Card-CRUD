package com.ben.card.crud.service;

import com.ben.card.crud.model.NewCreditCardRequest;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface NewCreditCardRequestService {


    List<NewCreditCardRequest> findAll();

    List<NewCreditCardRequest> findByOib(String oib);

    Optional<NewCreditCardRequest> findByRequestId(int i);

    void createCardRequest(String firstName, String lastName, String oib, String status);
}
