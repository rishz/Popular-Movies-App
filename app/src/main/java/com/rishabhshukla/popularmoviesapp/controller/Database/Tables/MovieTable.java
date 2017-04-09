package com.rishabhshukla.popularmoviesapp.controller.Database.Tables;

import static com.rishabhshukla.popularmoviesapp.controller.Database.Consts.*;

/**
 * Created by rishabhshukla on 09/04/17.
 */

public class MovieTable {
    public static final String TABLE_NAME = "todos";
    private static final String TAG = "TODOS";
    public static final String CMD_UPDATE_TABLE_1_2_ADD_COL = "ALTER TABLE "+ TABLE_NAME + " ADD "+Columns.DONE+
            TYPE_BOOLEAN+SEMICOL;
    public static final String CMD_TABLE_CREATE =   "CREATE TABLE "+TABLE_NAME+
            LBR+Columns.ID+TYPE_INT+TYPE_PK+TYPE_AI+COMMA+Columns.TASK+TYPE_TEXT+RBR+SEMICOL;

    public interface  Columns{
        String ID = "todo_id";
        String TASK  = "todo_task";
        String DONE = "todo_done";
    }
}
