package com.ben.card.crud.controller;

import com.ben.card.crud.model.NewCreditCardRequest;
import com.ben.card.crud.repository.NewCreditCardRequestDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class CreditCardControllerTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Value("${sql.script.create.newcreditcard.request}")
    private String sqlAddCards;

    @Value("${sql.script.delete.newcreditcard.request}")
    private String sqlDeleteCards;

    @Autowired
    private NewCreditCardRequestController controller;

    @Autowired
    private NewCreditCardRequestDao cardRequestDao;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    public static final MediaType APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON;


    @BeforeEach
    public void initializeDatabase() {
        jdbc.execute(sqlAddCards);
    }

    // get by oib
    @Test
    public void getCreditCardRequestByOibTest() throws Exception {
        // use get request together with oib to fetch card requests for that client
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/client-cards/{oib}", "11112223334"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)));

        // check if response is ok
    }



    // post - new request

    // delete
    @Test
    public void deleteCardRequestByOibTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/client-cards/{oib}", "11112223334"))
                .andExpect(status().isOk());

        List<NewCreditCardRequest> benjamin = cardRequestDao.findByOib("11112223334");
        assertEquals(0, benjamin.size());

        assertFalse(cardRequestDao.findById(1).isPresent());
    }

    @AfterEach
    public void restoreDatabase() {
        jdbc.execute(sqlDeleteCards);
    }

}
