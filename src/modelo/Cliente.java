package modelo;

public class Cliente extends Actor {
    private String apellido;
    private String nombre;
    private int dni;

    public Cliente(int id, Contacto contacto, String apellido, String nombre, int dni) {
        super(id, contacto);
        this.apellido = apellido;
        this.nombre = nombre;
        setDni(dni);
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        if (validarIdentificadorUnico(dni)) {
            this.dni = dni;
        } else {
            throw new RuntimeException("El dni es invalido");
        }
    }

    @Override
    protected boolean validarIdentificadorUnico(long identificador) {
        return (identificador > 1000000 && identificador < 99999999);
    }

    @Override
    public String toString() {
        return "Cliente [apellido=" + apellido + ", nombre=" + nombre + ", dni=" + dni + "]";
    }
}
