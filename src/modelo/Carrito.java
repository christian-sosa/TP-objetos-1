package modelo;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private int idCarrito;
    private LocalDate fecha;
    private LocalTime hora;
    private boolean cerrado;
    private double descuento;
    private Cliente cliente;
    private List<ItemCarrito> itemCarritos;
    private Entrega entrega;

    public Carrito(int idCarrito, LocalDate fecha, LocalTime hora, boolean cerrado, Cliente cliente) {
        this.idCarrito = idCarrito;
        this.fecha = fecha;
        this.hora = hora;
        this.cerrado = cerrado;
        this.cliente = cliente;
        this.itemCarritos = new ArrayList<>();
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

    public List<ItemCarrito> getItemCarritos() {
        return itemCarritos;
    }

    public void setItemCarritos(List<ItemCarrito> itemCarritos) {
        this.itemCarritos = itemCarritos;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    @Override
    public String toString() {
        return "Carrito [idCarrito=" + idCarrito + ", fecha=" + fecha + ", hora=" + hora + ", cerrado=" + cerrado
                + ", descuento=" + descuento + ", cliente=" + cliente + ", itemCarrito=" + itemCarritos + ", entrega="
                + entrega + "]";
    }

    public double calcularTotalCarrito() {
        double total = 0;
        for (ItemCarrito itemCarrito : this.itemCarritos) {
            total += itemCarrito.calcularSubtotal();
        }

        return total;
    }

    public double calcularDescuentoEfectivo(double porcentajeDescuentoEfectivo) {
        return calcularTotalCarrito() * porcentajeDescuentoEfectivo / 100;
    }

    public double calcularDescuentoDia(double porcentajeDescuentoDia) {
        Double descuento = 0d;
        for (ItemCarrito item : this.itemCarritos) {
            descuento += ((item.getCantidad() / 2) * item.getArticulo().getPrecio() * porcentajeDescuentoDia / 100);
        }

        return descuento;
    }

    public double calcularDescuentoCarrito(int diaDescuento, double porcentajeDescuentoDia, double porcentajeDescuentoEfectivo) {
        Double descuentoDia = 0d;
        Double descuentoEfectivo = 0d;

        if (diaDescuento == LocalDate.now().getDayOfWeek().getValue()) {
            descuentoDia = this.calcularDescuentoDia(porcentajeDescuentoDia);
        }
        if (this.getEntrega().isEfectivo()) {
            descuentoEfectivo = this.calcularDescuentoEfectivo(porcentajeDescuentoEfectivo);
        }

        return descuentoDia > descuentoEfectivo ? descuentoDia : descuentoEfectivo;
    }

    public boolean agregarItemCarrito(Articulo articulo, int cantidad) {
        int i = 0;
        ItemCarrito itemCarritoAux = traerItemCarrito(articulo.getId());
        if (itemCarritoAux == null) {
            this.itemCarritos.add(new ItemCarrito(articulo, cantidad));
        } else {
            itemCarritoAux.setCantidad(itemCarritoAux.getCantidad() + cantidad);
        }
        return true;
    }

    public boolean removerItemCarrito(Articulo articulo, int cantidad) {
        ItemCarrito itemCarritoAux = traerItemCarrito(articulo.getId());
        boolean remover = false;
        if (cantidad >= itemCarritoAux.getCantidad()) {
            remover = this.itemCarritos.remove(itemCarritoAux);
        } else {
            itemCarritoAux.setCantidad(itemCarritoAux.getCantidad() - cantidad);
            remover = true;
        }

        return remover;

    }

    private ItemCarrito traerItemCarrito(int id) {
        int i = 0;
        ItemCarrito itemCarrito = null;
        while (i < this.itemCarritos.size() && itemCarrito == null) {
            if (this.itemCarritos.get(i).getArticulo().getId() == id) {
                itemCarrito = this.itemCarritos.get(i);
            }
            i++;
        }
        return itemCarrito;
    }


}

