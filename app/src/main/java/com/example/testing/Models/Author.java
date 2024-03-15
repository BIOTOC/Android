package com.example.testing.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class Author {
    @SerializedName("id")
    @Expose
    private float Id;
    @SerializedName("name")
    @Expose
    private String Name;
    @SerializedName("createdDate")
    @Expose
    private LocalDateTime CreatedDate;
    @SerializedName("updatedDate")
    @Expose
    private LocalDateTime UpdatedDate;
    @SerializedName("isDeleted")
    @Expose
    private boolean IsDeleted;

    public Author() {
    }

    public Author(float id, String name, LocalDateTime createdDate, LocalDateTime updatedDate, boolean isDeleted) {
        Id = id;
        Name = name;
        CreatedDate = createdDate;
        UpdatedDate = updatedDate;
        IsDeleted = isDeleted;
    }

    public float getId() {
        return Id;
    }

    public void setId(float id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public LocalDateTime getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        CreatedDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return UpdatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        UpdatedDate = updatedDate;
    }

    public boolean isDeleted() {
        return IsDeleted;
    }

    public void setDeleted(boolean deleted) {
        IsDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Author{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", CreatedDate=" + CreatedDate +
                ", UpdatedDate=" + UpdatedDate +
                ", IsDeleted=" + IsDeleted +
                '}';
    }
}
