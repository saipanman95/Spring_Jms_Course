package com.mdrsolutions.SpringJmsExample.pojos;

public class Customer {

    public Customer(String customerId, String fullName) {
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
}
