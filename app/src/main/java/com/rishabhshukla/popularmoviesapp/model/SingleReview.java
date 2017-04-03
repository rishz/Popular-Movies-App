package com.rishabhshukla.popularmoviesapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rishabhshukla on 03/04/17.
 */

public class SingleReview {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("content")
    @Expose
    private String content;

    public void setId(String id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {

        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    @SerializedName("url")
    @Expose

    private String url;
}
