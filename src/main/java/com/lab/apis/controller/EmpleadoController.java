package com.lab.apis.controller;

import com.lab.apis.model.Empleado;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private List<Empleado> empleados = new ArrayList<>();

    public EmpleadoController() {
        empleados.add(new Empleado(1, "Mario Ramírez", "Desarrollador Senior", 8500.00, "Sistemas"));
        empleados.add(new Empleado(2, "Laura Fernández", "Diseñadora UX", 6200.00, "Diseño"));
        empleados.add(new Empleado(3, "Roberto Castillo", "Gerente de Proyecto", 12000.00, "Administración"));
        empleados.add(new Empleado(4, "Patricia Morales", "Analista de Datos", 7000.00, "Sistemas"));
        empleados.add(new Empleado(5, "Daniel Vasquez", "Soporte Técnico", 4500.00, "IT"));
    }

    @GetMapping
    public List<Empleado> obtenerTodos() {
        return empleados;
    }

    @GetMapping("/{id}")
    public Empleado obtenerPorId(@PathVariable int id) {
        return empleados.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public Empleado crear(@RequestBody Empleado empleado) {
        empleados.add(empleado);
        return empleado;
    }

    @PutMapping("/{id}")
    public Empleado actualizarTotal(@PathVariable int id, @RequestBody Empleado nuevo) {
        Empleado e = obtenerPorId(id);
        if (e != null) {
            e.setNombre(nuevo.getNombre());
            e.setPuesto(nuevo.getPuesto());
            e.setSalario(nuevo.getSalario());
            e.setDepartamento(nuevo.getDepartamento());
        }
        return e;
    }

    @PatchMapping("/{id}")
    public Empleado actualizarParcial(@PathVariable int id, @RequestBody Empleado nuevo) {
        Empleado e = obtenerPorId(id);
        if (e != null) {
            if (nuevo.getNombre() != null) e.setNombre(nuevo.getNombre());
            if (nuevo.getPuesto() != null) e.setPuesto(nuevo.getPuesto());
            if (nuevo.getSalario() != null) e.setSalario(nuevo.getSalario());
            if (nuevo.getDepartamento() != null) e.setDepartamento(nuevo.getDepartamento());
        }
        return e;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        empleados.removeIf(e -> e.getId() == id);
        return "Empleado eliminado exitosamente";
    }
}