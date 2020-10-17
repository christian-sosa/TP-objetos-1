package modelo;

public class Articulo {
    private int id;
    private String nombre;
    private String codBarras;
    private double precio;

    public Articulo(int id, String nombre, String codBarras, double precio) throws  RuntimeException{
        this.id = id;
        this.nombre = nombre;
        setCodBarras(codBarras);
        this.precio = precio;
    }

    public Articulo() {
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

    public void setCodBarras(String codBarras)throws RuntimeException {
        if (validarCodigo(codBarras)) {
            this.codBarras = codBarras;
        } else {
            throw new RuntimeException("Codigo de barras no valido");
        }
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

    public boolean validarCodigo(String codigo) {
        boolean valido = false;
        if (codigo.length() == 13) {
            valido = codigo.charAt(0) == '7'
                    && codigo.charAt(1) == '7'
                    && codigo.charAt(2) == '9'
                    && codigo.charAt(12) == '3';
        }
        return valido;
    }


}
