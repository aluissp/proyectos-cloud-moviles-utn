package com.example.sqlite_app.db;

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
//        db.execSQL("CREATE TABLE IF NOT EXISTS  paises (id int PRIMARY KEY, nombre TEXT(50), capital TEXT(50), moneda TEXT(20), poblacion int, idioma TEXT(20))");

        db.execSQL("""
                CREATE TABLE IF NOT EXISTS  productos (id int PRIMARY KEY,
                nombre TEXT(50),
                descripcion TEXT(150),
                stock NUMBER(10,3),
                precio_unitario NUMBER(10,3),
                tasa_iva NUMBER(10,3))""");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
