package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
    List<Carrito> carritos;
    List<Articulo> articulos;

    public Comercio(int id, Contacto contacto, String nombreComercio, long cuit, double costoFijo,
                    double costoPorKm, int diaDescuento, int porcentajeDescuentoDia, int porcentajeDescuentoEfectivo
                    ) {
        super(id, contacto);
        this.nombreComercio = nombreComercio;
        this.cuit = cuit;
        this.costoFijo = costoFijo;
        this.costoPorKm = costoPorKm;
        this.diaDescuento = diaDescuento;
        this.porcentajeDescuentoDia = porcentajeDescuentoDia;
        this.porcentajeDescuentoEfectivo = porcentajeDescuentoEfectivo;
        this.diaRetiros = new ArrayList<>();
        this.carritos = new ArrayList<>();
        this.articulos = new ArrayList<>();
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

    public List<Carrito> getCarritos() {
        return carritos;
    }

    public void setCarritos(List<Carrito> carritos) {
        this.carritos = carritos;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
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

    public List<Turno> generarTurnos (LocalDate fecha){
        List<Turno> listaTurnos = new ArrayList<>();
        DiaRetiro diaRetiro = this.obtenerDiaRetiro(fecha);
        LocalTime horaDesde = diaRetiro.getHoraDesde();
        LocalTime horaHasta = diaRetiro.getHoraHasta();

        while(horaDesde.isBefore(horaHasta)){
            listaTurnos.add(new Turno(fecha, horaDesde, false));
            horaDesde = horaDesde.plusMinutes(diaRetiro.getIntervalo());
        }
        return  listaTurnos;
    }

    private DiaRetiro obtenerDiaRetiro(LocalDate fecha){
        DiaRetiro diaRetiro = null;
        boolean encontrado  = false;
        int i = 0;
        while (encontrado == false && i< this.diaRetiros.size()) {
            if(this.diaRetiros.get(i).getDiaSemana() == fecha.getDayOfWeek().getValue()){
                diaRetiro = this.diaRetiros.get(i);
                encontrado = true;
            }
            i++;
        }
        if (diaRetiro == null){
            throw new RuntimeException("No se encuentra dia de retiro con ese numero de dia de semana");
        }
        return diaRetiro;
    }

    public List<Carrito> generarCarrito (Cliente cliente, boolean estado){
        List<Carrito> carritos = new ArrayList<>();
        boolean carritoAbiertoEncontrado = false ;
        int i = this.carritos.size();
        while(carritoAbiertoEncontrado == false && i > 0  ) {
            Carrito carrito = this.carritos.get(i);
            if(carrito.getCliente().equals(cliente) && carrito.isCerrado() == estado){
                carritos.add(this.carritos.get(i));
                if (estado == false){
                    carritoAbiertoEncontrado = true;
                }
            }
        }
        if(carritos.size() == 0 && !estado){
            carritos.add(new Carrito(generarId(), LocalDate.now(), LocalTime.now(), false, 12, cliente,
                    new Entrega(1, LocalDate.now(), true )));
        }
        return carritos;
    }

    public boolean agregarArticulo(int id, String nombre, String codBarras, double precio) {
        boolean resultado = false;
        Articulo articulo = new Articulo (id, nombre, codBarras, precio);
        int i = 0;
        while (i < this.articulos.size()) {
            if (this.articulos.get(i).getCodBarras().equals(codBarras)) {
                throw new RuntimeException("Ya existe un item con ese codigo de barras");
            }
            i++;
        }
        this.articulos.add(articulo);
        return resultado;
    }

    public Articulo traerArticulo (int id){
        Articulo articulo = null;
        int i  = 0;
        while (i < this.articulos.size() && articulo == null){
            if(this.articulos.get(i).getId() == id){
                articulo = this.articulos.get(i);
            }
            i++;
        }
        return articulo;
    }



    private int generarId(){
        int id = 1;
        for (Carrito carrito : this.carritos) {
            if (carrito.getIdCarrito() > id){
                id = carrito.getIdCarrito();
            }
        }
        return  id;
    }


}
