package com.lab.apis.controller;

import com.lab.apis.model.Tarea;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private List<Tarea> tareas = new ArrayList<>();

    public TareaController() {
        tareas.add(new Tarea(1, "Instalar Java 21", "Configurar JDK y variables de entorno", true, "Alta"));
        tareas.add(new Tarea(2, "Crear proyecto Spring Boot", "Generar estructura base en Spring Initializr", true, "Alta"));
        tareas.add(new Tarea(3, "Probar APIs en Postman", "Crear la colección organizada con las 10 rutas", false, "Media"));
        tareas.add(new Tarea(4, "Subir repositorio a GitHub", "Crear commits y vincular con origin main", false, "Alta"));
        tareas.add(new Tarea(5, "Redactar documento PDF", "Adjuntar capturas de Postman y enlace del repo", false, "Baja"));
    }

    @GetMapping
    public List<Tarea> obtenerTodas() {
        return tareas;
    }

    @GetMapping("/{id}")
    public Tarea obtenerPorId(@PathVariable int id) {
        return tareas.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public Tarea crear(@RequestBody Tarea tarea) {
        tareas.add(tarea);
        return tarea;
    }

    @PutMapping("/{id}")
    public Tarea actualizarTotal(@PathVariable int id, @RequestBody Tarea nueva) {
        Tarea t = obtenerPorId(id);
        if (t != null) {
            t.setTitulo(nueva.getTitulo());
            t.setDescripcion(nueva.getDescripcion());
            t.setCompletada(nueva.getCompletada());
            t.setPrioridad(nueva.getPrioridad());
        }
        return t;
    }

    @PatchMapping("/{id}")
    public Tarea actualizarParcial(@PathVariable int id, @RequestBody Tarea nueva) {
        Tarea t = obtenerPorId(id);
        if (t != null) {
            if (nueva.getTitulo() != null) t.setTitulo(nueva.getTitulo());
            if (nueva.getDescripcion() != null) t.setDescripcion(nueva.getDescripcion());
            if (nueva.getCompletada() != null) t.setCompletada(nueva.getCompletada());
            if (nueva.getPrioridad() != null) t.setPrioridad(nueva.getPrioridad());
        }
        return t;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        tareas.removeIf(t -> t.getId() == id);
        return "Tarea eliminada exitosamente";
    }
}