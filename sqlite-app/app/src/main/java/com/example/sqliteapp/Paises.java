package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class Paises {

    private AdminSQL sql;

    public Paises(@Nullable Context context, @Nullable String name, int version) {
        sql = new AdminSQL(context, name, null, version);
    }

    //C - create
    public Pais Create(int id, String nombre, String capital, String moneda, int poblacion, String idioma) {
        SQLiteDatabase bdd = sql.getWritableDatabase();
        ContentValues item = new ContentValues();
        item.put("id", id);
        item.put("nombre", nombre);
        item.put("capital", capital);
        item.put("moneda", moneda);
        item.put("poblacion", poblacion);
        item.put("idioma", idioma);
        bdd.insert("paises", null, item);
        Pais p = new Pais();
        p.Id = id;
        p.Nombre = nombre;
        p.Capital = capital;
        p.Moneda = moneda;
        p.Poblacion = poblacion;
        p.Idioma = idioma;
        return p;
    }

    // R - read One
    public Pais Read_ById(int id) {
        SQLiteDatabase bdd = sql.getReadableDatabase();
        Cursor data = bdd.rawQuery("SELECT id,nombre,capital,moneda,poblacion,idioma FROM paises where id = " + id, null);
        if (data.getCount() > 0) {
            data.moveToFirst();
            Pais p = new Pais();
            p.Id = data.getInt(0);
            p.Nombre = data.getString(1);
            p.Capital = data.getString(2);
            p.Moneda = data.getString(3);
            p.Poblacion = data.getInt(4);
            p.Idioma = data.getString(5);
            return p;
        }
        return null;
    }

    // R - read All
    public Pais[] Read_All() {
        SQLiteDatabase bdd = sql.getReadableDatabase();
        Cursor data = bdd.rawQuery("SELECT id,nombre,capital,moneda,poblacion,idioma FROM paises ORDER BY nombre", null);
        if (data.getCount() > 0) {
            Pais[] resultado = new Pais[data.getCount()];
            int i = 0;
            while (data.moveToNext()) {
                Pais p = new Pais();
                p.Id = data.getInt(0);
                p.Nombre = data.getString(1);
                p.Capital = data.getString(2);
                p.Moneda = data.getString(3);
                p.Poblacion = data.getInt(4);
                p.Idioma = data.getString(5);
                resultado[i++] = p;
            }
            return resultado;
        }
        return null;
    }

    // R - read One by name
    public Pais[] Read_ByName(String search_for) {
        SQLiteDatabase bdd = sql.getReadableDatabase();
        Cursor data = bdd.rawQuery("SELECT id,nombre,capital,moneda,poblacion,idioma FROM paises WHERE nombre LIKE '%" + search_for + "%' ORDER BY nombre", null);
        if (data.getCount() > 0) {
            Pais[] resultado = new Pais[data.getCount()];
            int i = 0;
            while (data.moveToNext()) {
                Pais p = new Pais();
                p.Id = data.getInt(0);
                p.Nombre = data.getString(1);
                p.Capital = data.getString(2);
                p.Moneda = data.getString(3);
                p.Poblacion = data.getInt(4);
                p.Idioma = data.getString(5);
                resultado[i++] = p;
            }
            return resultado;
        }
        return null;
    }

    //U - update
    public void Update(int id, String nombre, String capital, String moneda, int poblacion, String idioma) {
        SQLiteDatabase bdd = sql.getWritableDatabase();
        ContentValues item = new ContentValues();
        item.put("id", id);
        item.put("nombre", nombre);
        item.put("capital", capital);
        item.put("moneda", moneda);
        item.put("poblacion", poblacion);
        item.put("idioma", idioma);
        bdd.update("paises", item, "id=" + id, null);
    }

    //U - delete
    public void Delete(int id) {
        SQLiteDatabase bdd = sql.getWritableDatabase();
        bdd.delete("paises", "id=" + id, null);
    }
}
