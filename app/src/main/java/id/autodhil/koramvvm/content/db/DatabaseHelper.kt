package com.example.koramvvm.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    companion object{
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "dbFavoritee"

        private val SQL_CREAT_TABLE_FAVORITE : String = String.format("CREATE TABLE %s"
                +"(%s INTEGER PRIMARY KEY AUTOINCREMENT,"+
                " %s TEXT NOT NULL,"+
                " %s TEXT NOT NULL,"+
                " %s TEXT NOT NULL,"+
                " %s TEXT NOT NULL,"+
                " %s TEXT NOT NULL,"+
                " %s TEXT NOT NULL,"+
                " %s TEXT NOT NULL)",
            DatabaseContract.TABLE_FAVORIT,
            DatabaseContract.FavoriteColumns._ID,
            DatabaseContract.FavoriteColumns.EPISODE,
            DatabaseContract.FavoriteColumns.GENRE,
            DatabaseContract.FavoriteColumns.IMAGE,
            DatabaseContract.FavoriteColumns.RATE,
            DatabaseContract.FavoriteColumns.TITLE,
            DatabaseContract.FavoriteColumns.BANNER,
            DatabaseContract.FavoriteColumns.SINOPSIS)
    }






    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREAT_TABLE_FAVORITE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_FAVORIT)
        onCreate(db)
    }

}