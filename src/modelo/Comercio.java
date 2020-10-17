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
    /*Ignacio Oliveto:
           A mi entender toda la logica de turnos deberia ser por comercio, si esto deberia escalar y admitir mas comercios
           lo logico es q los comercios tengan una lista de turnos q puedan estar libres u ocupados y cuando se va a
           generar un retiro verifique si el comercio tiene turnos disponibles para retiro en ese horario.
           Ademas de esto, el encapsulamiento de las funcionalidades seria mas claro y los metodos de la clase podrian usar
           la lista de la instancia del comercio para minimizar la cantidad de parametros.
           La idea de tener q recorrer  carritos para generar turnos libres u ocupados me parecio muy poco eficiente.
   */
    List<Turno> turnos;

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
        this.turnos = new ArrayList<>();
    }

    public void setCuit(long cuit) throws RuntimeException {
        if (validarIdentificadorUnico(cuit)) {
            this.cuit = cuit;
        }else{
            throw new RuntimeException("CUIT invalido");
        }

    }


    public void setDiaDescuento(int diaDescuento) {
        if (diaDescuento > 0 && diaDescuento < 8 ) {
            this.diaDescuento = diaDescuento;
        }else {
            throw new RuntimeException("El numero de dia de descuento tiene q ser entre 1 y 7");
        }
    }


    public List<Carrito> getCarritos() {
        return carritos;
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

    public boolean generarTurnos(LocalDate fecha) {

        DiaRetiro diaRetiro = this.obtenerDiaRetiro(fecha);
        LocalTime horaDesde = diaRetiro.getHoraDesde();
        LocalTime horaHasta = diaRetiro.getHoraHasta();

        while (horaDesde.isBefore(horaHasta)) {
            this.turnos.add(new Turno(fecha, horaDesde, false));
            horaDesde = horaDesde.plusMinutes(diaRetiro.getIntervalo());
        }
        return true;
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


    public boolean agregarArticulo(int id, String nombre, String codBarras, double precio) throws RuntimeException{
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


    public Articulo traerArticulo(int id)throws RuntimeException {
        Articulo articulo = null;
        int i = 0;
        while (i < this.articulos.size() && articulo == null) {
            if (this.articulos.get(i).getId() == id) {
                articulo = this.articulos.get(i);
            }
            i++;
        }
        if(articulo == null){
            throw new RuntimeException("Articulo no existe");
        }
        return articulo;
    }

    /*Ignacio Oliveto:
        LA idea era que al agregar un carrito si se encuentra otro carrito abierto para un cliente no agregue uno nuevo.
     */
    public boolean agregarCarrito(Cliente cliente) {
        boolean resultado = false;

        if (this.traerCarrito(cliente.getId(), false).size() == 0) {
            this.carritos.add(new Carrito(generarIdCarrito(), LocalDate.now(), LocalTime.now(), false, cliente));
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

    private int generarIdCarrito() {
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

    //Ignacio Oliveto: La idea era q este metodo no solo pudiese crear dias de retiro sino remplazarlos para actualizarlos
    public boolean agregarDiaRetiro (int diaSemana, LocalTime horaDesde, LocalTime horaHasta, int intervalo) throws RuntimeException{
        DiaRetiro diaRetiro = traerDiaRetiro(diaSemana);
        if(diaSemana > 7 || diaSemana < 0){
            throw new RuntimeException("Dia de retiro tiene que tener un valor entre 1 y 7 inclusive");
        }
        if(diaRetiro != null ){
            this.diaRetiros.remove(diaRetiro);
        }
        this.diaRetiros.add(new DiaRetiro(generarIdDiaRetiro(), diaSemana, horaDesde, horaHasta, intervalo));
        return true;
    }

    private int generarIdDiaRetiro() {
        int id = 1;
        if (!this.diaRetiros.isEmpty()) {
            int max = 0;
            for (DiaRetiro diaRetiro : this.diaRetiros) {
                if (diaRetiro.getId() > max) {
                    max = diaRetiro.getId();
                    id = diaRetiro.getId() + 1;
                }
            }
        }
        return id;
    }
    /*
    Ignacio Oliveto: Preferi hacer un metodo que reciba un booleano si es envio o retiro en lugar de hacer dos metodos
    me parecio que era menos codigo y se puede reutilizar mas un metodo, se considero ademas que cuando se agrega entrega
    a nivel flujo del sistema es cuando ya se va a cerrar la compra(En la mayoria de los e comerce que vi sucede de este modo).
     */
    public boolean agregarEntrega(Cliente cliente, LocalDate fechaEntrega, boolean efectivo, LocalTime horaDesde,
                                  LocalTime horaHasta, boolean envio)throws RuntimeException {
        boolean resultado = false;
        List<Carrito> carritos = this.traerCarrito(cliente.getId(), false);
        if(carritos.isEmpty()){
            throw new RuntimeException("No se detectan carritos abiertos para ese cliente");
        }
        Carrito carrito = carritos.get(0);

        if(envio){
            carrito.setEntrega(new Envio(1, fechaEntrega, efectivo, horaHasta, horaDesde,
                    generarCosto(cliente.getContacto().getUbicacion()), cliente.getContacto().getUbicacion()));
            resultado = true;
        }else{
            if(traerDiaRetiro(fechaEntrega.getDayOfWeek().getValue()) == null){
                throw new RuntimeException("Dia de retiro no valido");
            }
            List<Turno> turnos = this.traerTurnos(fechaEntrega, false, horaDesde, horaHasta);
            if(turnos.isEmpty()){
                throw new RuntimeException("Horario de retiro invalido");
            }
            turnos.get(0).setOcupado(true);
            carrito.setEntrega(new RetiroLocal(1, fechaEntrega, efectivo, horaDesde));
        }
        return resultado;
    }

    public DiaRetiro traerDiaRetiro(int diaSemana){
        DiaRetiro diaRetiro = null;
        int i = 0;
        while(i<this.diaRetiros.size() && diaRetiro == null ){
            if(this.diaRetiros.get(i).getDiaSemana() == diaSemana){
                diaRetiro = this.diaRetiros.get(i);
            }
            i++;
        }
        return  diaRetiro;
    }


    public double generarCosto(Ubicacion ubicacion) {
        return (distanciaCoord(ubicacion.getLatitud(), ubicacion.getLongitud(), this.getContacto().getUbicacion().getLatitud()
                        , this.getContacto().getUbicacion().getLongitud()) * costoPorKm) + costoFijo;
    }

    public double distanciaCoord(double lat1, double lng1, double lat2, double lng2) {
        double radioTierra = 6371; //en kilómetros
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double va1 =Math.pow(sindLat, 2)+Math.pow(sindLng, 2)*Math.cos(Math.toRadians(lat1))*
                Math.cos(Math.toRadians(lat2));
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
        return radioTierra * va2;
    }

    public double totalAPagar(int idCliente){
        Carrito carrito = traerCarrito(idCliente, false).get(0);
        if(carrito == null){
            throw  new RuntimeException("No se detectan carritos abiertos para ese cliente");
        }
        carrito.setCerrado(true);
        double total = carrito.calcularTotalCarrito() - carrito.calcularDescuentoCarrito(this.diaDescuento,
                this.porcentajeDescuentoDia, this.porcentajeDescuentoEfectivo);
        if(carrito.getEntrega().getClass().equals(Envio.class)) {
            total += ((Envio) carrito.getEntrega()).getCosto();
        }
        return total;
    }
    /*
    Ignacio Oliveto: Traer turnos puede en lugar de recibir solo la fecha tambien si esta diponible u ocupado asi
    en lugar de tener dos metodos tenemos uno solo, ademas podriamos traerlos por franjas horarias para
    tener alternativas de turnos.
     */
    public List<Turno> traerTurnos(LocalDate fechaEntrega,  boolean ocupado, LocalTime desde, LocalTime hasta){
        DiaRetiro diaRetiro = this.obtenerDiaRetiro(fechaEntrega);
        List<Turno> turnos = new ArrayList<>();
        for (Turno turno: this.turnos) {
            if(turno.isOcupado() == ocupado && turno.getDia().compareTo(fechaEntrega) == 0 ){
                if(turno.getHora().compareTo(desde) >= 0 &&
                        turno.getHora().plusMinutes(diaRetiro.getIntervalo()).compareTo(hasta) <= 0  )
                turnos.add(turno);
            }
        }
        return turnos;
    }


}
