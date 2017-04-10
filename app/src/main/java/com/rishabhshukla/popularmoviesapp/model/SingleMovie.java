package com.rishabhshukla.popularmoviesapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import ckm.simple.sql_provider.annotation.SimpleSQLColumn;
import ckm.simple.sql_provider.annotation.SimpleSQLTable;

/**
 * Created by rishabhshukla on 30/03/17.
 */

@SimpleSQLTable(table = "movieTable", provider = "MovieProvider")

public class SingleMovie implements Serializable{
    @SerializedName("poster_path")
    @Expose
    @SimpleSQLColumn("col_posterPath")
    private String posterPath;
    @SerializedName("overview")
    @Expose
    @SimpleSQLColumn("col_overview")
    private String overview;
    @SerializedName("release_date")
    @Expose
    @SimpleSQLColumn("col_releaseDate")
    private String releaseDate;
    @SerializedName("id")
    @Expose
    @SimpleSQLColumn("col_id")
    private Integer id;
    @SerializedName("original_title")
    @Expose
    @SimpleSQLColumn("col_originalTitle")
    private String originalTitle;
    @SerializedName("title")
    @Expose
    @SimpleSQLColumn("col_title")
    private String title;
    @SerializedName("backdrop_path")
    @Expose
    @SimpleSQLColumn("col_backdropPath")
    private String backdropPath;
    @SerializedName("original_language")
    @Expose
    @SimpleSQLColumn("col_originalLanguage")
    private String originalLanguage;
    @SerializedName("adult")
    @Expose
    @SimpleSQLColumn("col_adult")
    private Boolean adult;
    @SerializedName("popularity")
    @Expose
    @SimpleSQLColumn("col_popularity")
    private Double popularity;
    @SerializedName("vote_average")
    @Expose
    private Double voteAvg;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("video")
    @Expose
    private Boolean video;

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public void setVoteAvg(Double voteAvg) {
        this.voteAvg = voteAvg;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public Double getPopularity() {

        return popularity;
    }

    public Double getVoteAvg() {
        return voteAvg;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public Boolean getVideo() {
        return video;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds;

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }
}