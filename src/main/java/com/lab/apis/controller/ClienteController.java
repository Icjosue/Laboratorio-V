package com.lab.apis.controller;

import com.lab.apis.model.Cliente;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private List<Cliente> clientes = new ArrayList<>();

    public ClienteController() {
        clientes.add(new Cliente(1, "Juan Pérez", "juan.perez@email.com", "5551-2345", "Zona 10, Ciudad de Guatemala"));
        clientes.add(new Cliente(2, "María López", "maria.lopez@email.com", "5552-3456", "Zona 14, Ciudad de Guatemala"));
        clientes.add(new Cliente(3, "Carlos Gómez", "carlos.gomez@email.com", "5553-4567", "Zona 1, Quetzaltenango"));
        clientes.add(new Cliente(4, "Ana Rodríguez", "ana.rodriguez@email.com", "5554-5678", "Antigua Guatemala"));
        clientes.add(new Cliente(5, "Luis Martínez", "luis.martinez@email.com", "5555-6789", "Zona 11, Ciudad de Guatemala"));
    }

    @GetMapping
    public List<Cliente> obtenerTodos() {
        return clientes;
    }

    @GetMapping("/{id}")
    public Cliente obtenerPorId(@PathVariable int id) {
        return clientes.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public Cliente crear(@RequestBody Cliente cliente) {
        clientes.add(cliente);
        return cliente;
    }

    @PutMapping("/{id}")
    public Cliente actualizarTotal(@PathVariable int id, @RequestBody Cliente nuevo) {
        Cliente c = obtenerPorId(id);
        if (c != null) {
            c.setNombre(nuevo.getNombre());
            c.setEmail(nuevo.getEmail());
            c.setTelefono(nuevo.getTelefono());
            c.setDireccion(nuevo.getDireccion());
        }
        return c;
    }

    @PatchMapping("/{id}")
    public Cliente actualizarParcial(@PathVariable int id, @RequestBody Cliente nuevo) {
        Cliente c = obtenerPorId(id);
        if (c != null) {
            if (nuevo.getNombre() != null) c.setNombre(nuevo.getNombre());
            if (nuevo.getEmail() != null) c.setEmail(nuevo.getEmail());
            if (nuevo.getTelefono() != null) c.setTelefono(nuevo.getTelefono());
            if (nuevo.getDireccion() != null) c.setDireccion(nuevo.getDireccion());
        }
        return c;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        clientes.removeIf(c -> c.getId() == id);
        return "Cliente eliminado exitosamente";
    }
}