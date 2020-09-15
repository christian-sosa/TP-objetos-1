package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Carrito {
    private int idCarrito;
    private LocalDate fecha;
    private LocalTime hora;
    private boolean cerrado;
    private double descuento;
    private Cliente cliente;
    private List<ItemCarrito> itemCarrito;
    private long conflictivo;

    public Carrito() {
    }

    public Carrito(int idCarrito, LocalDate fecha, LocalTime hora, boolean cerrado, double descuento, Cliente cliente, List<ItemCarrito> itemCarrito) {
        this.idCarrito = idCarrito;
        this.fecha = fecha;
        this.hora = hora;
        this.cerrado = cerrado;
        this.descuento = descuento;
        this.cliente = cliente;
        this.itemCarrito = itemCarrito;
    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public boolean isCerrado() {
        return cerrado;
    }

    public void setCerrado(boolean cerrado) {
        this.cerrado = cerrado;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemCarrito> getItemCarrito() {
        return itemCarrito;
    }

    public void setItemCarrito(List<ItemCarrito> itemCarrito) {
        this.itemCarrito = itemCarrito;
    }
}
