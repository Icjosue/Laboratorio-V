package com.lab.apis.controller;

import com.lab.apis.model.Curso;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private List<Curso> cursos = new ArrayList<>();

    public CursoController() {
        cursos.add(new Curso(1, "Spring Boot desde Cero", "Prof. Alex", 40, 250.00));
        cursos.add(new Curso(2, "Java Avanzado", "Ing. Roberto", 50, 300.00));
        cursos.add(new Curso(3, "Bases de Datos con MySQL", "Dra. Elena", 35, 180.00));
        cursos.add(new Curso(4, "Desarrollo Web con React", "Lic. Fernando", 45, 220.00));
        cursos.add(new Curso(5, "Git y GitHub Avanzado", "Prof. Alex", 15, 100.00));
    }

    @GetMapping
    public List<Curso> obtenerTodos() {
        return cursos;
    }

    @GetMapping("/{id}")
    public Curso obtenerPorId(@PathVariable int id) {
        return cursos.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public Curso crear(@RequestBody Curso curso) {
        cursos.add(curso);
        return curso;
    }

    @PutMapping("/{id}")
    public Curso actualizarTotal(@PathVariable int id, @RequestBody Curso nuevo) {
        Curso c = obtenerPorId(id);
        if (c != null) {
            c.setNombre(nuevo.getNombre());
            c.setInstructor(nuevo.getInstructor());
            c.setDuracionHoras(nuevo.getDuracionHoras());
            c.setPrecio(nuevo.getPrecio());
        }
        return c;
    }

    @PatchMapping("/{id}")
    public Curso actualizarParcial(@PathVariable int id, @RequestBody Curso nuevo) {
        Curso c = obtenerPorId(id);
        if (c != null) {
            if (nuevo.getNombre() != null) c.setNombre(nuevo.getNombre());
            if (nuevo.getInstructor() != null) c.setInstructor(nuevo.getInstructor());
            if (nuevo.getDuracionHoras() != null) c.setDuracionHoras(nuevo.getDuracionHoras());
            if (nuevo.getPrecio() != null) c.setPrecio(nuevo.getPrecio());
        }
        return c;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        cursos.removeIf(c -> c.getId() == id);
        return "Curso eliminado exitosamente";
    }
}