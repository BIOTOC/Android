package com.example.testing.Services.Story.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FilterStoryRequest {
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("pageSize")
    @Expose
    private Integer pageSize;
    @SerializedName("nameTextSearch")
    @Expose
    private String nameTextSearch;

    public FilterStoryRequest() {
    }

    public FilterStoryRequest(Integer page, Integer pageSize, String nameTextSearch) {
        this.page = page;
        this.pageSize = pageSize;
        this.nameTextSearch = nameTextSearch;
    }


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getNameTextSearch() {
        return nameTextSearch;
    }

    public void setKeyRearch(String nameTextSearch) {
        this.nameTextSearch = nameTextSearch;
    }
}
