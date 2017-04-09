package com.rishabhshukla.popularmoviesapp.controller.Database.Tables;

import static com.rishabhshukla.popularmoviesapp.controller.Database.Consts.*;

/**
 * Created by rishabhshukla on 09/04/17.
 */

public class MovieTable {
    public static final String TABLE_NAME = "movies";
    private static final String TAG = "MOVIE_TABLE";

//    public static final String CMD_UPDATE_TABLE_1_2_ADD_COL = "ALTER TABLE "+ TABLE_NAME + " ADD "+Columns.DONE+
//            TYPE_BOOLEAN+SEMICOL;

    public static final String CMD_TABLE_CREATE =   "CREATE TABLE "+TABLE_NAME+
            LBR+Columns.ID+TYPE_INT+TYPE_PK+COMMA+Columns.posterPath+TYPE_TEXT+COMMA+
        Columns.overview+TYPE_TEXT+COMMA+Columns.releaseDate+TYPE_TEXT +
        COMMA+Columns.originalTitle+TYPE_TEXT+COMMA+Columns.title+TYPE_TEXT+COMMA +
        Columns.backdropPath+TYPE_TEXT+COMMA+Columns.originalLanguage+TYPE_TEXT+
        COMMA+Columns.adult+TYPE_BOOLEAN+COMMA+Columns.voteCount+TYPE_INT +
        RBR+SEMICOL;

    public interface  Columns{
        String ID = "movie_id";
        String posterPath = "movie_posterPath";
        String overview = "movie_overview";

        String releaseDate = "movie_releaseDate";
        String originalTitle = "movie_originalTitle";
         String title = "movie_title";
         String backdropPath = "movie_backdropPath";
         String originalLanguage = "movie_originalLanguage";
        String adult = "movie_adult";
        String popularity = "movie_popularity";
         String voteAvg = "movie_voteAvg";
         String voteCount = "movie_voteCount";
         String video = "movie_video";
    }
}
