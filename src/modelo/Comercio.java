package modelo;

import java.util.List;

public class Comercio extends Actor {

    private String nombreComercio;
    private long cuit;
    private double costoFijo;
    private double costoPorKm;
    private int diaDescuento;
    private int porcentajeDescuentoDia;
    private int porcentajeDescuentoEfectivo;
    List<DiaRetiro> diaRetiros;

    public Comercio(int id, Contacto contacto, String nombreComercio, long cuit, double costoFijo,
                    double costoPorKm, int diaDescuento, int porcentajeDescuentoDia, int porcentajeDescuentoEfectivo,
                    List<DiaRetiro> diaRetiros) {
        super(id, contacto);
        this.nombreComercio = nombreComercio;
        this.cuit = cuit;
        this.costoFijo = costoFijo;
        this.costoPorKm = costoPorKm;
        this.diaDescuento = diaDescuento;
        this.porcentajeDescuentoDia = porcentajeDescuentoDia;
        this.porcentajeDescuentoEfectivo = porcentajeDescuentoEfectivo;
        this.diaRetiros = diaRetiros;
    }

    public String getNombreComercio() {
        return nombreComercio;
    }

    public void setNombreComercio(String nombreComercio) {
        this.nombreComercio = nombreComercio;
    }

    public long getCuit() {
        return cuit;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    public double getCostoFijo() {
        return costoFijo;
    }

    public void setCostoFijo(double costoFijo) {
        this.costoFijo = costoFijo;
    }

    public double getCostoPorKm() {
        return costoPorKm;
    }

    public void setCostoPorKm(double costoPorKm) {
        this.costoPorKm = costoPorKm;
    }

    public int getDiaDescuento() {
        return diaDescuento;
    }

    public void setDiaDescuento(int diaDescuento) {
        this.diaDescuento = diaDescuento;
    }

    public int getPorcentajeDescuentoDia() {
        return porcentajeDescuentoDia;
    }

    public void setPorcentajeDescuentoDia(int porcentajeDescuentoDia) {
        this.porcentajeDescuentoDia = porcentajeDescuentoDia;
    }

    public int getPorcentajeDescuentoEfectivo() {
        return porcentajeDescuentoEfectivo;
    }

    public void setPorcentajeDescuentoEfectivo(int porcentajeDescuentoEfectivo) {
        this.porcentajeDescuentoEfectivo = porcentajeDescuentoEfectivo;
    }

    public List<DiaRetiro> getDiaRetiros() {
        return diaRetiros;
    }

    public void setDiaRetiros(List<DiaRetiro> diaRetiros) {
        this.diaRetiros = diaRetiros;
    }

    @Override
    public String toString() {
        return "Comercio{" +
                "nombreComercio='" + nombreComercio + '\'' +
                ", cuit=" + cuit +
                ", costoFijo=" + costoFijo +
                ", costoPorKm=" + costoPorKm +
                ", diaDescuento=" + diaDescuento +
                ", porcentajeDescuentoDia=" + porcentajeDescuentoDia +
                ", porcentajeDescuentoEfectivo=" + porcentajeDescuentoEfectivo +
                ", diaRetiros=" + diaRetiros +
                '}';
    }
}
