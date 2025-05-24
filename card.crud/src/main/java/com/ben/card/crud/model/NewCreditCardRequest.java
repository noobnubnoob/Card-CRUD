package com.ben.card.crud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="CREDIT_CARD_REQUEST")
public class NewCreditCardRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Integer requestId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    @Pattern(regexp = "^[a-zA-Z]{1,35}")
    private String lastName;
    @Column(name = "oib")
    @NotNull
    @Pattern(regexp = "^[0-9]{3,11}")
    private String oib;
    @Column(name = "status")
    private String status;

    public NewCreditCardRequest(String firstName, String lastName, String oib, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.oib = oib;
        this.status = status;
    }

    public NewCreditCardRequest() {
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
