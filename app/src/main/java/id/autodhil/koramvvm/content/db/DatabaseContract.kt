package com.example.koramvvm.db

import android.database.Cursor
import android.provider.BaseColumns

open class KBaseColums{
    val _ID = "_id"
}

class DatabaseContract : BaseColumns {

    companion object{
        val TABLE_FAVORIT = "favoritee"
    }


     object FavoriteColumns : KBaseColums() {
        var EPISODE = "title"
        var GENRE = "deskripsi"
        var IMAGE = "popularity"
        var RATE = "background"
        var TITLE = "vote_average"
        var BANNER =  "banner"
        var SINOPSIS = "sinopsis"
    }
}