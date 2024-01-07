package com.example.examen_app_moviles.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.examen_app_moviles.db.AdminSQL;
import com.example.examen_app_moviles.models.Genero;
import com.example.examen_app_moviles.models.Pelicula;

public class GenerosController {
    private AdminSQL sql;

    public GenerosController(@Nullable Context context, @Nullable String name, int version) {
        sql = new AdminSQL(context, name, null, version);
    }

    public Genero crear(String nombre, int popularidad) {
        SQLiteDatabase bdd = sql.getWritableDatabase();
        ContentValues item = new ContentValues();

        item.put("nombre", nombre);
        item.put("popularidad", popularidad);

        int id = (int) bdd.insert("generos", null, item);

        return new Genero(id, nombre, popularidad);
    }

    public Genero leerPorId(int id) {
        SQLiteDatabase bdd = sql.getReadableDatabase();

        String query = "SELECT id,nombre,popularidad FROM generos WHERE id = " + id;

        Cursor data = bdd.rawQuery(query, null);

        if (data.getCount() < 1) return null;

        data.moveToFirst();

        return new Genero(data.getInt(0), data.getString(1), data.getInt(2));
    }

    public Genero[] leerTodo() {
        SQLiteDatabase bdd = sql.getReadableDatabase();
        Cursor data = bdd.rawQuery("SELECT id,nombre,popularidad FROM generos ORDER BY nombre", null);

        if (data.getCount() < 1) return new Genero[0];

        Genero[] generos = new Genero[data.getCount()];
        int i = 0;

        while (data.moveToNext()) {
            generos[i++] = new Genero(data.getInt(0), data.getString(1), data.getInt(2));
        }
        return generos;
    }

    public Genero[] leerPorNombre(String nombreStr) {
        SQLiteDatabase bdd = sql.getReadableDatabase();
        Cursor data = bdd.rawQuery("SELECT id,nombre,popularidad \n" + "FROM generos\n" + "WHERE nombre LIKE '%" + nombreStr + "%'\n " + "ORDER BY nombre", null);

        if (data.getCount() < 1) return null;

        Genero[] generos = new Genero[data.getCount()];
        int i = 0;

        while (data.moveToNext()) {
            generos[i++] = new Genero(data.getInt(0), data.getString(1), data.getInt(2));
        }
        return generos;
    }


    public void actualizar(int id, String nombre, int popularidad) {
        SQLiteDatabase bdd = sql.getWritableDatabase();
        ContentValues item = new ContentValues();

        item.put("nombre", nombre);
        item.put("popularidad", popularidad);

        bdd.update("generos", item, "id=" + id, null);
    }

    public void eliminar(int id) {
        SQLiteDatabase bdd = sql.getWritableDatabase();
        bdd.delete("generos", "id=" + id, null);
    }
}
