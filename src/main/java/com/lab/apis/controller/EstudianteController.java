package com.lab.apis.controller;

import com.lab.apis.model.Estudiante;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private List<Estudiante> estudiantes = new ArrayList<>();

    public EstudianteController() {
        estudiantes.add(new Estudiante(1, "Carlos", "Pérez", "Ingeniería en Sistemas", 21));
        estudiantes.add(new Estudiante(2, "María", "Gómez", "Licenciatura en Informática", 22));
        estudiantes.add(new Estudiante(3, "Juan", "López", "Ingeniería Industrial", 20));
        estudiantes.add(new Estudiante(4, "Ana", "Martínez", "Administración", 23));
        estudiantes.add(new Estudiante(5, "Luis", "Hernández", "Ingeniería en Sistemas", 19));
    }

    @GetMapping
    public List<Estudiante> obtenerTodos() {
        return estudiantes;
    }

    @GetMapping("/{id}")
    public Estudiante obtenerPorId(@PathVariable int id) {
        return estudiantes.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public Estudiante crear(@RequestBody Estudiante estudiante) {
        estudiantes.add(estudiante);
        return estudiante;
    }

    @PutMapping("/{id}")
    public Estudiante actualizarTotal(@PathVariable int id, @RequestBody Estudiante nuevo) {
        Estudiante e = obtenerPorId(id);
        if (e != null) {
            e.setNombre(nuevo.getNombre());
            e.setApellido(nuevo.getApellido());
            e.setCarrera(nuevo.getCarrera());
            e.setEdad(nuevo.getEdad());
        }
        return e;
    }

    @PatchMapping("/{id}")
    public Estudiante actualizarParcial(@PathVariable int id, @RequestBody Estudiante nuevo) {
        Estudiante e = obtenerPorId(id);
        if (e != null) {
            if (nuevo.getNombre() != null) e.setNombre(nuevo.getNombre());
            if (nuevo.getApellido() != null) e.setApellido(nuevo.getApellido());
            if (nuevo.getCarrera() != null) e.setCarrera(nuevo.getCarrera());
            if (nuevo.getEdad() != null) e.setEdad(nuevo.getEdad());
        }
        return e;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        estudiantes.removeIf(e -> e.getId() == id);
        return "Estudiante eliminado exitosamente";
    }
}