package com.example.testing.Models;

public class Chapter {
    private int chapterId;
    private String chapterDetail;


    public Chapter(int chapterId, String chapterDetail) {
        this.chapterId = chapterId;
        this.chapterDetail = chapterDetail;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterDetail() {
        return chapterDetail;
    }

    public void setChapterDetail(String chapterDetail) {
        this.chapterDetail = chapterDetail;
    }
}

