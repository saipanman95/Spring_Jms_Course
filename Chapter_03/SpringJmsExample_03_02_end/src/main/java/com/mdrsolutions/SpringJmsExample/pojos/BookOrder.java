package com.mdrsolutions.SpringJmsExample.pojos;

public class BookOrder {

    public BookOrder(String bookOrderId, Book book, Customer customer) {
        this.bookOrderId = bookOrderId;
        this.book = book;
        this.customer = customer;
    }

    private final String bookOrderId;
    private final Book book;
    private final Customer customer;

    public String getBookOrderId() {
        return bookOrderId;
    }

    public Book getBook() {
        return book;
    }

    public Customer getCustomer() {
        return customer;
    }
}
