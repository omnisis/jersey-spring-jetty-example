package com.nextinstruction.model;

public class Quote {
    private String message;
    private String author;

    public Quote(String message, String author) {
        this.message = message;
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(message).append('\n');
        sb.append("-- ").append(author);
        return sb.toString();
    }
}
