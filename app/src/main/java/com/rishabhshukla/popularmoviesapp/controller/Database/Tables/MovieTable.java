package com.rishabhshukla.popularmoviesapp.controller.Database.Tables;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.rishabhshukla.popularmoviesapp.model.SingleMovie;

import static com.rishabhshukla.popularmoviesapp.controller.Database.Consts.COMMA;
import static com.rishabhshukla.popularmoviesapp.controller.Database.Consts.LBR;
import static com.rishabhshukla.popularmoviesapp.controller.Database.Consts.RBR;
import static com.rishabhshukla.popularmoviesapp.controller.Database.Consts.SEMICOL;
import static com.rishabhshukla.popularmoviesapp.controller.Database.Consts.TYPE_BOOLEAN;
import static com.rishabhshukla.popularmoviesapp.controller.Database.Consts.TYPE_INT;
import static com.rishabhshukla.popularmoviesapp.controller.Database.Consts.TYPE_PK;
import static com.rishabhshukla.popularmoviesapp.controller.Database.Consts.TYPE_TEXT;

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
//        String popularity = "movie_popularity";
//         String voteAvg = "movie_voteAvg";
         String voteCount = "movie_voteCount";
//         String video = "movie_video";
    }

    public static boolean addMovie(SQLiteDatabase db, SingleMovie movie){
        if(db.isReadOnly()){
            return false;
        }
        ContentValues obj = new ContentValues();
        obj.put(Columns.ID,movie.getId());
        obj.put(Columns.posterPath,movie.getPosterPath());
        obj.put(Columns.overview,movie.getOverview());
        obj.put(Columns.originalTitle,movie.getOriginalTitle());
        obj.put(Columns.originalLanguage,movie.getOriginalLanguage());
        obj.put(Columns.backdropPath,movie.getBackdropPath());
        obj.put(Columns.adult,movie.getAdult());
        obj.put(Columns.voteCount,movie.getVoteCount());
        obj.put(Columns.title,movie.getTitle());
        obj.put(Columns.releaseDate,movie.getReleaseDate());

        db.insert(TABLE_NAME,null,obj);
        db.close();

        return true;
    }
//    public static ArrayList<SingleMovie> getAllTasks (SQLiteDatabase db){
//        String[] PROJECTION = {
//                Columns.ID, Columns.posterPath, Columns.backdropPath, Columns.voteCount, Columns.adult
//                ,Columns.originalLanguage, Columns.originalTitle, Columns.overview, Columns.releaseDate,
//                Columns.title
//        };
//
//        Cursor cursor = db.query(TABLE_NAME,PROJECTION,null,null,null,null,null);
//
//        ArrayList<SingleMovie> movies = new ArrayList<>();
//        cursor.moveToFirst();
//
//        int idIndex=  cursor.getColumnIndex(Columns.ID);
//        int taskIndex = cursor.getColumnIndex(Columns.TASK);
//        while(cursor.moveToNext()){
//            movies.add(new SingleMovie(cursor.getInt(idIndex),cursor.getString(taskIndex)));
//        }
//        cursor.close();
//        return movies;
//    }
}
