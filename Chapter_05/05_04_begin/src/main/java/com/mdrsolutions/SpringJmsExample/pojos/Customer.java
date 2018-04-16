package com.mdrsolutions.SpringJmsExample.pojos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer {

    @JsonCreator
    public Customer(@JsonProperty("customerId") String customerId, @JsonProperty("fullName") String fullName) {
        this.customerId = customerId;
        this.fullName = fullName;
    }

    private final String customerId;
    private final String fullName;

    public String getCustomerId() {
        return customerId;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}

