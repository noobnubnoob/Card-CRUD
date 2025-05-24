package com.ben.card.crud.repository;

import com.ben.card.crud.model.NewCreditCardRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewCreditCardRequestDao extends JpaRepository<NewCreditCardRequest, Integer> {
}
