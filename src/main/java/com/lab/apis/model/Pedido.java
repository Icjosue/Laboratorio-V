package com.lab.apis.model;

public class Pedido {
    private Integer id;
    private String producto;
    private Integer cantidad;
    private Double precioTotal;
    private String estado;

    // Constructor vacío obligatorio para Spring/Jackson
    public Pedido() {}

    public Pedido(Integer id, String producto, Integer cantidad, Double precioTotal, String estado) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
        this.estado = estado;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public Double getPrecioTotal() { return precioTotal; }
    public void setPrecioTotal(Double precioTotal) { this.precioTotal = precioTotal; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}