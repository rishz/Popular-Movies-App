package com.rishabhshukla.popularmoviesapp.controller;

import android.view.View;

/**
 * Created by rishabhshukla on 02/04/17.
 */

public interface ItemClickListener {
    void onClick(View v, int position, boolean isLongClick);
}
