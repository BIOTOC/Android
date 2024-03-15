package com.example.testing.Model;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {

    private int bookId;
    private String resourceId;
    private String title;
    private int numberOfChapters;

    private List<Chapter> chapterList;

    public Book() {
    }

    public Book(int bookId, String resourceId, String title, int numberOfChapters, List<Chapter> chapterList) {
        this.bookId = bookId;
        this.resourceId = resourceId;
        this.title = title;
        this.numberOfChapters = numberOfChapters;
        this.chapterList = chapterList;
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

    public List<Chapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<Chapter> chapterList) {
        this.chapterList = chapterList;
    }
}
