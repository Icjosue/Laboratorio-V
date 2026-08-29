package com.lab.apis.controller;

import com.lab.apis.model.Producto;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private List<Producto> productos = new ArrayList<>();

    public ProductoController() {
        productos.add(new Producto(1, "Laptop HP", 7500.00, "Tecnología"));
        productos.add(new Producto(2, "Mouse Inalámbrico", 150.00, "Accesorios"));
        productos.add(new Producto(3, "Teclado Mecánico", 450.00, "Accesorios"));
        productos.add(new Producto(4, "Monitor 24 pulgadas", 1200.00, "Tecnología"));
        productos.add(new Producto(5, "Audífonos Bluetooth", 350.00, "Audio"));
    }

    @GetMapping
    public List<Producto> obtenerTodos() {
        return productos;
    }

    @GetMapping("/{id}")
    public Producto obtenerPorId(@PathVariable int id) {
        return productos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        productos.add(producto);
        return producto;
    }

    @PutMapping("/{id}")
    public Producto actualizarTotal(@PathVariable int id, @RequestBody Producto nuevo) {
        Producto p = obtenerPorId(id);
        if (p != null) {
            p.setNombre(nuevo.getNombre());
            p.setPrecio(nuevo.getPrecio());
            p.setCategoria(nuevo.getCategoria());
        }
        return p;
    }

    @PatchMapping("/{id}")
    public Producto actualizarParcial(@PathVariable int id, @RequestBody Producto nuevo) {
        Producto p = obtenerPorId(id);
        if (p != null) {
            if (nuevo.getNombre() != null) p.setNombre(nuevo.getNombre());
            if (nuevo.getPrecio() != null) p.setPrecio(nuevo.getPrecio());
            if (nuevo.getCategoria() != null) p.setCategoria(nuevo.getCategoria());
        }
        return p;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        productos.removeIf(p -> p.getId() == id);
        return "Producto eliminado exitosamente";
    }
}