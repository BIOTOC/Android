package com.example.testing.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Story {

    @SerializedName("id")
    @Expose
    private Integer Id;

    @SerializedName("name")
    @Expose
    private String Name;

    @SerializedName("image")
    @Expose
    private String Image;

    @SerializedName("description")
    @Expose
    private String Description;

    @SerializedName("status")
    @Expose
    private Integer Status;

    @SerializedName("numberChapter")
    private Integer NumberChapter;

    @SerializedName("chapters")
    private List<Chapter> Chapters;

    @SerializedName("author")
    private Author Author;

    public Story() {
    }

    public Story(Integer id, String name, String image, String description, Integer status, Integer numberChapter, List<Chapter> chapters, com.example.testing.Models.Author author) {
        Id = id;
        Name = name;
        Image = image;
        Description = description;
        Status = status;
        NumberChapter = numberChapter;
        Chapters = chapters;
        Author = author;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public Integer getNumberChapter() {
        return NumberChapter;
    }

    public void setNumberChapter(Integer numberChapter) {
        NumberChapter = numberChapter;
    }

    public List<Chapter> getChapters() {
        return Chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        Chapters = chapters;
    }

    public com.example.testing.Models.Author getAuthor() {
        return Author;
    }

    public void setAuthor(com.example.testing.Models.Author author) {
        Author = author;
    }

    @Override
    public String toString() {
        return "Story{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Image='" + Image + '\'' +
                ", Description='" + Description + '\'' +
                ", Status=" + Status +
                ", NumberChapter=" + NumberChapter +
                ", Chapters=" + Chapters +
                ", Author=" + Author +
                '}';
    }
}
