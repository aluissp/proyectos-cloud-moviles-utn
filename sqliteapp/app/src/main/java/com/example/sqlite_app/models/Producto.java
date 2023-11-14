package com.example.sqlite_app.models;

public class Producto {
    public int id;
    public String nombre;
    public String descripcion;
    public float stock;
    public float precioUnitario;
    public float tasaIva;

    public Producto(int id, String nombre, String descripcion, float stock, float precioUnitario, float tasaIva) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precioUnitario = precioUnitario;
        this.tasaIva = tasaIva;
    }
}
