package com.example.testing.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chapter {
    @SerializedName("id")
    @Expose
    private Integer chapterId;

    @SerializedName("title")
    @Expose
    private String  title;
    @SerializedName("detail")
    @Expose
    private String chapterDetail;

    @SerializedName("storyId")
    @Expose
    private Integer storyId;

    @SerializedName("nextChapter")
    @Expose
    private Integer nextChapter;

    @SerializedName("previousChapter")
    @Expose
    private Integer previousChapter;

    public Chapter() {
    }

    public Chapter(Integer chapterId, String title, String chapterDetail, Integer storyId, Integer nextChapter, Integer previousChapter) {
        this.chapterId = chapterId;
        this.title = title;
        this.chapterDetail = chapterDetail;
        this.storyId = storyId;
        this.nextChapter = nextChapter;
        this.previousChapter = previousChapter;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChapterDetail() {
        return chapterDetail;
    }

    public void setChapterDetail(String chapterDetail) {
        this.chapterDetail = chapterDetail;
    }

    public Integer getStoryId() {
        return storyId;
    }

    public void setStoryId(Integer storyId) {
        this.storyId = storyId;
    }

    public Integer getNextChapter() {
        return nextChapter;
    }

    public void setNextChapter(Integer nextChapter) {
        this.nextChapter = nextChapter;
    }

    public Integer getPreviousChapter() {
        return previousChapter;
    }

    public void setPreviousChapter(Integer previousChapter) {
        this.previousChapter = previousChapter;
    }
}

