package com.example.testing.Model;

import java.io.Serializable;

public class Book implements Serializable {

    private int bookId;
    private String resourceId;
    private String title;
    private int numberOfChapters;

    public Book() {
    }

    public Book(int bookId, String resourceId, String title, int numberOfChapters) {
        this.bookId = bookId;
        this.resourceId = resourceId;
        this.title = title;
        this.numberOfChapters = numberOfChapters;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageBase64() {
        return resourceId;
    }


    public int getNumberOfChapters() {
        return numberOfChapters;
    }

    public void setNumberOfChapters(int numberOfChapters) {
        this.numberOfChapters = numberOfChapters;
    }
}
