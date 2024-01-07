package com.example.examen_app_moviles.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQL extends SQLiteOpenHelper {
    public AdminSQL(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("""
                CREATE TABLE IF NOT EXISTS  generos (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre VARCHAR(255) NOT NULL,
                    popularidad INT)
                """);


        db.execSQL("""
                CREATE TABLE IF NOT EXISTS  peliculas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                titulo VARCHAR(50),
                director VARCHAR(50),
                anio VARCHAR(4),
                genero_id INTEGER,
                FOREIGN KEY (genero_id) REFERENCES generos(id))
                """);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
