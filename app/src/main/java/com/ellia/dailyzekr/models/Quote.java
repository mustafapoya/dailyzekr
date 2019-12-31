package com.ellia.dailyzekr.models;

import androidx.annotation.NonNull;

public class Quote {
    public String quote, author;

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public String getQuote() {
        return quote;
    }

    @NonNull
    @Override
    public String toString() {
        return getAuthor() + " , " + getQuote();
    }
}
