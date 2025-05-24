package com.ben.card.crud.service;

import com.ben.card.crud.model.NewCreditCardRequest;
import com.ben.card.crud.repository.NewCreditCardRequestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
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
    public Optional<NewCreditCardRequest> findByRequestId(int id) {
        return newCreditCardRequestDao.findById(id);
    }

    @Override
    public NewCreditCardRequest createCardRequest(String firstName, String lastName, String oib, String status) {
        System.out.println("Create new " + oib);
        NewCreditCardRequest cardRequest = new NewCreditCardRequest(firstName, lastName, oib, status);

        return newCreditCardRequestDao.save(cardRequest);
    }

    @Override
    public void deleteByRequestId(int id) {
        System.out.println("DELETE HIT");
        newCreditCardRequestDao.deleteById(id);
    }

    @Override
    public void deleteByOib(String oib) {
        newCreditCardRequestDao.deleteByOib(oib);
    }
}
