package modelo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Date;

public class Carrito {
    private int idCarrito;
    private LocalDate fecha;
    private LocalTime hora;
    private boolean cerrado;
    private double descuento;
    private Cliente cliente;
    private List<ItemCarrito> itemCarrito;
    private List<DiaRetiro> diaRetiros;
    private Entrega entrega;


    public Carrito() {
    }

    public Carrito(int idCarrito, LocalDate fecha, LocalTime hora, boolean cerrado, double descuento, Cliente cliente,
                   List<ItemCarrito> itemCarrito, Entrega entrega, List<DiaRetiro> diaRetiros) {
        this.idCarrito = idCarrito;
        this.fecha = fecha;
        this.hora = hora;
        this.cerrado = cerrado;
        this.descuento = descuento;
        this.cliente = cliente;
        this.itemCarrito = itemCarrito;
        this.entrega = entrega;
        this.diaRetiros = diaRetiros;
    }

    public List<DiaRetiro> getDiaRetiros() {
        return diaRetiros;
    }

    public void setDiaRetiros(List<DiaRetiro> diaRetiros) {
        this.diaRetiros = diaRetiros;
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

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }
    @Override
	public String toString() {
		return "Carrito [idCarrito=" + idCarrito + ", fecha=" + fecha + ", hora=" + hora + ", cerrado=" + cerrado
				+ ", descuento=" + descuento + ", cliente=" + cliente + ", itemCarrito=" + itemCarrito + ", entrega="
				+ entrega + "]";
	}

    public double calcularTotalCarrito() {
    	double total=0;
    	for(int i=0;i<this.getItemCarrito().size();i++) {
    		total +=itemCarrito.get(i).getCantidad()*itemCarrito.get(i).getArticulo().getPrecio();
    	}
    	return total;
    }
    public double calcularDescuentoEfectivo(double porcentajeDescuentoEfectivo){
    return calcularTotalCarrito()*porcentajeDescuentoEfectivo/100;
    }
    public double calcularDescuentoDia(int diaDescuento, double porcentajeDescuentoDia){
    return 0;
        
    }
    public double calcularDescuentoCarrito(int diaDescuento, double porcentajeDescuento, double porcentajeDescuentoEfectivo) {
    	double descuento;

    	LocalDate localDate = LocalDate.now();

    	if(localDate.getDayOfWeek().getValue() == diaDescuento) {
    	   if(porcentajeDescuento>porcentajeDescuentoEfectivo) {
    		   descuento=porcentajeDescuento;
    	   }
    	   else {
    		   descuento=porcentajeDescuentoEfectivo;
    	   }  		 
        }
        else {
        	descuento=porcentajeDescuentoEfectivo;
        }
        return descuento;      			
    }
    public double totalAPagarCarrito(){
    	double total = this.calcularTotalCarrito();
    	double descuento = this.getDescuento();
    	return total-descuento;
    }

    public boolean agregarItemCarrito (ItemCarrito itemCarrito){
        int i = 0;
        ItemCarrito itemCarritoAux = traerItemCarrito(itemCarrito.getArticulo().getId());
        if(itemCarritoAux == null){
            this.itemCarrito.add(itemCarrito);
        }else{
            itemCarritoAux.setCantidad(itemCarritoAux.getCantidad() + itemCarrito.getCantidad());
        }
        return true;
    }
    public boolean removerItemCarrito(ItemCarrito itemCarrito){
        itemCarrito = traerItemCarrito(itemCarrito.getArticulo().getId());
        boolean resultado = false;
        if(itemCarrito != null){
            this.itemCarrito.remove(itemCarrito);
            resultado = true;
        }
        return resultado;
    }

    private ItemCarrito traerItemCarrito (int id){
        int i = 0;
        ItemCarrito itemCarrito = null;
        while (i< this.itemCarrito.size() && itemCarrito == null ) {
            ItemCarrito itemCarritoAux = this.itemCarrito.get(i);
            if(itemCarritoAux.getArticulo().getId() == id) {
                itemCarrito = this.itemCarrito.get(i);
            }
            i++;
        }
        return itemCarrito;
    }


}

