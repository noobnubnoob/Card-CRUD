package com.ben.card.crud.service;

import com.ben.card.crud.dto.NewCardRequest;
import com.ben.card.crud.model.NewCreditCardRequest;
import com.ben.card.crud.repository.NewCreditCardRequestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class NewCreditCardRequestServiceImpl implements NewCreditCardRequestService {

    @Autowired
    private NewCreditCardRequestDao newCreditCardRequestDao;

    @Override
    public List<NewCreditCardRequest> findAll() {
        List<NewCreditCardRequest> cardRequestList = newCreditCardRequestDao.findAll();

        return cardRequestList;
    }

    @Override
    public List<NewCreditCardRequest> findByOib(String oib) {
        return newCreditCardRequestDao.findByOib(oib);
    }

    @Override
    public Optional<NewCreditCardRequest> findByRequestId(int i) {
        return newCreditCardRequestDao.findById(i);
    }

    @Override
    public void createCardRequest(String firstName, String lastName, String oib, String status) {
        NewCreditCardRequest cardRequest = new NewCreditCardRequest(firstName, lastName, oib, status);

        newCreditCardRequestDao.save(cardRequest);
    }
}
