package com.lab.apis.controller;

import com.lab.apis.model.Libro;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private List<Libro> libros = new ArrayList<>();

    public LibroController() {
        libros.add(new Libro(1, "Cien Años de Soledad", "Gabriel García Márquez", "Novela", 150.00));
        libros.add(new Libro(2, "Don Quijote de la Mancha", "Miguel de Cervantes", "Clásico", 200.00));
        libros.add(new Libro(3, "El Principito", "Antoine de Saint-Exupéry", "Fábula", 90.00));
        libros.add(new Libro(4, "1984", "George Orwell", "Ciencia Ficción", 130.00));
        libros.add(new Libro(5, "Fahrenheit 451", "Ray Bradbury", "Distopía", 110.00));
    }

    @GetMapping
    public List<Libro> obtenerTodos() {
        return libros;
    }

    @GetMapping("/{id}")
    public Libro obtenerPorId(@PathVariable int id) {
        return libros.stream().filter(l -> l.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public Libro crear(@RequestBody Libro libro) {
        libros.add(libro);
        return libro;
    }

    @PutMapping("/{id}")
    public Libro actualizarTotal(@PathVariable int id, @RequestBody Libro nuevo) {
        Libro l = obtenerPorId(id);
        if (l != null) {
            l.setTitulo(nuevo.getTitulo());
            l.setAutor(nuevo.getAutor());
            l.setGenero(nuevo.getGenero());
            l.setPrecio(nuevo.getPrecio());
        }
        return l;
    }

    @PatchMapping("/{id}")
    public Libro actualizarParcial(@PathVariable int id, @RequestBody Libro nuevo) {
        Libro l = obtenerPorId(id);
        if (l != null) {
            if (nuevo.getTitulo() != null) l.setTitulo(nuevo.getTitulo());
            if (nuevo.getAutor() != null) l.setAutor(nuevo.getAutor());
            if (nuevo.getGenero() != null) l.setGenero(nuevo.getGenero());
            if (nuevo.getPrecio() != null) l.setPrecio(nuevo.getPrecio());
        }
        return l;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        libros.removeIf(l -> l.getId() == id);
        return "Libro eliminado exitosamente";
    }
}