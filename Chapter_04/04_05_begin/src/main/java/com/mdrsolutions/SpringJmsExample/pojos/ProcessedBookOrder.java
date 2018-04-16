package com.mdrsolutions.SpringJmsExample.pojos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ProcessedBookOrder {

    @JsonCreator
    public ProcessedBookOrder(
            @JsonProperty("bookOrder") BookOrder bookOrder,
            @JsonProperty("processingDateTime") Date processingDateTime,
            @JsonProperty("expectedShippingDateTime") Date expectedShippingDateTime) {
        this.bookOrder = bookOrder;
        this.processingDateTime = processingDateTime;
        this.expectedShippingDateTime = expectedShippingDateTime;
    }

    private final BookOrder bookOrder;
    private final Date processingDateTime;
    private final Date expectedShippingDateTime;

    public BookOrder getBookOrder() {
        return bookOrder;
    }

    public Date getProcessingDateTime() {
        return processingDateTime;
    }

    public Date getExpectedShippingDateTime() {
        return expectedShippingDateTime;
    }

    @Override
    public String toString() {
        return "ProcessedBookOrder{" +
                "bookOrder=" + bookOrder +
                ", processingDateTime=" + processingDateTime +
                ", expectedShippingDateTime=" + expectedShippingDateTime +
                '}';
    }
}
