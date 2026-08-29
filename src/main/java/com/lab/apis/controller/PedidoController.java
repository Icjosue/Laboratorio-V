package com.lab.apis.controller;

import com.lab.apis.model.Pedido;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private List<Pedido> pedidos = new ArrayList<>();

    public PedidoController() {
        pedidos.add(new Pedido(1, "Laptop Dell XPS 15", 1, 1500.00, "Entregado"));
        pedidos.add(new Pedido(2, "Monitor LG 27 pulgadas", 2, 600.00, "Enviado"));
        pedidos.add(new Pedido(3, "Teclado Mecánico RGB", 1, 120.00, "Pendiente"));
        pedidos.add(new Pedido(4, "Mouse Inalámbrico Logitech", 3, 135.00, "Entregado"));
        pedidos.add(new Pedido(5, "Auriculares Bluetooth Sony", 1, 250.00, "Pendiente"));
    }

    @GetMapping
    public List<Pedido> obtenerTodos() {
        return pedidos;
    }

    @GetMapping("/{id}")
    public Pedido obtenerPorId(@PathVariable int id) {
        return pedidos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public Pedido crear(@RequestBody Pedido pedido) {
        pedidos.add(pedido);
        return pedido;
    }

    @PutMapping("/{id}")
    public Pedido actualizarTotal(@PathVariable int id, @RequestBody Pedido nuevo) {
        Pedido p = obtenerPorId(id);
        if (p != null) {
            p.setProducto(nuevo.getProducto());
            p.setCantidad(nuevo.getCantidad());
            p.setPrecioTotal(nuevo.getPrecioTotal());
            p.setEstado(nuevo.getEstado());
        }
        return p;
    }

    @PatchMapping("/{id}")
    public Pedido actualizarParcial(@PathVariable int id, @RequestBody Pedido nuevo) {
        Pedido p = obtenerPorId(id);
        if (p != null) {
            if (nuevo.getProducto() != null) p.setProducto(nuevo.getProducto());
            if (nuevo.getCantidad() != null) p.setCantidad(nuevo.getCantidad());
            if (nuevo.getPrecioTotal() != null) p.setPrecioTotal(nuevo.getPrecioTotal());
            if (nuevo.getEstado() != null) p.setEstado(nuevo.getEstado());
        }
        return p;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        pedidos.removeIf(p -> p.getId() == id);
        return "Pedido eliminado exitosamente";
    }
}