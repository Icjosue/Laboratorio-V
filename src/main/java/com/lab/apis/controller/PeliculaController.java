package com.lab.apis.controller;

import com.lab.apis.model.Pelicula;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {

    private List<Pelicula> peliculas = new ArrayList<>();

    public PeliculaController() {
        peliculas.add(new Pelicula(1, "Inception", "Christopher Nolan", 2010, "Ciencia Ficción"));
        peliculas.add(new Pelicula(2, "Interstellar", "Christopher Nolan", 2014, "Ciencia Ficción"));
        peliculas.add(new Pelicula(3, "The Dark Knight", "Christopher Nolan", 2008, "Acción"));
        peliculas.add(new Pelicula(4, "Pulp Fiction", "Quentin Tarantino", 1994, "Crimen"));
        peliculas.add(new Pelicula(5, "Forrest Gump", "Robert Zemeckis", 1994, "Drama"));
    }

    @GetMapping
    public List<Pelicula> obtenerTodas() {
        return peliculas;
    }

    @GetMapping("/{id}")
    public Pelicula obtenerPorId(@PathVariable int id) {
        return peliculas.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public Pelicula crear(@RequestBody Pelicula pelicula) {
        peliculas.add(pelicula);
        return pelicula;
    }

    @PutMapping("/{id}")
    public Pelicula actualizarTotal(@PathVariable int id, @RequestBody Pelicula nueva) {
        Pelicula p = obtenerPorId(id);
        if (p != null) {
            p.setTitulo(nueva.getTitulo());
            p.setDirector(nueva.getDirector());
            p.setAnio(nueva.getAnio());
            p.setGenero(nueva.getGenero());
        }
        return p;
    }

    @PatchMapping("/{id}")
    public Pelicula actualizarParcial(@PathVariable int id, @RequestBody Pelicula nueva) {
        Pelicula p = obtenerPorId(id);
        if (p != null) {
            if (nueva.getTitulo() != null) p.setTitulo(nueva.getTitulo());
            if (nueva.getDirector() != null) p.setDirector(nueva.getDirector());
            if (nueva.getAnio() != null) p.setAnio(nueva.getAnio());
            if (nueva.getGenero() != null) p.setGenero(nueva.getGenero());
        }
        return p;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        peliculas.removeIf(p -> p.getId() == id);
        return "Película eliminada exitosamente";
    }
}