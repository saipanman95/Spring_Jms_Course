package com.mdrsolutions.SpringJmsExample.pojos;

public class Book {

    public Book(String bookId, String title) {
        this.bookId = bookId;
        this.title = title;
    }

    private final String bookId;
    private final String title;

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }
}
