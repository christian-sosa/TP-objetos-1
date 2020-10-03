package modelo;

public class ItemCarrito {
    private Articulo articulo;
    private int cantidad;
    private int id;

    public ItemCarrito(Articulo articulo, int cantidad) {
        this.articulo = articulo;
        this.cantidad = cantidad;
    }

    public double calcularSubtotal() {
        return this.articulo.getPrecio() * this.cantidad;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setid(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getid() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "ItemCarrito [articulo=" + articulo + ", cantidad=" + cantidad + "]";
    }
}
