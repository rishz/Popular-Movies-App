package com.rishabhshukla.popularmoviesapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rishabhshukla on 03/04/17.
 */

public class SingleVideo {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("iso_639_1")
    @Expose
    private String iso639;

    @SerializedName("iso_3166_1")
    @Expose
    private String iso3166;

    @SerializedName("key")
    @Expose
    private String key;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("site")
    @Expose
    private String site;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("size")
    @Expose
    private Integer size;

    public void setId(String id) {
        this.id = id;
    }

    public void setIso639(String iso639) {
        this.iso639 = iso639;
    }

    public void setIso3166(String iso3166) {
        this.iso3166 = iso3166;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getId() {

        return id;
    }

    public String getIso639() {
        return iso639;
    }

    public String getIso3166() {
        return iso3166;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getSite() {
        return site;
    }

    public String getType() {
        return type;
    }

    public Integer getSize() {
        return size;
    }
}
