package com.ben.card.crud.repository;

import com.ben.card.crud.model.NewCreditCardRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewCreditCardRequestDao extends JpaRepository<NewCreditCardRequest, Integer> {
    List<NewCreditCardRequest> findByOib(String oib);

    void deleteByOib(String oib);
}
