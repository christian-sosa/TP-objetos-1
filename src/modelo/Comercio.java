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
    )throws RuntimeException {
        super(id, contacto);
        this.nombreComercio = nombreComercio;
        setCuit(cuit);
        this.costoFijo = costoFijo;
        this.costoPorKm = costoPorKm;
        setDiaDescuento(diaDescuento);
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

    public void setCuit(long cuit) throws RuntimeException {
        if (validarIdentificadorUnico(cuit)) {
            this.cuit = cuit;
        }else{
            throw new RuntimeException("CUIT invalido");
        }

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
        if (diaDescuento > 0 && diaDescuento < 8 ) {
            this.diaDescuento = diaDescuento;
        }else {
            throw new RuntimeException("El numero de dia de descuento tiene q ser entre 1 y 7");
        }
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

    public List<Turno> generarTurnos(LocalDate fecha) {
        List<Turno> listaTurnos = new ArrayList<>();
        DiaRetiro diaRetiro = this.obtenerDiaRetiro(fecha);
        LocalTime horaDesde = diaRetiro.getHoraDesde();
        LocalTime horaHasta = diaRetiro.getHoraHasta();

        while (horaDesde.isBefore(horaHasta)) {
            listaTurnos.add(new Turno(fecha, horaDesde, false));
            horaDesde = horaDesde.plusMinutes(diaRetiro.getIntervalo());
        }
        return listaTurnos;
    }

    private DiaRetiro obtenerDiaRetiro(LocalDate fecha) {
        DiaRetiro diaRetiro = null;
        boolean encontrado = false;
        int i = 0;
        while (encontrado == false && i < this.diaRetiros.size()) {
            if (this.diaRetiros.get(i).getDiaSemana() == fecha.getDayOfWeek().getValue()) {
                diaRetiro = this.diaRetiros.get(i);
                encontrado = true;
            }
            i++;
        }
        if (diaRetiro == null) {
            throw new RuntimeException("No se encuentra dia de retiro con ese numero de dia de semana");
        }
        return diaRetiro;
    }


    public boolean agregarArticulo(int id, String nombre, String codBarras, double precio) {
        boolean resultado = false;
        Articulo articulo = new Articulo(id, nombre, codBarras, precio);
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


    public Articulo traerArticulo(int id) {
        Articulo articulo = null;
        int i = 0;
        while (i < this.articulos.size() && articulo == null) {
            if (this.articulos.get(i).getId() == id) {
                articulo = this.articulos.get(i);
            }
            i++;
        }
        return articulo;
    }

    public boolean agregarCarrito(Cliente cliente) {
        boolean resultado = false;

        if (this.traerCarrito(cliente.getId(), false).size() == 0) {
            this.carritos.add(new Carrito(generarId(), LocalDate.now(), LocalTime.now(), false, cliente));
            resultado = true;
        }
        return resultado;
    }

    public List<Carrito> traerCarrito(int idCliente, boolean isCerrado) {
        List<Carrito> carrito = new ArrayList<>();
        boolean carritoAbiertoEncontrado = false;
        int i = this.carritos.size() - 1;
        while (i >= 0 && carritoAbiertoEncontrado == false) {
            Carrito carritoAux = this.getCarritos().get(i);
            if (carritoAux.getCliente().getId() == idCliente && carritoAux.isCerrado() == isCerrado) {
                carrito.add(carritoAux);
                if (!carritoAux.isCerrado()) {
                    carritoAbiertoEncontrado = true;
                }
            }
            i--;
        }
        return carrito;
    }

    private int generarId() {
        int id = 1;
        if (!this.carritos.isEmpty()) {
            int max = 0;
            for (Carrito carrito : this.carritos) {
                if (carrito.getIdCarrito() > max) {
                    max = carrito.getIdCarrito();
                    id = carrito.getIdCarrito() + 1;
                }
            }
        }
        return id;
    }

    @Override
    protected boolean validarIdentificadorUnico(long identificador) {
        boolean valido = false;
        String cuit = Long.toString(identificador);
        if(cuit.length() != 11){
            throw new RuntimeException("La longitud del cuit es erronea");
        }
        int acum = 0;
        int[] multiplicadores = {5, 4 ,3, 2, 7, 6, 5, 4, 3, 2};
        char[] arrayCuit = cuit.toCharArray();
        int verificador = Character.getNumericValue(arrayCuit[10]);
        for (int i = 0; i< 10; i ++){
            acum += Character.getNumericValue(arrayCuit[i]) * multiplicadores[i];
        }
        acum = 11 - (acum % 11);
        if(acum == 11){
            if(verificador == 0){
                valido = true;
            }
        }else if (acum == 10) {
            if(verificador == 9){
                valido = true;
            }
        }else {
            if(verificador == acum){
                valido = true;
            }
        }

        return valido;
    }

    public Double finalizarCompra(Cliente cliente)throws RuntimeException {
        List<Carrito> carritos = this.traerCarrito(cliente.getId(), false);
        if(carritos.isEmpty()){
            throw new RuntimeException("No se detectan carritos abiertos para ese cliente");
        }
        Carrito carrito = carritos.get(0);
        double total = carrito.calcularTotalCarrito() - carrito.calcularDescuentoCarrito(this.diaDescuento,
                this.porcentajeDescuentoDia, this.porcentajeDescuentoEfectivo);
        carrito.setCerrado(true);
        return total;
    }
}
