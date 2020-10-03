package modelo;

import java.time.LocalDate;

public class Entrega {

    private int id;
    private LocalDate fecha;
    private boolean efectivo;

    public Entrega(int id, LocalDate fecha, boolean efectivo) {
        this.id = id;
        this.fecha = fecha;
        this.efectivo = efectivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isEfectivo() {
        return efectivo;
    }

    public void setEfectivo(boolean efectivo) {
        this.efectivo = efectivo;
    }

    @Override
    public String toString() {
        return "Entrega [id=" + id + ", fecha=" + fecha + ", efectivo=" + efectivo + "]";
    }
}
