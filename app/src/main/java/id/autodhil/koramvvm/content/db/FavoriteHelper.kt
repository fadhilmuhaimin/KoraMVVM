package com.example.koramvvm.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.provider.BaseColumns._ID
import androidx.core.content.contentValuesOf
import com.example.koramvvm.db.DatabaseContract.Companion.TABLE_FAVORIT
import com.example.koramvvm.db.DatabaseContract.FavoriteColumns.BANNER
import com.example.koramvvm.db.DatabaseContract.FavoriteColumns.EPISODE
import com.example.koramvvm.db.DatabaseContract.FavoriteColumns.GENRE
import com.example.koramvvm.db.DatabaseContract.FavoriteColumns.RATE
import com.example.koramvvm.db.DatabaseContract.FavoriteColumns.SINOPSIS
import com.example.koramvvm.db.DatabaseContract.FavoriteColumns.TITLE
import id.autodhil.koramvvm.content.model.Komik

import java.sql.SQLException
import com.example.koramvvm.db.DatabaseContract.FavoriteColumns.IMAGE as IMAGE

class FavoriteHelper(context:Context) {
    companion object {
        val DATABASE_TABLE = TABLE_FAVORIT
        private lateinit var databaseHelper:DatabaseHelper
        private var INSTANCE:FavoriteHelper? = null
        private lateinit var  database: SQLiteDatabase
        fun getInstance(context: Context): FavoriteHelper? {
            if (INSTANCE == null)
            {
                synchronized (SQLiteOpenHelper::class.java) {
                    if (INSTANCE == null)
                    {
                        INSTANCE = FavoriteHelper(context)
                    }
                }
            }
            return INSTANCE
        }
    }
    init {
        databaseHelper = DatabaseHelper(context)
    }

    @Throws(SQLException::class)
    fun open() {
        database = databaseHelper.getWritableDatabase()
    }

    fun close(){
        databaseHelper.close()
        if (database.isOpen){
            database.close()
        }
    }

    fun getAllKomik() : ArrayList<Komik>{
        val arrayList =  ArrayList<Komik>()
        val cursor = database.query(DATABASE_TABLE, null,
        null,null,null,null,_ID+" ASC",null)
        cursor.moveToFirst()
        var komik : Komik
        if (cursor.count >0){
            do{
                komik = Komik()
                komik.id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID))
                komik.episode = cursor.getString(cursor.getColumnIndex(EPISODE))
                komik.genre = cursor.getString(cursor.getColumnIndex(GENRE))
                komik.image = cursor.getString(cursor.getColumnIndex(IMAGE))
                komik.rate = cursor.getString(cursor.getColumnIndex(RATE))
                komik.title = cursor.getString(cursor.getColumnIndex(TITLE))
                komik.banner = cursor.getString(cursor.getColumnIndex(BANNER))
                komik.sinopsis = cursor.getString(cursor.getColumnIndex(SINOPSIS))
                arrayList.add(komik)
                cursor.moveToNext()
            }while (!cursor.isAfterLast)
        }
        cursor.close()
        return arrayList
    }

    fun insertMovie(komik: Komik) : Long{
        val contentValues = ContentValues()
        contentValues.put(EPISODE,komik.episode)
        contentValues.put(GENRE,komik.genre)
        contentValues.put(IMAGE,komik.image)
        contentValues.put(RATE,komik.rate)
        contentValues.put(TITLE,komik.title)
        contentValues.put(BANNER,komik.banner)
        contentValues.put(SINOPSIS,komik.sinopsis)
        return database.insert(DATABASE_TABLE, null,contentValues)
    }
    fun delete(id : Int):Int{
        return database.delete(TABLE_FAVORIT, _ID+"= '"+id+"'",null)
    }
}