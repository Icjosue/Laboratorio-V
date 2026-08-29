package com.lab.apis.model;

public class Pelicula {
    private Integer id;
    private String titulo;
    private String director;
    private Integer anio;
    private String genero;

    public Pelicula() {}

    public Pelicula(Integer id, String titulo, String director, Integer anio, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.anio = anio;
        this.genero = genero;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }
    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
}