package com.ben.card.crud.service;

import com.ben.card.crud.dto.NewCardRequest;
import com.ben.card.crud.model.NewCreditCardRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application-test.properties")
@SpringBootTest
public class CreditCardServiceImplTest {

    @Autowired
    private  NewCreditCardRequestService newCreditCardRequestService;

    @Autowired
    private JdbcTemplate jdbc;

    @Value("${sql.script.create.newcreditcard.request}")
    private String sqlAddCards;

    @Value("${sql.script.delete.newcreditcard.request}")
    private String sqlDeleteCards;

    @BeforeEach
    public void initializeDatabase() {
        jdbc.execute(sqlAddCards);
    }

    // tests to do

    @Test
    public void createCardRequestTes() {
        // insert in database
        newCreditCardRequestService.createCardRequest("Petar", "Peric", "555", "pending");
        // check if it actually is in databse afterwards
        Optional<NewCreditCardRequest> newCard = newCreditCardRequestService.findByRequestId(7);

        // check how many records are in db
        assertEquals(7, newCreditCardRequestService.findAll().size());
        assertTrue(newCard.isPresent());
        assertEquals("Petar", newCard.get().getFirstName());
        assertEquals("Peric", newCard.get().getLastName());
        assertEquals("555", newCard.get().getOib());
        assertEquals("pending", newCard.get().getStatus());
    }

    // searching database - return nothing if not found
    @Test
    public void findAllRequestsTest() {
        List<NewCreditCardRequest> allRequests = newCreditCardRequestService.findAll();

        assertEquals(6, allRequests.size());
    }

    @Test
    public void findRequestByOibTest() {
        List<NewCreditCardRequest> benjamin = newCreditCardRequestService.findByOib("11112223334");
        assertEquals(2, benjamin.size());

        NewCreditCardRequest benjaminCard1 = benjamin.get(0);
        assertEquals("Benjamin", benjaminCard1.getFirstName());
        assertEquals("Vinkovic", benjaminCard1.getLastName());
        assertEquals("pending", benjaminCard1.getStatus());

        NewCreditCardRequest benjaminCard2 = benjamin.get(1);
        assertEquals("Benjamin", benjaminCard2.getFirstName());
        assertEquals("Vinkovic", benjaminCard2.getLastName());
        assertEquals("approved", benjaminCard2.getStatus());

        List<NewCreditCardRequest> marko = newCreditCardRequestService.findByOib("111");
        assertEquals(1, marko.size());

        NewCreditCardRequest markoCard = marko.get(0);
        assertEquals("Marko", markoCard.getFirstName());
        assertEquals("Markic", markoCard.getLastName());
        assertEquals("rejected", markoCard.getStatus());
    }

    @Test
    public void findByRequestIdTest() {
        Optional<NewCreditCardRequest> benjamin = newCreditCardRequestService.findByRequestId(1);

        assertTrue(benjamin.isPresent());

        assertEquals("Benjamin", benjamin.get().getFirstName());
        assertEquals("Vinkovic", benjamin.get().getLastName());
        assertEquals("pending", benjamin.get().getStatus());
        assertEquals("11112223334", benjamin.get().getOib());
    }

    // deleting a client + delete by oib
    @Test
    public void deleteCardRequestTest() {
        newCreditCardRequestService.deleteByRequestId(2);

        List<NewCreditCardRequest> allCardRequests = newCreditCardRequestService.findAll();
        assertNotEquals(6, allCardRequests.size());

        Optional<NewCreditCardRequest> marko = newCreditCardRequestService.findByRequestId(2);
        assertFalse(marko.isPresent());
    }

    @Test
    @Transactional
    public void deleteCardRequestByIdTest() {
        newCreditCardRequestService.deleteByOib("11112223334");

        List<NewCreditCardRequest> allCardRequests = newCreditCardRequestService.findAll();
        assertEquals(4, allCardRequests.size());

        Optional<NewCreditCardRequest> benjamin = newCreditCardRequestService.findByRequestId(1);
        assertFalse(benjamin.isPresent());

        List<NewCreditCardRequest> benjaminCards = newCreditCardRequestService.findByOib("11112223334");
        assertEquals(0, benjaminCards.size());
    }

    @AfterEach
    public void restoreDatabase() {
        jdbc.execute(sqlDeleteCards);
    }


}
