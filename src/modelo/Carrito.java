package modelo;

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


    public Carrito() {
    }

    public Carrito(int idCarrito, LocalDate fecha, LocalTime hora, boolean cerrado, double descuento, Cliente cliente,
                             Entrega entrega) {
        this.idCarrito = idCarrito;
        this.fecha = fecha;
        this.hora = hora;
        this.cerrado = cerrado;
        this.descuento = descuento;
        this.cliente = cliente;
        this.itemCarritos = new ArrayList<>();
        this.entrega = entrega;
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
    	double total=0;
    	for(int i = 0; i<this.getItemCarritos().size(); i++) {
    		total += itemCarritos.get(i).getCantidad()* itemCarritos.get(i).getArticulo().getPrecio();
    	}
    	return total;
    }
    public double calcularDescuentoEfectivo(double porcentajeDescuentoEfectivo){
    return calcularTotalCarrito()*porcentajeDescuentoEfectivo/100;
    }
    public double calcularDescuentoDia(int diaDescuento, double porcentajeDescuentoDia){
        LocalDate numeroDiaDescuento = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), diaDescuento);
        double descuento = 0;
        if(numeroDiaDescuento.compareTo(LocalDate.now()) == 0 ){
             for (ItemCarrito itemCarrito: this.itemCarritos) {

             }
         }
        return  descuento;
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
    public double totalAPagarCarrito(LocalDate diaDeCompra){
    	double total = this.calcularTotalCarrito();
    	//double descuento = calcularDescuentoCarrito();
    	return total-descuento;
    }

    public boolean agregarItemCarrito (Articulo articulo, int cantidad){
        int i = 0;
        ItemCarrito itemCarritoAux = traerItemCarrito(articulo.getId());
        if(itemCarritoAux == null){
            this.itemCarritos.add(new ItemCarrito(articulo, cantidad));
        }else{
            itemCarritoAux.setCantidad(itemCarritoAux.getCantidad() + cantidad);
        }
        return true;
    }
    public boolean removerItemCarrito(Articulo articulo, int cantidad){
        ItemCarrito itemCarritoAux = traerItemCarrito(articulo.getId());
        boolean remover = false;
        if(cantidad >= itemCarritoAux.getCantidad()){
             remover = this.itemCarritos.remove(itemCarritoAux);
        }else {
            itemCarritoAux.setCantidad(itemCarritoAux.getCantidad() -cantidad);
            remover = true;
        }

        return remover;

    }

    private ItemCarrito traerItemCarrito (int id){
        int i = 0;
        ItemCarrito itemCarrito = null;
        while (i< this.itemCarritos.size() && itemCarrito == null ) {
            if( this.itemCarritos.get(i).getArticulo().getId() == id) {
                itemCarrito = this.itemCarritos.get(i);
            }
            i++;
        }
        return itemCarrito;
    }


}

