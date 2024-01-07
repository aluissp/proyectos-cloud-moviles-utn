package com.example.examen_app_moviles.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.examen_app_moviles.db.AdminSQL;
import com.example.examen_app_moviles.models.Pelicula;

public class PeliculasController {
    private AdminSQL sql;

    public PeliculasController(@Nullable Context context, @Nullable String name, int version) {
        sql = new AdminSQL(context, name, null, version);
    }

    public Pelicula crear(String titulo, String director, String anio, int generoId) {
        SQLiteDatabase bdd = sql.getWritableDatabase();
        ContentValues item = new ContentValues();

        item.put("titulo", titulo);
        item.put("director", director);
        item.put("anio", anio);
        item.put("genero_id", generoId);

        int id = (int) bdd.insert("peliculas", null, item);

        return new Pelicula(
                id,
                titulo,
                director,
                anio,
                generoId
        );
    }

    public Pelicula leerPorId(int id) {
        SQLiteDatabase bdd = sql.getReadableDatabase();

        String query = "SELECT id,titulo,director,anio,genero_id \n" +
                       "FROM peliculas WHERE id = " + id;

        Cursor data = bdd.rawQuery(query, null);

        if (data.getCount() < 1) return null;

        data.moveToFirst();

        return new Pelicula(
                data.getInt(0),
                data.getString(1),
                data.getString(2),
                data.getString(3),
                data.getInt(4)
        );
    }

    public Pelicula[] leerTodo() {
        SQLiteDatabase bdd = sql.getReadableDatabase();
        String query = "SELECT id,titulo,director,anio,genero_id FROM peliculas ORDER BY titulo";
        Cursor data = bdd.rawQuery(query, null);

        if (data.getCount() < 1) return new Pelicula[0];

        Pelicula[] peliculas = new Pelicula[data.getCount()];
        int i = 0;

        while (data.moveToNext()) {
            peliculas[i++] = new Pelicula(
                    data.getInt(0),
                    data.getString(1),
                    data.getString(2),
                    data.getString(3),
                    data.getInt(4)
            );
        }
        return peliculas;
    }

    public Pelicula[] leerPorNombre(String nombreStr) {
        SQLiteDatabase bdd = sql.getReadableDatabase();
        Cursor data = bdd.rawQuery("SELECT id,titulo,director,anio,genero_id \n" +
                                   "FROM productos\n" +
                                   "WHERE titulo LIKE '%" + nombreStr + "%'\n " +
                                   "ORDER BY titulo", null);

        if (data.getCount() < 1) return null;

        Pelicula[] peliculas = new Pelicula[data.getCount()];
        int i = 0;

        while (data.moveToNext()) {
            peliculas[i++] = new Pelicula(
                    data.getInt(0),
                    data.getString(1),
                    data.getString(2),
                    data.getString(3),
                    data.getInt(4)
            );
        }
        return peliculas;
    }


    public void actualizar(int id, String titulo, String director, String anio, int generoId) {
        SQLiteDatabase bdd = sql.getWritableDatabase();
        ContentValues item = new ContentValues();

        item.put("titulo", titulo);
        item.put("director", director);
        item.put("anio", anio);
        item.put("genero_id", generoId);

        bdd.update("peliculas", item, "id=" + id, null);
    }

    public void eliminar(int id) {
        SQLiteDatabase bdd = sql.getWritableDatabase();
        bdd.delete("peliculas", "id=" + id, null);
    }
}
