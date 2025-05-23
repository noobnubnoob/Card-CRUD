package com.ben.card.crud.repository;

import com.ben.card.crud.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardDao extends JpaRepository<CreditCard, Integer> {
}
