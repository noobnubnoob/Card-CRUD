package com.ben.card.crud.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

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



    @AfterEach
    public void restoreDatabase() {
        jdbc.execute(sqlDeleteCards);
    }


}
