package com.lab.apis.controller;

import com.lab.apis.model.Vehiculo;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private List<Vehiculo> vehiculos = new ArrayList<>();

    public VehiculoController() {
        vehiculos.add(new Vehiculo(1, "Toyota", "Corolla", 2022, 18500.00));
        vehiculos.add(new Vehiculo(2, "Honda", "Civic", 2023, 22000.00));
        vehiculos.add(new Vehiculo(3, "Ford", "Mustang", 2021, 35000.00));
        vehiculos.add(new Vehiculo(4, "Chevrolet", "Onix", 2020, 12500.00));
        vehiculos.add(new Vehiculo(5, "Nissan", "Sentra", 2022, 17000.00));
    }

    @GetMapping
    public List<Vehiculo> obtenerTodos() {
        return vehiculos;
    }

    @GetMapping("/{id}")
    public Vehiculo obtenerPorId(@PathVariable int id) {
        return vehiculos.stream().filter(v -> v.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public Vehiculo crear(@RequestBody Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
        return vehiculo;
    }

    @PutMapping("/{id}")
    public Vehiculo actualizarTotal(@PathVariable int id, @RequestBody Vehiculo nuevo) {
        Vehiculo v = obtenerPorId(id);
        if (v != null) {
            v.setMarca(nuevo.getMarca());
            v.setModelo(nuevo.getModelo());
            v.setAnio(nuevo.getAnio());
            v.setPrecio(nuevo.getPrecio());
        }
        return v;
    }

    @PatchMapping("/{id}")
    public Vehiculo actualizarParcial(@PathVariable int id, @RequestBody Vehiculo nuevo) {
        Vehiculo v = obtenerPorId(id);
        if (v != null) {
            if (nuevo.getMarca() != null) v.setMarca(nuevo.getMarca());
            if (nuevo.getModelo() != null) v.setModelo(nuevo.getModelo());
            if (nuevo.getAnio() != null) v.setAnio(nuevo.getAnio());
            if (nuevo.getPrecio() != null) v.setPrecio(nuevo.getPrecio());
        }
        return v;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        vehiculos.removeIf(v -> v.getId() == id);
        return "Vehículo eliminado exitosamente";
    }
}