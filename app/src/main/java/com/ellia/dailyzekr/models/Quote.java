package com.ellia.dailyzekr.models;

import androidx.annotation.NonNull;

public class Quote {
    public String quote, quoteFarsi, quoteTurkey, quoteHindi, author;

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public void setQuoteFarsi(String quoteFarsi) {
        this.quoteFarsi = quoteFarsi;
    }

    public void setQuoteTurkey(String quoteTurkey) {
        this.quoteTurkey = quoteTurkey;
    }

    public void setQuoteHindi(String quoteHindi) {
        this.quoteHindi = quoteHindi;
    }

    public String getAuthor() {
        return author;
    }

    public String getQuote() {
        return quote;
    }

    public String getQuoteFarsi() {
        return quoteFarsi;
    }

    public String getQuoteTurkey() {
        return quoteTurkey;
    }

    public String getQuoteHindi() {
        return quoteHindi;
    }

    @NonNull
    @Override
    public String toString() {
        return getAuthor() + " , " + getQuote();
    }
}
