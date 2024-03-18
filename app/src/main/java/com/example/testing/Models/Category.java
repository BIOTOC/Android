package com.example.testing.Models;

import java.util.List;

public class Category {
    private String nameCategory;
    private List<Story> stories;

    public Category() {
    }

    public Category(String nameCategory, List<Story> stories) {
        this.nameCategory = nameCategory;
        this.stories = stories;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public List<Story> getBooks() {
        return stories;
    }

    public void setBooks(List<Story> stories) {
        this.stories = stories;
    }
}
