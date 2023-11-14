package com.example.sqlite_app.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.sqlite_app.db.AdminSQL;
import com.example.sqlite_app.models.Producto;

public class ProductosController {
    private AdminSQL sql;

    public ProductosController(@Nullable Context context, @Nullable String name, int version) {
        sql = new AdminSQL(context, name, null, version);
    }

    public Producto crearProducto(int id, String nombre, String descripcion, float stock, float precioUnitario, float tasaIva) {
        SQLiteDatabase bdd = sql.getWritableDatabase();
        ContentValues item = new ContentValues();

        item.put("id", id);
        item.put("nombre", nombre);
        item.put("descripcion", descripcion);
        item.put("stock", stock);
        item.put("precio_unitario", precioUnitario);
        item.put("tasa_iva", tasaIva);
        bdd.insert("productos", null, item);

        return new Producto(
                id,
                nombre,
                descripcion,
                stock,
                precioUnitario,
                tasaIva
        );
    }

    public Producto leerPorId(int id) {
        SQLiteDatabase bdd = sql.getReadableDatabase();

        String query = "SELECT id,nombre,descripcion,stock,precio_unitario,tasa_iva \n" +
                       "FROM productos WHERE id = " + id;

        Cursor data = bdd.rawQuery(query, null);

        if (data.getCount() < 1) return null;

        data.moveToFirst();

        return new Producto(
                data.getInt(0),
                data.getString(1),
                data.getString(2),
                data.getFloat(3),
                data.getFloat(4),
                data.getFloat(5)
        );
    }

    public Producto[] leerTodo() {
        SQLiteDatabase bdd = sql.getReadableDatabase();
        Cursor data = bdd.rawQuery("SELECT id,nombre,descripcion,stock,precio_unitario,tasa_iva FROM productos ORDER BY nombre", null);

        if (data.getCount() < 1) return null;

        Producto[] productos = new Producto[data.getCount()];
        int i = 0;

        while (data.moveToNext()) {
            productos[i++] = new Producto(
                    data.getInt(0),
                    data.getString(1),
                    data.getString(2),
                    data.getFloat(3),
                    data.getFloat(4),
                    data.getFloat(5)
            );
        }
        return productos;
    }

    public Producto[] leerPorNombre(String nombreStr) {
        SQLiteDatabase bdd = sql.getReadableDatabase();
        Cursor data = bdd.rawQuery("SELECT id,nombre,descripcion,stock,precio_unitario,tasa_iva \n" +
                                   "FROM productos\n" +
                                   "WHERE nombre LIKE '%" + nombreStr + "%'\n " +
                                   "ORDER BY nombre", null);

        if (data.getCount() < 1) return null;

        Producto[] productos = new Producto[data.getCount()];
        int i = 0;

        while (data.moveToNext()) {
            productos[i++] = new Producto(
                    data.getInt(0),
                    data.getString(1),
                    data.getString(2),
                    data.getFloat(3),
                    data.getFloat(4),
                    data.getFloat(5)
            );
        }
        return productos;
    }


    public void actualizar(int id, String nombre, String descripcion, float stock, float precioUnitario, float tasaIva) {
        SQLiteDatabase bdd = sql.getWritableDatabase();
        ContentValues item = new ContentValues();

        item.put("id", id);
        item.put("nombre", nombre);
        item.put("descripcion", descripcion);
        item.put("stock", stock);
        item.put("precio_unitario", precioUnitario);
        item.put("tasa_iva", tasaIva);
        bdd.update("productos", item, "id=" + id, null);
    }

    public void eliminar(int id) {
        SQLiteDatabase bdd = sql.getWritableDatabase();
        bdd.delete("productos", "id=" + id, null);
    }
}
