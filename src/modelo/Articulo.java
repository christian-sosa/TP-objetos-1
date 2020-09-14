package modelo;

public class Articulo {
    private int id;
    private String nombre;
    private String codBarras;
    private double precio;

    public Articulo(int id, String nombre, String codBarras, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.codBarras = codBarras;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "id=" + id +
                ", nombre=" + nombre +
                ", codigoBarras=" + codBarras +
                ", precio=" + precio +
                '}';
    }
}