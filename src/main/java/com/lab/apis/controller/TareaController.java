package com.lab.apis.controller;

import com.lab.apis.model.Tarea;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {
    private List<Tarea> tareas = new ArrayList<>();

    public TareaController() {
        tareas.add(new Tarea(1, "Estudiar Java Spring Boot", false));
        tareas.add(new Tarea(2, "Subir laboratorio de APIs", true));
        tareas.add(new Tarea(3, "Configurar variables de entorno", true));
        tareas.add(new Tarea(4, "Probar endpoints en Postman", true));
        tareas.add(new Tarea(5, "Enviar tarea a la plataforma", false));
    }

    @GetMapping
    public List<Tarea> obtenerTareas() {
        return tareas;
    }
}