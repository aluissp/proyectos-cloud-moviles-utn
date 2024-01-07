package com.example.examen_app_moviles.models;

public class Pelicula {
    public int id;
    public String titulo;
    public String director;
    public String anio;
    public int genero_id;

    public Pelicula(int id, String titulo, String director, String anio, int genero_id) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.anio = anio;
        this.genero_id = genero_id;
    }
}
